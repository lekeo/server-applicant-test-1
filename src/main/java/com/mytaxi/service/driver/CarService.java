package com.mytaxi.service.driver;

import java.util.List;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

public interface CarService
{
    List<CarDO> findAll();


    List<CarDO> findByParams(String manufacturer, String model, String licensePlate, EngineType engineType);


    CarDO find(Long carId) throws EntityNotFoundException;


    CarDO create(CarDO carDO) throws ConstraintsViolationException;


    void delete(Long carId) throws EntityNotFoundException;


    void rateCar(Long carId, Integer rating) throws EntityNotFoundException;

}
