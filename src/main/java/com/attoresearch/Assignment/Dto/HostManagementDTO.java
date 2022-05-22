package com.attoresearch.Assignment.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HostManagementDTO{
    private int id;
    private String ip;
    private String name;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private boolean alive_status;
    private LocalDateTime alive_time;
}