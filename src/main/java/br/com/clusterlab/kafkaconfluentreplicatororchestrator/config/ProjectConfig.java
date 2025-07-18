package br.com.clusterlab.kafkaconfluentreplicatororchestrator.config;


import br.com.clusterlab.kafkaconfluentreplicatororchestrator.dto.Credential;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@PropertySource("classpath:application.properties")
@PropertySource("classpath:secret.properties")
public class ProjectConfig {
    static Logger logger = LoggerFactory.getLogger(ProjectConfig.class);

    @Configuration
    public static class SecurityConfiguration {
        @Bean
        public InMemoryUserDetailsManager userDetailsService() {
            var users = new InMemoryUserDetailsManager();
            try {

                for (Credential credential : Credentials.getCredentials(AppProperties.credentials)) {
                    UserDetails user = User
                            .withUsername(credential.getUsername().toLowerCase())
                            .username(credential.getUsername().toLowerCase())
                            .password(credential.getPassword())
                            .roles(credential.getRole().toUpperCase())
                            .build();
                    users.createUser(user);
                    logger.info("Loaded user \"{}\" to UserDetailsService.", credential.getUsername());
                }

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return users;

        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.httpBasic();
//            http.authorizeRequests().filterSecurityInterceptorOncePerRequest(false).anyRequest().hasRole("ADMIN");
            http.csrf(AbstractHttpConfigurer::disable);
            http.authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers(HttpMethod.GET).anonymous()
                    .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                    .anyRequest().denyAll());
            return http.build();
        }
    }
}
