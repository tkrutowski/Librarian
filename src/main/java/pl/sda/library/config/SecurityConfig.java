package pl.sda.library.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        int i=0;
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .rolePrefix("ROLE_")
                .usersByUsernameQuery("SELECT username, password, 'true' FROM users where username = ?")
                .authoritiesByUsernameQuery("SELECT username, role FROM users where username = ?");

//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .and()
//                .withUser("test")
//                .password(passwordEncoder().encode("test"))
//                .roles("USER");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/h2-console/**").permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();


      /*  http.csrf().disable()
                .authorizeRequests()
//                .antMatchers("/api/**").hasRole("ADMIN")
                .antMatchers("/home").hasAnyRole("ADMIN","USER")
                .antMatchers("/users","/users/**").hasRole("ADMIN")
                .antMatchers("/users/**").hasRole("ADMIN")
                .antMatchers("/bookstores/delete/**","/authors/delete/**").hasRole("ADMIN")
                .antMatchers("/bookstores","/authors").hasAnyRole("ADMIN","USER")
                .antMatchers("/userbooks","/userbooks/**").hasRole("USER")
                .antMatchers("/books","/books/**").hasRole("USER")
                .antMatchers("/**","/login*").permitAll()
                .antMatchers("/h2console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login-error")
                .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                .deleteCookies("JSESSIONID").clearAuthentication(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/error403");*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }
}
