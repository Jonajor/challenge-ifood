package com.challenge.ifood.feign;

import com.challenge.ifood.model.Wather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "WeatherClient", url = "http://api.openweathermap.org/data/2.5/weather")
public interface WeatherClient {

    @GetMapping
    Wather buscaPorCidade(@RequestParam(name = "q") String cidade,
                          @RequestParam(name = "appid") String apiKey);

    @GetMapping
    Wather buscaPorCoordenada(@RequestParam(name = "lat") Double latitude,
                              @RequestParam(name = "lon") Double longitude,
                              @RequestParam(name = "appid") String apiKey);
}
