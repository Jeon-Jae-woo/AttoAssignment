package com.attoresearch.Assignment.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HostDTO {
    private int id;
    private String ip;
    private String name;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
