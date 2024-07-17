package br.com.auth_server.api;

import br.com.auth_server.dto.response.DataResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "AssetsCoinLoreClient", url="${assets.coin.lore.api.url}")
public interface AssetsCoinLoreClient {
    @GetMapping("/api/tickers/")
    DataResponse geAssets();
}
