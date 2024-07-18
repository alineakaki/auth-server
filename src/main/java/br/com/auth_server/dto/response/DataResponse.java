package br.com.auth_server.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataResponse {
    @JsonProperty("data")
    private List<AssetsResponse> data;
}
