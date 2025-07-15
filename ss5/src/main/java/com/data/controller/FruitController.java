package com.data.controller;

import com.data.modal.DTO.FruitDTO;
import com.data.modal.entity.Fruit;
import com.data.service.FruitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruits")
@Api(value = "Fruit API", description = "Các thao tác để quản lý trái cây")
public class FruitController {

    @Autowired
    private FruitService fruitService;

    @GetMapping
    @ApiOperation(value = "Lấy danh sách tất cả trái cây", response = List.class)
    public ResponseEntity<List<FruitDTO>> getAllFruits() {
        List<FruitDTO> fruits = fruitService.getAllFruits();
        return new ResponseEntity<>(fruits, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Lấy thông tin trái cây theo ID", response = FruitDTO.class)
    public ResponseEntity<FruitDTO> getFruitById(
            @ApiParam(value = "ID của trái cây cần lấy", required = true) @PathVariable Long id) {

        FruitDTO fruit = fruitService.getFruitById(id);
        if (fruit != null) {
            return new ResponseEntity<>(fruit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ApiOperation(value = "Tạo mới một trái cây")
    public ResponseEntity<Fruit> createFruit(
            @ApiParam(value = "Đối tượng trái cây cần tạo", required = true) @RequestBody Fruit fruit) {

        Fruit createdFruit = fruitService.createFruit(fruit);
        return new ResponseEntity<>(createdFruit, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Cập nhật một trái cây đã tồn tại")
    public ResponseEntity<Fruit> updateFruit(
            @ApiParam(value = "ID của trái cây cần cập nhật", required = true) @PathVariable Long id,
            @ApiParam(value = "Đối tượng trái cây sau khi cập nhật", required = true) @RequestBody Fruit fruit) {

        Fruit updatedFruit = fruitService.updateFruit(id, fruit);
        if (updatedFruit != null) {
            return new ResponseEntity<>(updatedFruit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Xóa một trái cây")
    public ResponseEntity<Void> deleteFruit(
            @ApiParam(value = "ID của trái cây cần xóa", required = true) @PathVariable Long id) {

        fruitService.deleteFruit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

