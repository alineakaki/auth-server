package br.com.auth_server.dto.response.mapper;

import br.com.auth_server.dto.response.AssetsResponse;

public class AssetsMapper {

    public static AssetsResponse mapResponse(AssetsResponse response) {
        return AssetsResponse.builder()
                .name(response.getName())
                .rank(response.getRank())
                .symbol(response.getSymbol())
                .priceUsd(response.getPriceUsd()).build();
    }
}
