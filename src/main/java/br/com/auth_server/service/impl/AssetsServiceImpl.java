package br.com.auth_server.service.impl;

import br.com.auth_server.api.AssetsCapClient;
import br.com.auth_server.api.AssetsCoinLoreClient;
import br.com.auth_server.dto.response.AssetsResponse;
import br.com.auth_server.dto.response.mapper.AssetsMapper;
import br.com.auth_server.service.AssetsService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetsServiceImpl implements AssetsService {

    private final AssetsCapClient assetsCapClient;
    private final AssetsCoinLoreClient assetsCoinLoreClient;

    @Override
    @CircuitBreaker(name = "assetsCapClient", fallbackMethod = "getFallbackCoinLore")
    public List<AssetsResponse> geAssets() {
        try {
            var capClientResponse = assetsCapClient.getAssets();
            return capClientResponse.getData()
                    .parallelStream()
                    .map(AssetsMapper::mapResponse)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("######## Error > getAssetsCapClient : {}", e.getMessage());
            throw e;
        }
    }

    private List<AssetsResponse> getFallbackCoinLore(Exception e) {
        var coinCoreResponse = assetsCoinLoreClient.geAssets();
        log.info("######## getFallbackCoinLore");
        return coinCoreResponse.getData()
                .parallelStream()
                .map(AssetsMapper::mapResponse)
                .collect(Collectors.toList());
    }
}