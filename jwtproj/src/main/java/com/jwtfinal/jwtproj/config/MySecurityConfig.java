package com.jwtfinal.jwtproj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwtfinal.jwtproj.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	//over
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//which authentication we want to use in-memory or db authentication etc
		//here we will tell we want userDetailsService authentication this will be custom service.
		//super.configure(auth);
		auth.userDetailsService(customUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//we will define our URL which url to give permit and which url should be private(can be used by authenticated user)
		//super.configure(http);
		//Cross-Site Request Forgery (CSRF) is an attack that forces an end user to execute
		//unwanted actions on a web application in which they're currently authenticated.
		//Cross-origin resource sharing (CORS) is a mechanism that allows restricted resources on
		//a web page to be requested from another domain outside the domain from which the first resource was served.
		http.csrf().disable()
		     .cors().disable()
		     .authorizeRequests()
		     .antMatchers("/token").permitAll()
		     .anyRequest().authenticated()
		     .and()
		     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		     .and()
		     .exceptionHandling().authenticationEntryPoint(entryPoint);
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}

	
	//create a passwordencoder bean class
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		//when using it practically then use Bcrypt passwordEncoder
		return NoOpPasswordEncoder.getInstance();
		
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
