package pl.sda.library.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    DataSource dataSource;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .rolePrefix("ROLE_")
//                .usersByUsernameQuery("SELECT username, password, 'true' FROM users where username = ?")
//                .authoritiesByUsernameQuery("SELECT username, role FROM users where username = ?");

        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .and()
                .withUser("test")
                .password(passwordEncoder().encode("test"))
                .roles("USER");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http//.csrf().disable()
//                .requestMatchers()
//                .and()
                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/login*").permitAll()
//                .antMatchers("/logout**").permitAll()
                .antMatchers("/error**").hasAnyRole("ADMIN","USER")
                .antMatchers("/home").hasAnyRole("ADMIN","USER")
                .antMatchers("/users","/users/**").hasRole("ADMIN")
                .antMatchers("/bookstores/**").hasAuthority("USER")
                .antMatchers("/userbooks","/userbooks/**").hasRole("USER")
                .antMatchers("/**","/login*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .loginPage("/login").permitAll()
                .failureUrl("/login-error")
                .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                .deleteCookies("JSESSIONID").clearAuthentication(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/error403")
//        .and()
//        .anonymous().disable()
        ;



        // http builder configurations for authorize requests and form login (see below)
 /*       http//.csrf().disable()
                .requestMatchers()
                .antMatchers("/","/login*")
                .and()
                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/login*").permitAll()
//                .antMatchers("/logout**").permitAll()
                .antMatchers("/error**").hasAnyRole("ADMIN","USER")
                .antMatchers("/home").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/users/**").hasRole("ADMIN")
                .antMatchers("/bookstores/**").hasAuthority("USER")
                .antMatchers("/userbooks/**").hasRole("USER")
                .antMatchers("/userbooks").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .permitAll()
                    .loginPage("/login").permitAll()
                    .failureUrl("/login-error")
                    .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                .deleteCookies("JSESSIONID").clearAuthentication(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
        .and()
        .exceptionHandling().accessDeniedPage("/error403")
//        .and()
//        .anonymous().disable()
        ;*/
         /*       .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/users/**").permitAll()
                .antMatchers("/users/**").hasRole("ADMIN")
                .antMatchers("/userbooks/**").hasRole("USER")
                .antMatchers("/bookstores/**").hasRole("USER")
                .antMatchers("/login*").permitAll()
                .antMatchers("/h2console/**").permitAll()
//                .antMatchers("/anonymous*").anonymous()

                .and()
        .httpBasic()
        .and()
        .authorizeRequests()
        ;*/


//                .anyRequest().authenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .formLogin()
////                .loginPage()
////                .loginPage("/login")
////                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/", true)
////                .usernameParameter("login")
////                .passwordParameter("password")
////                .failureUrl("/login.html?error=true")
////                .failureHandler(authenticationFailureHandler())
//                .and()
//                .logout()
//                .logoutUrl("/perform_logout")
//                .deleteCookies("JSESSIONID")
////                .logoutSuccessHandler(logoutSuccessHandler());
//                .and().csrf().disable()
        ;
    }
}
