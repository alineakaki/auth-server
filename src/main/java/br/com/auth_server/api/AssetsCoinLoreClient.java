package br.com.auth_server.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "AssetsCoinLoreClient", url="${assets.coin.lore.api.url}")
public interface AssetsCoinLoreClient {
    @GetMapping("/api/tickers/")
    Object getAssets();
}
