package com.challenge.ifood.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpotifyAccessTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokeType;

    @JsonProperty("expires_in")
    private Long expireIn;

    @JsonProperty("scope")
    private String scope;
}
