package br.felipehenriques.archiveBoss.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.felipehenriques.archiveBoss.model.User;
import br.felipehenriques.archiveBoss.security.jwt.JWTAuthenticationFilter;
import br.felipehenriques.archiveBoss.security.jwt.JWTLoginFilter;
import br.felipehenriques.archiveBoss.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable caching
        http.headers().cacheControl();

        http.csrf().disable() // disable csrf for our requests.
        	.headers().frameOptions().disable()
        	.and()
        	.authorizeRequests()
        	.antMatchers(HttpMethod.POST,"/users").permitAll()
        	.antMatchers("/h2/**").permitAll()
        	.anyRequest().authenticated()
        	.and()
        	// We filter the api/login requests
        	.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
        	// And filter other requests to check the presence of JWT in header
        	.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
