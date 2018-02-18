package com.mytaxi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.driver.CarService;

@RestController
@RequestMapping("v1/cars")
public class CarController
{

    @Autowired
    private CarService carService;


    @GetMapping
    public List<CarDTO> getCars()
    {
        return CarMapper.makeCarDTOList(carService.findAll());
    }


    @GetMapping("/filter")
    public List<CarDTO> getCarsByParams(
        @RequestParam(required = false) String manufacturer, @RequestParam(required = false) String model, @RequestParam(required = false) String licensePlate,
        @RequestParam(required = false) EngineType engineType)
    {
        return CarMapper.makeCarDTOList(carService.findByParams(manufacturer, model, licensePlate, engineType));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@RequestBody CarDTO carDTO) throws ConstraintsViolationException
    {
        CarDO carDO = CarMapper.makeCarDO(carDTO);
        return CarMapper.makeCarDTO(carService.create(carDO));
    }


    @GetMapping("/{carId}")
    public CarDTO getCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        return CarMapper.makeCarDTO(carService.find(carId));
    }


    @PutMapping("/{carId}")
    public void rateCar(@Valid @PathVariable long carId, @RequestParam int rating) throws EntityNotFoundException
    {
        carService.rateCar(carId, rating);
    }


    @DeleteMapping("/{carId}")
    public void deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        carService.delete(carId);
    }

}
