package com.telcel.aprovisionamiento.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
public class SpringSecutiry  extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/","/css/**", "/js/**", "/img/**").permitAll()
		.antMatchers("/inicio/**").hasAnyRole("USER")
		.antMatchers("/file/**").hasAnyRole("USER")
		.anyRequest().authenticated()
		.and().csrf()
		.and()
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout().permitAll()
		.and().exceptionHandling().accessDeniedPage("/error_403")
		;
	}

	@Autowired
	public void configuracionGlobal(AuthenticationManagerBuilder builder) throws Exception {
		

		@SuppressWarnings("deprecation")
		UserBuilder userBuilder = User.withDefaultPasswordEncoder();
		
		builder.inMemoryAuthentication()
		.withUser(userBuilder.username("admin").password("123").roles("ADMIN", "USER"))
		.withUser(userBuilder.username("ismael").password("123").roles("USER"));
		
	}
}
