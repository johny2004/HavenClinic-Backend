package puj.web.clinicahaven.security;
import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        // Permitir localhost para desarrollo
        config.addAllowedOrigin("http://localhost:4200");
        // Permitir GitHub Pages para producción
        config.addAllowedOrigin("https://johny2004.github.io");
        config.addAllowedOrigin("https://johny2004.github.io/HavenClinic-Angular-Frontend");
        // Permitir todos los headers
        config.addAllowedHeader("*");
        // Permitir todos los métodos HTTP
        config.addAllowedMethod("*");
        config.setExposedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION));
        config.setMaxAge(3600L); // Cache preflight por 1 hora

        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));

        bean.setOrder(-102);
        return new CorsFilter(source);
    }
}