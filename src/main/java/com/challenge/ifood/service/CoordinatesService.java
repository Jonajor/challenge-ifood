package com.challenge.ifood.service;

import com.challenge.ifood.feign.SpotifyClient;
import com.challenge.ifood.feign.WeatherClient;
import com.challenge.ifood.model.SpotifyAccessTokenResponse;
import com.challenge.ifood.model.Wather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CoordinatesService {

    @Autowired
    private WeatherClient weatherClient;

    @Autowired
    private SpotifyClient spotifyClient;

    private static final String API_KEY_WEARTHER ="b77e07f479efe92156376a8b07640ced";
    private static final String IDS = "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B";
    @Value("${authentication.clientId}")
    private String clientId;
    @Value("${authentication.clientSecret}")
    private String clientSecret;
    @Value("${authentication.accessTokenUri}")
    private String accessTokenUri;
    @Value("${authentication.grant-type}")
    private String grantType;


    public Wather getCoordinates(Optional<Double> latitude, Optional<Double> longitude, Optional<String> cidade){

        var teste= cidade.filter(String::isEmpty)
                .map(s -> weatherClient.buscaPorCoordenada(latitude.get(), longitude.get(), API_KEY_WEARTHER))
                .orElse(weatherClient.buscaPorCidade(cidade.get(), API_KEY_WEARTHER));

        /*
            TODO: Implementar chamada do token com feign
            TODO: Ajustar para chamar a api correta do Spotify
            TODO: Verificar temperatura do retor da api de tempo para oferecer sugestao de genero musical
            TODO: Mudar chamada do token para spring security oauth
         */
        var toke = getAccessToken();
        var token = "Bearer " + toke;
        var teste2 = spotifyClient.buscaGenero(token, IDS);
        System.out.println(teste2);
        return teste;

    }

    public String getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("grant_type", grantType);
        map.add("client_id",clientId);
        map.add("client_secret", clientSecret);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<SpotifyAccessTokenResponse> response = restTemplate.postForEntity(
               accessTokenUri, request , SpotifyAccessTokenResponse.class);

        return response.getBody().getAccessToken();
    }

}
