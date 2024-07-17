package br.com.auth_server.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AssetsResponse {
    @JsonProperty("name")
    private String name;

    @JsonProperty("rank")
    private int rank;

    @JsonProperty("priceUsd")
    @JsonAlias("price_usd")
    private BigDecimal priceUsd;

    @JsonProperty("symbol")
    private String symbol;
}
