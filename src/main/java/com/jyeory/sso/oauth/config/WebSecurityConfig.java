package com.jyeory.sso.oauth.config;

import java.io.PrintWriter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.jyeory.sso.oauth.filter.ResourceOwnerAuthenticationFilter;
import com.jyeory.sso.oauth.handler.CustomAuthenticationFailureHandler;
import com.jyeory.sso.oauth.handler.CustomAuthenticationSuccessHandler;
import com.jyeory.sso.oauth.provider.CustomAuthenticationProvider;
import com.jyeory.sso.service.OAuth2UserDetailService;

/**
 * 
 * @author yun-yeoseong
 *
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired @Qualifier("userDetailsService")
	private OAuth2UserDetailService userDetailsService;
	
    @Bean(name="bCryptEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
	
	@Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
        // @formatter:off
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		   .antMatchers("/css/**")
		   .antMatchers("/vendor/**")
		   .antMatchers("/js/**")
		   .antMatchers("/favicon*/**")
		   .antMatchers("/img/**")
		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
		.authorizeRequests()
			.antMatchers("/login*/**").permitAll()
			.antMatchers("/error**").permitAll()
			.anyRequest().authenticated()
		.and().csrf()
			  .disable()
		.addFilter(authenticationFilter())
		.exceptionHandling()
			  .authenticationEntryPoint(authenticationEntryPoint())
			  .accessDeniedHandler((request,response,exception)->{
				  response.setContentType("application/json;charset=UTF-8");
		          response.setHeader("Cache-Control", "no-cache");
		          PrintWriter writer = response.getWriter();
		          writer.println(new AccessDeniedException("access denied !"));
			  })
		;
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	public ResourceOwnerAuthenticationFilter authenticationFilter() throws Exception {
		ResourceOwnerAuthenticationFilter filter = new ResourceOwnerAuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/loginProcess");
		filter.setUsernameParameter("username");
		filter.setPasswordParameter("password");
		filter.afterPropertiesSet();
		return filter;
	}
	
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new LoginUrlAuthenticationEntryPoint("/loginPage");
	}
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		CustomAuthenticationSuccessHandler successHandler = new CustomAuthenticationSuccessHandler();
		return successHandler;
	}
	
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		CustomAuthenticationFailureHandler failureHandler = new CustomAuthenticationFailureHandler();
		failureHandler.setDefaultFailureUrl("/loginPage?error=loginfali");
		return failureHandler;
	}

	@Bean
	@Primary
    @Override 
    protected AuthenticationManager authenticationManager() throws Exception {
    	return super.authenticationManager(); 
    }
}
