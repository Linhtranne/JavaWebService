package com.data.service;

import com.data.entity.ScreenRoom;
import com.data.repository.ScreenRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScreenRoomService implements IService<ScreenRoom, Long> {

    @Autowired
    private ScreenRoomRepository screenRoomRepository;

    @Override
    public List<ScreenRoom> findAll() {
        return screenRoomRepository.findAll();

    }

    @Override
    public ScreenRoom save(ScreenRoom screenRoom) {
        return screenRoomRepository.save(screenRoom);
    }

    @Override
    public Optional<ScreenRoom> findById(Long id) {
        return screenRoomRepository.findById(id);
    }

    @Override
    public ScreenRoom update(ScreenRoom screenRoom) {
        if (screenRoomRepository.existsById(screenRoom.getId())) {
            return screenRoomRepository.save(screenRoom);
        }
        throw new IllegalArgumentException("Không tìm thấy phòng chiếu với ID: " + screenRoom.getId());
    }

    @Override
    public void delete(Long id) {
        screenRoomRepository.deleteById(id);
    }
}