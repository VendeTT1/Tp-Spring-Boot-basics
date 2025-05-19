package ma.enset.springmvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {//permet a spring de specifier les user qui peuvent acceder a l'app
        PasswordEncoder encoder = passwordEncoder();
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password("1234").roles("USER").build(),
                User.withUsername("user2").password("1234").roles("USER").build(),
                User.withUsername("admin").password("1234").roles("USER","ADMIN").build()
        );
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {// specifie la strategie de security
        return http
                .formLogin(Customizer.withDefaults())// config par defaut, if user not auth display form
//                .formLogin(fl->fl.loginPage("/login"))// if i wanat to use my custom login page
                .authorizeHttpRequests(ar->ar.requestMatchers("/index/**").hasRole("USER"))// pour acceder a la resource /index/** ton role doit etre USER
                .authorizeHttpRequests(ar->ar.requestMatchers("/save/**","/delete/**").hasRole("ADMIN"))
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated()) // every request need to authenticate
                .build();
    }
}
