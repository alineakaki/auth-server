package br.com.auth_server.controller;

import br.com.auth_server.dto.response.AssetsResponse;
import br.com.auth_server.service.AssetsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class AssetsController {

    private final AssetsService assetsService;

    @GetMapping(value = "/assets")
    public ResponseEntity<List<AssetsResponse>> assets() {
       return ResponseEntity.status(HttpStatus.OK).body(assetsService.geAssets());
    }
}
