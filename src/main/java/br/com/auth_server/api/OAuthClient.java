package br.com.auth_server.api;

import br.com.auth_server.config.OAuthInterceptor;
import br.com.auth_server.dto.response.AuthResponse;
import br.com.auth_server.dto.request.TokenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@FeignClient(name= "OAuthClient", url="${oauth.issuer}", configuration = OAuthInterceptor.class)
public interface OAuthClient {
    @PostMapping(value= "/oauth/token")
    AuthResponse token(@RequestBody TokenRequest tokenRequest);
}
