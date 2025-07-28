package com.data.ss14.service;

import com.data.ss14.model.dto.request.ShowTimeRequestDTO;
import com.data.ss14.model.entity.ShowTime;

import java.util.List;

public interface ShowTimeService{
    List<ShowTime> getAllShowTimes();
    ShowTime saveShowTime(ShowTimeRequestDTO showTimeRequestDTO);
}