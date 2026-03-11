
package puj.web.clinicahaven.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


//En esta clase voy a configurar todo lo relacionado a seguridad(Filter Chain(tercer patron de diseño))
@Configuration //decir que en esta clase se van a crear beans
@EnableWebSecurity  
public class SecurityConfig {
    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    @Bean //decir que este metodo va a crear un bean que se va a manejar  de manejador de objetos 
     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> {})  // Habilitar CORS usando la configuración de CorsFilter
            .csrf(AbstractHttpConfigurer::disable)
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .authorizeHttpRequests(exchanges ->   //configurar lo que quiero que sea publico o no 
                exchanges
                     .requestMatchers("/login").permitAll()
                    .requestMatchers("/h2/**" ).permitAll() 
                    //acceso del veterinario
                    //funciones con los clientes
                    .requestMatchers("/cliente/find/**" ).hasAuthority("VETERINARIO")
                     .requestMatchers("/veterinarios/details").hasAuthority("VETERINARIO")
                     .requestMatchers("/cliente/all").hasAuthority("VETERINARIO")
                     .requestMatchers("/cliente/findClienteByNombre/**").hasAuthority("VETERINARIO")
                     .requestMatchers("/cliente/update/**").hasAuthority("VETERINARIO")
                     //.requestMatchers("/cliente/eliminarCliente/**").hasAuthority("VETERINARIO")
                     //.requestMatchers("/cliente/agregarCliente").hasAuthority("VETERINARIO")
                     //funciones con las mascotas
                    .requestMatchers("/mascotas/vetmascota").hasAuthority("VETERINARIO")   
                    .requestMatchers("/mascotas/addPet/**").hasAuthority("VETERINARIO") 
                    .requestMatchers("/mascotas/deletePet/**").hasAuthority("VETERINARIO")
                    .requestMatchers("/mascotas/actualizar_mascota/**").hasAuthority("VETERINARIO")

                    //funciones con los tratamientos
                    .requestMatchers("/tratamientos/**").hasAuthority("VETERINARIO")


                    //Acceso del cliente
                    .requestMatchers("/mascotas/mis_mascotas/").hasAuthority("CLIENTE")


                    //acceso del admin
                    .requestMatchers("/admin/").hasAuthority("ADMIN")
                    .requestMatchers("/veterinarios/all").hasAuthority("ADMIN")
                     .requestMatchers("/veterinarios/agregarVeterinario").hasAuthority("ADMIN")
                     .requestMatchers("/veterinarios/update/**").hasAuthority("ADMIN")
                     .requestMatchers("/dashboard/kpis").hasAuthority("ADMIN")
                     



                     //acceso del cliente
                    .requestMatchers("/cliente/details").hasAuthority("CLIENTE")
                    .anyRequest().permitAll()
            )
            .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint))
           .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
            return http.build();
     }

     @Bean
     PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
     }

     @Bean  
     public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration
     ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
     }

     @Bean
     public JWTAuthenticationFilter jwtAuthenticationFilter(){
        return new JWTAuthenticationFilter();
     }
}