    package br.com.auth_server.service.impl;

    import br.com.auth_server.api.AssetsCapClient;
    import br.com.auth_server.api.AssetsCoinLoreClient;
    import br.com.auth_server.dto.response.AssetsResponse;
    import br.com.auth_server.dto.response.mapper.AssetsMapper;
    import br.com.auth_server.service.AssetsService;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import io.github.resilience4j.circuitbreaker.CircuitBreaker;
    import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
    import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
    import org.springframework.stereotype.Service;

    import java.time.Duration;
    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    public class AssetsServiceImpl implements AssetsService {

        private final AssetsCapClient assetsCapClient;
        private final AssetsCoinLoreClient assetsCoinLoreClient;
        private final CircuitBreaker circuitBreaker;

        public AssetsServiceImpl(AssetsCapClient assetsCapClient, AssetsCoinLoreClient assetsCoinLoreClient, ObjectMapper objectMapper, ObjectMapper objectMapper1) {
            this.assetsCapClient = assetsCapClient;
            this.assetsCoinLoreClient = assetsCoinLoreClient;
            CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                    .failureRateThreshold(50)
                    .waitDurationInOpenState(Duration.ofSeconds(30))
                    .permittedNumberOfCallsInHalfOpenState(3)
                    .build();

            CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
            this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("assetsCapClient");
        }

        @Override
        public List<AssetsResponse> geAssets() {
            return CircuitBreaker.decorateSupplier(circuitBreaker, this::callGetAssets).get();
        }

        private List<AssetsResponse> callGetAssets() {
            try {
                var capClientResponse = assetsCapClient.getAssets();
                return capClientResponse.getData()
                        .parallelStream()
                        .map(AssetsMapper::mapResponse)
                        .collect(Collectors.toList());

            } catch (Exception e) {
                var coinCoreResponse = assetsCoinLoreClient.geAssets(); //fallback
                return coinCoreResponse.getData()
                        .parallelStream()
                        .map(AssetsMapper::mapResponse)
                        .collect(Collectors.toList());
            }
        }
    }