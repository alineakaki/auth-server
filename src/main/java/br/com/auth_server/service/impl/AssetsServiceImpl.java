package br.com.auth_server.service.impl;

import br.com.auth_server.api.AssetsCapClient;
import br.com.auth_server.service.AssetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetsServiceImpl implements AssetsService {

    private final AssetsCapClient assetsCapClient;

    @Override
    public Object geAssets() {
        return assetsCapClient.getAssets();
    }
}
