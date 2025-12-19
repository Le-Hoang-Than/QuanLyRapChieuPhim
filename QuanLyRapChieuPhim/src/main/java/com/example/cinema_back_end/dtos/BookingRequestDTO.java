package com.example.cinema_back_end.dtos;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class BookingRequestDTO {
    private Integer userId;
    private Integer scheduleId;
    private List<Integer> listSeatIds;
}
