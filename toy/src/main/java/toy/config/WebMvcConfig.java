package toy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {
    
    @Value("${file.dir}")
    private String fileDir;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 정적 리소스 설정
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        
        // 업로드된 파일 설정
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:" + fileDir)
                .setCachePeriod(0);
        
        log.info("File upload path: {}", fileDir);
    }
}
