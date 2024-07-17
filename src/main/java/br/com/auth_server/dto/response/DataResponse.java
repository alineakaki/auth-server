package br.com.auth_server.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class DataResponse {
    @JsonProperty("data")
    private List<AssetsResponse> data;
}
