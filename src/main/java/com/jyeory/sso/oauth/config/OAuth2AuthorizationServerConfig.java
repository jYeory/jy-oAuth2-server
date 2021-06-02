package com.jyeory.sso.oauth.config;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyeory.sso.oauth.token.CustomTokenEnhancer;

import lombok.extern.slf4j.Slf4j;


/**
 * Authorization Server Config
 * @author yun-yeoseong
 *
 */
@Slf4j
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired @Qualifier("mysqlDatasource") 
	private DataSource mysqlDataSource;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired @Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired @Qualifier("bCryptEncoder")
	private PasswordEncoder bCryptEncoder;
	
	@Value("${login.redirect.uri}")
	private String redirectUrl;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.passwordEncoder(bCryptEncoder);
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
		security.allowFormAuthenticationForClients();	// 없으면 form 로그인 후 토큰 발급 불가능
		security.accessDeniedHandler((request, response, exception)->{
										response.setContentType("application/json;charset=UTF-8");
							            response.setHeader("Cache-Control", "no-cache");
							            PrintWriter writer = response.getWriter();
							            writer.println(new AccessDeniedException("access denied 1111 !"));
									})
		.authenticationEntryPoint((request, response, exception)->{
									response.setContentType("application/json;charset=UTF-8");
						            response.setHeader("Cache-Control", "no-cache");
						            PrintWriter writer = response.getWriter();
						            writer.println(new AccessDeniedException("access denied 2222 !"));
								})
		;
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.withClientDetails( new ClientDetailsServiceImpl(mysqlDataSource()) );
		clients.inMemory()
				.withClient("myClient")
			        .secret(bCryptEncoder.encode("client_secret_key"))
			        .authorizedGrantTypes("password", "authorization_code", "refresh_token")
			        .autoApprove(true)
			        .accessTokenValiditySeconds(180)
			        .refreshTokenValiditySeconds(180) // 3m
			        .redirectUris(redirectUrl)
			        .scopes("read", "write");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenJwtConverter()));
        
		endpoints
			.tokenServices(tokenServices())
			.tokenEnhancer(tokenEnhancerChain)
			.authenticationManager(authenticationManager)
			.userDetailsService(userDetailsService) //refresh token 발급을 위해서는 UserDetailsService(AuthenticationManager authenticate()에서 사용)필요
//			.authorizationCodeServices(new JdbcAuthorizationCodeServices(mysqlDataSource)) //authorization code를 DB로 관리 코드 테이블의 authentication은 blob데이터타입으로..
			.authorizationCodeServices(new InMemoryAuthorizationCodeServices()) //authorization code를 DB로 관리 코드 테이블의 authentication은 blob데이터타입으로..
//			.approvalStore(approvalStore()) // 리소스 소유자의 승인을 추가, 검색, 취소하기 위한 메소드를 정의
			.tokenStore(jwtTokenStore()) // 토큰과 관련된 인증 데이터를 저장, 검색, 제거, 읽기를 정의
//			.tokenStore(redisTokenStore()) // 토큰과 관련된 인증 데이터를 저장, 검색, 제거, 읽기를 정의
			.accessTokenConverter(accessTokenJwtConverter())	// 액세스 토큰 형식, 
			;
	}

	@Autowired 	
	private RedisConnectionFactory redisConnectionFactory;
	@Bean    
    public TokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setEnableTransactionSupport(true);
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer(Charset.forName("UTF8")));
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer(new ObjectMapper()));
        
        template.setHashKeySerializer(new StringRedisSerializer(Charset.forName("UTF8")));
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer(new ObjectMapper()));
        return template;
    }
	
	@Bean
	public JwtTokenStore jwtTokenStore() {
		return new JwtTokenStore(accessTokenJwtConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenJwtConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey("non-prod-signature");
		
		return converter;
	}
	
	/*
	 * 새로운 클라이언트 등록을 위한 빈
	 */
	@Bean
	public ClientRegistrationService clientRegistrationService() {
		return new JdbcClientDetailsService(mysqlDataSource);
	}

	@Bean
	public JdbcApprovalStore approvalStore() {
		return new JdbcApprovalStore(mysqlDataSource);
	}

	@Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(redisTokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(accessTokenJwtConverter());
        defaultTokenServices.setAccessTokenValiditySeconds(180);
        defaultTokenServices.setRefreshTokenValiditySeconds(180);
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }
}
