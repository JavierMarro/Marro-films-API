//package dev.javiermarro.films.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/v1/**")
//                        .allowedOrigins("http://localhost:5173") //TODO Change this allowedOrigin when front deployed to Netlify
//                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .allowedHeaders("*");
//            }
//        };
//    }
//}
//
 // TODO: When putting together the front end uncomment this to allow CORS
