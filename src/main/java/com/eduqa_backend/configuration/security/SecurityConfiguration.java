package com.eduqa_backend.configuration.security;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private CustomLogoutHandler customLogoutHandler;
    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cr->cr.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/create","/search","/graphqil","/graphql","/logout")
                    .permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin(login -> {
                    login.loginPage("/login").permitAll();
                    login.defaultSuccessUrl("/success-login");
                    login.failureUrl("/fail-login").permitAll();
                })
                .sessionManagement(ses -> {
                    ses.maximumSessions(1);
                    ses.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
                    ses.sessionFixation((sessionFixation) -> sessionFixation
                            .newSession());
                })
                .logout((logout) -> {
                    logout.logoutUrl("/logout");
                    logout.addLogoutHandler(customLogoutHandler);
                    logout.logoutSuccessUrl("/login?logout");
                    logout.invalidateHttpSession(true);
                    logout.clearAuthentication(true);
                    logout.deleteCookies("JSESSIONID");
                    logout.permitAll();
                })
                .formLogin(login -> {
                    login.loginPage("/login").permitAll();
                    login.defaultSuccessUrl("/login?success", true);
                }).build();
    }
   @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:5173");  
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));  // Allowed methods
        configuration.setAllowedHeaders(Arrays.asList("*"));   
        configuration.setAllowCredentials(true);   

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); 

        return source;
    }
    // @Bean
    // public AuthenticationProvider authenticationProvider() {
    //     DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    //     provider.setUserDetailsService(userDetailsService);
    //     provider.setPasswordEncoder(new BCryptPasswordEncoder());
    //     return provider;

    // }
    @Bean
public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(new BCryptPasswordEncoder()); // set your custom password encoder if needed
    return provider;
}

}