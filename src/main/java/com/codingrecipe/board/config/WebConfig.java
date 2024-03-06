package com.codingrecipe.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private String resourcePath = "/upload/**"; // view에서  접근할 경로
    private String savePath = "file:////Users/eunsikkim/ESKIM/02. Project/03. CodingRecipe/05. SpringBoot_Board/workspace/board/board_attach_file/"; // 실제 파일 저장 경로

    // resourcePath -> savePath : resourcePath에서 접근 하면 savePath에서 찾아 주도록 설정
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }
}
