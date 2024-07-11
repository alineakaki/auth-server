package br.com.auth_server.controller;

import br.com.auth_server.service.AssetsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class AssetsController {

    private final AssetsService assetsService;

    @GetMapping(value = "/assets")
    public ResponseEntity<Object> assets(HttpServletRequest request) throws JsonProcessingException {
       return ResponseEntity.status(HttpStatus.OK).body(assetsService.geAssets());
    }
}