package com.travel.search.model;

import lombok.Data;

import java.util.List;

@Data
public class TravelSearchResponse {

    private List<String> flights;
    private List<String> hotels;
}
