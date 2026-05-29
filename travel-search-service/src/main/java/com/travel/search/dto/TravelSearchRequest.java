package com.travel.search.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TravelSearchRequest {

  private String source;
  private String destination;
  private LocalDateTime travelDate;
}
