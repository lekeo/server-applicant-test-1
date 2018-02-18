package com.mytaxi.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;

public class CarMapper
{
    public static CarDO makeCarDO(CarDTO carDTO)
    {
        return new CarDO(
            carDTO.getModel(), carDTO.getLicensePlate(), carDTO.getEngineType(), carDTO.getManufacturer(),
            carDTO.getSeatCount(), carDTO.getConvertible());
    }


    public static CarDTO makeCarDTO(CarDO carDO)
    {
        CarDTO.CarDTOBuilder carDTOBuilder =
            CarDTO
                .newBuilder()
                .setId(carDO.getId())
                .setModel(carDO.getModel())
                .setLicensePlate(carDO.getLicensePlate())
                .setEngineType(carDO.getEngineType())
                .setManufacturer(carDO.getManufacturer())
                .setConvertible(carDO.getConvertible())
                //                .setRating(carDO.getRating())
                .setSeatCount(carDO.getSeatCount());

        return carDTOBuilder.createCarDTO();
    }


    public static List<CarDTO> makeCarDTOList(Collection<CarDO> cars)
    {
        return cars
            .stream()
            .map(CarMapper::makeCarDTO)
            .collect(Collectors.toList());
    }
}
