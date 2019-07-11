package tern.block.demo.webConfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import tern.block.demo.NodeAuthenticationProvider;
import tern.block.demo.Handler.AuthenticationFailurHandler;
import tern.block.demo.Handler.AuthenticationSuccessHandler;
import tern.block.demo.Handler.LogoutSuccessHandler;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailurHandler authenticationFailureHandler;
	
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
	private NodeAuthenticationProvider nodeAuthenticationProvider;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//auth.inMemoryAuthentication().withUser("user").password("123").roles("USER");
		auth.authenticationProvider(nodeAuthenticationProvider);
	}
    
	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .authorizeRequests()
            .antMatchers("/","/login","/index","/register","/node/registry","/css/**","/images/**","/assets/**","/js/**","/fonts/**","/jquery-ui-datepicker/**","/webfonts/**").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler)
            .failureUrl("/login?error=true")
            .permitAll()
            .and()
        .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//登出
            .logoutSuccessHandler(logoutSuccessHandler)
            .permitAll()
            .and();
    	
    	http.csrf().disable();

    }

    
}