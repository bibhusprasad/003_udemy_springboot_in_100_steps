package com.springboot100steps.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// Authentication : User --> Roles
	@Override
	/*protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())  
        .withUser("bibhu")
         .password("ipsita")
         .roles("user","admin")
         .and()
         .withUser("alok")
         .password("alok")
         .roles("user");
	}*/
	
	@Bean
	public UserDetailsService userDetailsService() {

	    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	    final User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
	    UserDetails user = userBuilder
	            .username("alok")
	            .password("alok")
	            .roles("user")
	            .build();

	    UserDetails admin = userBuilder
	            .username("bibhu")
	            .password("ipsita")
	            .roles("user","admin")
	            .build();

	    return new InMemoryUserDetailsManager(user, admin);
	}
	
	
	
	// Authorization : Role -> Access
	// survey -> USER
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers("/surveys/**")
				.hasRole("user").antMatchers("/users/**").hasRole("user")
				.antMatchers("/**").hasRole("admin").and().csrf().disable()
				.headers().frameOptions().disable();
	}

}
