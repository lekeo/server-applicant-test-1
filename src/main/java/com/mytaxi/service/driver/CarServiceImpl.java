package com.mytaxi.service.driver;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

@Service
public class CarServiceImpl implements CarService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarRepository carRepository;


    @Override
    public List<CarDO> findAll()
    {
        return (List<CarDO>) carRepository.findAll();
    }


    @Override
    public List<CarDO> findByParams(String manufacturer, String model, String licensePlate, EngineType engineType)
    {
        return carRepository.findByParams(manufacturer, model, licensePlate, engineType);
    }


    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException
    {
        CarDO car;

        try
        {
            car = carRepository.save(carDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to car creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }

        return car;

    }


    @Override
    public CarDO find(Long carId) throws EntityNotFoundException
    {
        return findCarById(carId);
    }


    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException
    {
        CarDO carDO = findCarById(carId);
        // Check any online drivers using the car
        carDO.setDeleted(Boolean.TRUE);
    }


    @Override
    @Transactional
    public void rateCar(Long carId, Integer rating) throws EntityNotFoundException
    {
        CarDO car = findCarById(carId);
        car.setRating(rating);

    }


    private CarDO findCarById(Long carId) throws EntityNotFoundException
    {
        CarDO carDO = carRepository.findOne(carId);
        if (null == carDO)
        {
            throw new EntityNotFoundException("No car entity is found with id: " + carId);
        }

        return carDO;
    }

}
