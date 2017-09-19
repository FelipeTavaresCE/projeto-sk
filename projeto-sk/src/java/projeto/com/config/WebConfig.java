package projeto.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan("projeto.com.")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/webapp/resources/static/app/**")
                .addResourceLocations("/webapp/resources/static/app/");
        registry.addResourceHandler("/webapp/resources/static/lib/**")
                .addResourceLocations("/webapp/resources/static/lib/");
        registry.addResourceHandler("/webapp/resources/static/js/**")
                .addResourceLocations("/webapp/resources/static/js/");
        registry.addResourceHandler("/webapp/resources/static/css/**")
                .addResourceLocations("/webapp/resources/static/css/");
        registry.addResourceHandler("/webapp/webapp/resources/static/views/**")
                .addResourceLocations("/webapp/webapp/resources/static/views/");
        registry.addResourceHandler("/webapp/resources/static/**")
                .addResourceLocations("/webapp/resources/static/");
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
