// package dio.dio_spring_security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class WebSecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 .anyRequest().authenticated()
//             )

//         return http.build();
//     }

//     @Bean
//     public UserDetailsService userDetailsService() {
//         UserDetails user = User.withDefaultPasswordEncoder()
//             .username("user")
//             .password("user123") // Sem criptografia, igual ao código antigo
//             .roles("USERS")
//             .build();

//         UserDetails admin = User.withDefaultPasswordEncoder()
//             .username("admin")
//             .password("master123")
//             .roles("MANAGERS")
//             .build();

//         return new InMemoryUserDetailsManager(user, admin);
//     }
// }
