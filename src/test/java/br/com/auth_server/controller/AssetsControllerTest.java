package br.com.auth_server.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.auth_server.service.AssetsService;

@ExtendWith(MockitoExtension.class)
public class AssetsControllerTest {

    @InjectMocks
    private AssetsController assetsController;

    @Mock
    private AssetsService assetsService;

    @Test
    public void testAssets() throws Exception {
        var assetsJson = "[{\"id\": 1, \"name\": \"Asset 1\"}]";
        when(assetsService.geAssets()).thenReturn(assetsJson);
        ResponseEntity<Object> response = assetsController.assets();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(assetsJson, response.getBody());
    }
}