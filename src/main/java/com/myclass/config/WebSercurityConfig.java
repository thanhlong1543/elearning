package com.myclass.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.myclass.service.impl.UserDetailServiceImpl;
import com.myclass.util.UrlConstants;

@Component
@EnableWebSecurity
@ComponentScan("com.myclass")
@Order(1)
public class WebSercurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailServiceImpl).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.csrf().disable().antMatcher("/admin/**").authorizeRequests()
				.antMatchers("/admin/login/**",  "/", "/home").permitAll();
		http.authorizeRequests()
				.antMatchers(UrlConstants.Admin.HOME+"/**", UrlConstants.Admin.COURSE + "/**", UrlConstants.Admin.VIDEO+"/**",
						UrlConstants.Admin.USER+"/**", UrlConstants.Admin.TARGET+"/**").hasAnyRole("TEACHER","ADMIN")
				.antMatchers(UrlConstants.Admin.COURSE, UrlConstants.Admin.TARGET, UrlConstants.Admin.VIDEO,
						UrlConstants.Admin.USER).hasRole("TEACHER")
				.antMatchers("/admin/**").hasRole("ADMIN")
				//.anyRequest().permitAll()
				.anyRequest().authenticated()
				.and().formLogin().loginProcessingUrl("/admin/login").loginPage("/admin/login")
				.usernameParameter("email").passwordParameter("password").defaultSuccessUrl("/admin/home/")
				.failureUrl("/admin/login?error=true")
				.and()
				.logout()
				.logoutUrl("/admin/logout")
				.logoutSuccessUrl("/admin/login");
		http.authorizeRequests()
				.and()
				.exceptionHandling()
				.accessDeniedPage("/admin/403");

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}

}