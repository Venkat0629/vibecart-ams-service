//package com.nisum.vibe.cart.account.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * Configuration class for setting up CORS (Cross-Origin Resource Sharing) mappings.
// * This configuration allows requests from specified origins and methods.
// */
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//
//    private static final Logger logger = LoggerFactory.getLogger(CorsConfig.class);
//
//    /**
//     * Configures CORS settings for the application.
//     *
//     * @param registry the {@link CorsRegistry} to add CORS mappings
//     */
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        logger.debug("Configuring CORS settings");
//        registry.addMapping("/api/v1/vibe-cart/accounts/**").allowedOrigins("http://localhost:3000").allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS").allowedHeaders("*").allowCredentials(true);
//
//        logger.debug("CORS settings configured for /api/v1/vibe-cart/accounts/**");
//    }
//}
