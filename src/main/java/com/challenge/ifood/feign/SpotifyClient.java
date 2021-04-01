package com.challenge.ifood.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "SpotifyClient", url = "https://api.spotify.com/v1")
public interface SpotifyClient {

    @GetMapping("/audio-features")
    String buscaGenero(@RequestHeader(value = "Authorization") String token, @RequestParam(name = "ids") String ids);
}
