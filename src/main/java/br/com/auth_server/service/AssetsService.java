package br.com.auth_server.service;

import br.com.auth_server.dto.response.AssetsResponse;

import java.util.List;

public interface AssetsService {
    List<AssetsResponse> geAssets();
}
