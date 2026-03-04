@Configuration
@EnableMethodSecurity

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                //Esses endpoits qualquer um pode acessasr
                .requestMatchers("/login").permitAll()
                .requestMatchers("/cadastro").permitAll()
            )

        return http.build();
    }
}