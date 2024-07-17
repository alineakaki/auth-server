package br.com.auth_server.api;

import br.com.auth_server.dto.response.DataResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "AssetsCapClient", url="${assets.cap.api.url}")
public interface AssetsCapClient {
    @GetMapping("/v2/assets")
    DataResponse getAssets();
}
