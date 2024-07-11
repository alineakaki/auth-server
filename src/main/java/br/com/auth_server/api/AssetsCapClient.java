package br.com.auth_server.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "AssetsCapClient", url="${assets.cap.api.url}")
public interface AssetsCapClient {
    @GetMapping("v2/assets")
    Object getAssets();
}
