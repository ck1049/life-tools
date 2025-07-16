package com.loafer.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.x509.SubjectDnX509PrincipalExtractor;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.WebSessionServerLogoutHandler;
import reactor.core.publisher.Mono;

/**
 * @author loafer
 * @since 2025-03-08 17:52:27
 **/
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfiguration {

    @Bean
    @ConditionalOnMissingBean({SecurityFilterChain.class, SecurityWebFilterChain.class})
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        DelegatingServerLogoutHandler logoutHandler = new DelegatingServerLogoutHandler(
                new SecurityContextServerLogoutHandler(), new WebSessionServerLogoutHandler()
        );

        SubjectDnX509PrincipalExtractor principalExtractor = new SubjectDnX509PrincipalExtractor();
        principalExtractor.setSubjectDnRegex("OU=(.*?)(?:,|$)");

        ReactiveAuthenticationManager authenticationManager = authentication -> {
//            authentication.setAuthenticated("Trusted Org Unit".equals(authentication.getName()));
            authentication.setAuthenticated(true);
            return Mono.just(authentication);
        };

        http
//                .x509(Customizer.withDefaults())
                .x509(x509Spec -> x509Spec
                        .principalExtractor(principalExtractor)
                        .authenticationManager(authenticationManager)
                )
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/blog/**").permitAll()
                        .anyExchange().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                )
                .logout(logout -> logout.logoutHandler(logoutHandler));

        return http.build();
    }
}
