package fr.formation.eprint.config;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class WebPrefixConfig implements WebMvcConfigurer {
	  /**
     * Defines the "/api" prefix for all {@code @RestController} in the
     * application.
     * <p>
     * Configuring this way prevents conflicts and ease configuration with oauth
     * authentication endpoints (<i>i.e.</i> {@code "/oauth/token"}). Specified
     * in application properties would change the endpoint to
     * {@code "/api/oauth/token"}) and impact security endpoints configuration.
     *
     * @param configurer a path configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
	configurer.addPathPrefix("/api",
		HandlerTypePredicate.forAnnotation(RestController.class));
    }
    
    @Bean
    protected ModelMapper modelMapper() {
	ModelMapper mapper = new ModelMapper();
	mapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(AccessLevel.PRIVATE)
		.setMatchingStrategy(MatchingStrategies.LOOSE);
	return mapper;
    }
    
    @Bean
    protected ObjectMapper objectMapper() {
	ObjectMapper mapper = new ObjectMapper();
	mapper.findAndRegisterModules().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	return mapper;
    }
}
