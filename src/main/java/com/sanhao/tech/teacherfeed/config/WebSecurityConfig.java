package com.sanhao.tech.teacherfeed.config;

/**
 * Created by njp on 2016/4/18.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableAutoConfiguration
@Configuration
@EnableWebSecurity
@PropertySource(value = { "classpath:conf/app.properties" })
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${app.web.user:sanhao}")
    private String user;

    @Value("${app.web.password:sanhao123}")
    private String password;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    //    http.authorizeRequests().antMatchers("/open").permitAll();
    //    http.authorizeRequests().antMatchers("/other").denyAll(); // Block it for now
        http.authorizeRequests().antMatchers("/update").authenticated().and().httpBasic();
        http.authorizeRequests().antMatchers("/full").authenticated().and().httpBasic();
        http.authorizeRequests().antMatchers("/dump").authenticated().and().httpBasic();
        http.authorizeRequests().antMatchers("/teacher").permitAll();
        http.csrf().disable();
      //  http.authorizeRequests().antMatchers("/teacher").authenticated().and().httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser(user).password(password).roles("USER");
    }
}