package br.com.auth_server.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OAuthInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.uri(requestTemplate.url().replace("%3A", ":"));
        requestTemplate.header("content-type", "application/json");
    }
}
