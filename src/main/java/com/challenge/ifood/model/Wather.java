
package com.challenge.ifood.model;

import lombok.Data;

import java.util.List;

@Data
public class Wather {
    private String base;
    private Clouds clouds;
    private Long cod;
    private Coord coord;
    private Long dt;
    private Long id;
    private Main main;
    private String name;
    private Sys sys;
    private Long timezone;
    private Long visibility;
    private List<Weather> weather;
    private Wind wind;

}
