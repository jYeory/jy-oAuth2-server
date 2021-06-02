package com.jyeory.sso.oauth.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jyeory.sso.service.OAuth2UserDetailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(value="customAuthenticationProvider")
public class CustomAuthenticationProvider  implements AuthenticationProvider {

	@Autowired @Qualifier("userDetailsService")
	private OAuth2UserDetailService userDetailsService;
	
	@Autowired @Qualifier("bCryptEncoder")
	private PasswordEncoder bCryptEncoder;
	
	@Value("${oauth.run.mode}")
    private String runMode;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String rawPassword = (String) authentication.getCredentials();
        
        System.out.println("\t\t username : " + username);
        System.out.println("\t\t rawPassword : " + rawPassword);
        
        try {
        	// do something
             
        } catch (UsernameNotFoundException e) {
        	log.error("", e);
            throw new UsernameNotFoundException(e.getMessage());
        } catch (BadCredentialsException e) {
        	log.error("", e);
            throw new BadCredentialsException(e.getMessage());
        } catch (LockedException e) {
        	log.error("", e);
            throw new LockedException(e.getMessage());
        } catch (AccountExpiredException e) {
        	log.error("", e);
            throw new LockedException(e.getMessage());
        } catch (DisabledException e) {
        	log.error("", e);
            throw new LockedException(e.getMessage());
        } catch (NullPointerException e) {
        	log.error("", e);
            throw new NullPointerException(e.getMessage());
        } catch (Exception e) {
        	log.error("", e);
            throw new RuntimeException(e.getMessage());
        }
        
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(null, rawPassword, null);
		result.setDetails(authentication.getDetails());
		
        return result;
    }

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
