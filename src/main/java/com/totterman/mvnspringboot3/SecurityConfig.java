package com.totterman.mvnspringboot3;

import static org.springframework.security.config.Customizer.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    /*
     * @Bean
     * public UserDetailsService UserDetailsService() {
     * UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
     * userDetailsManager.createUser(
     * User.withDefaultPasswordEncoder()
     * .username("user")
     * .password("password")
     * .roles("USER")
     * .build());
     * userDetailsManager.createUser(
     * User.withDefaultPasswordEncoder()
     * .username("admin")
     * .password("password")
     * .roles("ADMIN")
     * .build());
     * return userDetailsManager;
     * }
     */
    /*
     * @Bean
     * public OAuth2AuthorizedClientManager clientManager(
     * ClientRegistrationRepository clientRegistrationRepository,
     * OAuth2AuthorizedClientRepository authorizedClientRepository) {
     * OAuth2AuthorizedClientProvider clientProvider =
     * OAuth2AuthorizedClientProviderBuilder.builder()
     * .authorizationCode()
     * .refreshToken()
     * .clientCredentials()
     * .password()
     * .build();
     * DefaultOAuth2AuthorizedClientManager clientManager =
     * new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,
     * authorizedClientRepository);
     * clientManager.setAuthorizedClientProvider(clientProvider);
     * return clientManager;
     * }
     */

    @Bean
    CommandLineRunner initUsers(UserManagementRepository repository) {
        return args -> {
            repository.save(new UserAccount("alice", "password", "ROLE_USER"));
            repository.save(new UserAccount("bob", "password", "ROLE_USER"));
            repository.save(new UserAccount("admin", "password", "ROLE_ADMIN"));
        };
    }

    @Bean
    UserDetailsService userService(UserRepository repo) {
        return username -> repo.findByUsername(username).asUser();
    }

    /*
     * @Bean
     * SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity)
     * throws Exception {
     * httpSecurity.authorizeHttpRequests(requests -> requests
     * .anyRequest()
     * .authenticated());
     * httpSecurity.formLogin(withDefaults());
     * httpSecurity.httpBasic(withDefaults());
     * return httpSecurity.build();
     * }
     */

    @Bean
    SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/login").permitAll()
                .requestMatchers("/", "/search").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/**").authenticated()
//                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/new-video", "/delete/**").authenticated()
                .anyRequest().denyAll());
        http
                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }

}
