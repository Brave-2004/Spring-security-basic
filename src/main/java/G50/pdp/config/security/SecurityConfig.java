package G50.pdp.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(conf ->conf.disable());

        http.authorizeHttpRequests(conf ->conf
                .requestMatchers("/login")
                .permitAll()


                .anyRequest()
                .authenticated()

        );
        http.formLogin(conf-> {
            try {
                conf.init(http);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        String encoder1 = passwordEncoder.encode("123");
//        String encoder2 = passwordEncoder.encode("0926");
//
//        UserDetails user = User.builder()
//                .password(encoder1)
//                .username("user")
//                .build();
//        UserDetails admin = User.builder()
//                .password(encoder2)
//                .username("admin")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

     @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
