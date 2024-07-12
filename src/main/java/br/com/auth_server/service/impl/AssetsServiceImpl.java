    package br.com.auth_server.service.impl;

    import br.com.auth_server.api.AssetsCapClient;
    import br.com.auth_server.api.AssetsCoinLoreClient;
    import br.com.auth_server.service.AssetsService;
    import io.github.resilience4j.circuitbreaker.CircuitBreaker;
    import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
    import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
    import org.springframework.stereotype.Service;

    import java.time.Duration;

    @Service
    public class AssetsServiceImpl implements AssetsService {

        private final AssetsCapClient assetsCapClient;
        private final AssetsCoinLoreClient assetsCoinLoreClient;
        private final CircuitBreaker circuitBreaker;

        public AssetsServiceImpl(AssetsCapClient assetsCapClient, AssetsCoinLoreClient assetsCoinLoreClient) {
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
        public Object geAssets() {
            return CircuitBreaker.decorateSupplier(circuitBreaker, this::callGetAssets).get();
        }

        private Object callGetAssets() {
            try {
                return assetsCapClient.getAssets();
            } catch (Exception e) {
                return assetsCoinLoreClient.getAssets(); //fallback
            }
        }
    }