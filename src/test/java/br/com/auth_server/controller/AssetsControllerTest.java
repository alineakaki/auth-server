package br.com.auth_server.controller;

import br.com.auth_server.dto.response.AssetsResponse;
import br.com.auth_server.service.AssetsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssetsControllerTest {

    @InjectMocks
    private AssetsController assetsController;

    @Mock
    private AssetsService assetsService;

    @Test
    void whenRequestOk_thenReturnAssetsList() {
        List<AssetsResponse> assetsResponses  = List.of(AssetsResponse.builder()
                .name("name")
                .rank(123)
                .priceUsd(new BigDecimal("100.00"))
                .symbol("CRIP").build());
        when(assetsService.geAssets()).thenReturn(assetsResponses);
        ResponseEntity<List<AssetsResponse>> responseEntity = assetsController.assets();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(assetsResponses, responseEntity.getBody());
    }
}