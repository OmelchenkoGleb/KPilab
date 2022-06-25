package com.example.demo.config;
import com.example.demo.Security.AuthProviderImple;
import com.example.demo.Security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
@ComponentScan("com.example.demo.Security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthProviderImple authProviderImple;
    @Autowired
    private com.example.demo.Security.MyUserDetailsService MyUserDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception  {
        http.authorizeRequests()
                .antMatchers("/").anonymous()
                .antMatchers(
                        "/admin", "/admin/add", "/admin/ad", "/admin/search",
                        "/admin/edit/{id}", "/admin/edit/{id}", "/admin/delite/{id}",
                        "/chatonline").authenticated()
                .and().csrf().disable()
                .formLogin().loginPage("/")
                .loginProcessingUrl("/login")
                .usernameParameter("username")

//                .and().rememberMe().userDetailsService(MyUserDetailsService).rememberMeParameter("username")

                .and().exceptionHandling().accessDeniedPage("/admin")
                .and().logout();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProviderImple).userDetailsService(MyUserDetailsService);
    }

}
