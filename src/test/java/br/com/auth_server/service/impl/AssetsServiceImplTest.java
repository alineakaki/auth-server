package br.com.auth_server.service.impl;


import br.com.auth_server.api.AssetsCapClient;
import br.com.auth_server.api.AssetsCoinLoreClient;
import br.com.auth_server.dto.response.AssetsResponse;
import br.com.auth_server.dto.response.DataResponse;
import br.com.auth_server.dto.response.mapper.AssetsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AssetsServiceImplTest {

    @InjectMocks
    private AssetsServiceImpl assetsService;

    @Mock
    private AssetsCapClient assetsCapClient;

    @Mock
    private AssetsCoinLoreClient assetsCoinLoreClient;

    @Test
    void whenGetAssetsSuccess_thenReturnAssets() {
        var response = new DataResponse();
        var assetDto = new AssetsResponse();
        assetDto.setName("Ethereum");
        assetDto.setSymbol("ETH");
        var data = List.of(AssetsMapper.mapResponse(assetDto));
        response.setData(data);

        when(assetsCapClient.getAssets()).thenReturn(response);

        List<AssetsResponse> result = assetsService.geAssets();
        assertEquals(1, result.size());
        verify(assetsCapClient, times(1)).getAssets();
        verify(assetsCoinLoreClient, never()).geAssets();
    }
}