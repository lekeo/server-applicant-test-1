package com.mytaxi.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainvalue.EngineType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{
    @JsonIgnore
    private Long id;

    @NotNull(message = "Car should have a model name from related manufacturer")
    private String model;

    private String manufacturer;

    @NotNull(message = "License Plate can not be empty!")
    private String licensePlate;

    private Integer seatCount;

    private Boolean convertible;

    //    private Integer rating;

    @NotNull(message = "Each car has an engine type!")
    private EngineType engineType;


    private CarDTO()
    {}


    private CarDTO(Long id, String model, String manufacturer, String licensePlate, Integer seatCount, Boolean convertible, EngineType engineType)
    {
        super();
        this.id = id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        //        this.rating = rating;
        this.engineType = engineType;
    }


    public static CarDTOBuilder newBuilder()
    {
        return new CarDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


    public String getModel()
    {
        return model;
    }


    public String getManufacturer()
    {
        return manufacturer;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public Integer getSeatCount()
    {
        return seatCount;
    }


    public Boolean getConvertible()
    {
        return convertible;
    }


    //    public Integer getRating()
    //    {
    //        return rating;
    //    }

    public EngineType getEngineType()
    {
        return engineType;
    }

    public static class CarDTOBuilder
    {
        private Long id;
        private String model;
        private String manufacturer;
        private String licensePlate;
        private Integer seatCount;
        private Boolean convertible;;
        //        private Integer rating;
        private EngineType engineType;


        public CarDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public CarDTOBuilder setModel(String model)
        {
            this.model = model;
            return this;
        }


        public CarDTOBuilder setManufacturer(String manufacturer)
        {
            this.manufacturer = manufacturer;
            return this;
        }


        public CarDTOBuilder setLicensePlate(String licensePlate)
        {
            this.licensePlate = licensePlate;
            return this;
        }


        public CarDTOBuilder setSeatCount(Integer seatCount)
        {
            this.seatCount = seatCount;
            return this;
        }


        public CarDTOBuilder setConvertible(Boolean convertible)
        {
            this.convertible = convertible;
            return this;
        }


        //        public CarDTOBuilder setRating(Integer rating)
        //        {
        //            this.rating = rating;
        //            return this;
        //        }

        public CarDTOBuilder setEngineType(EngineType engineType)
        {
            this.engineType = engineType;
            return this;
        }


        public CarDTO createCarDTO()
        {
            return new CarDTO(id, model, manufacturer, licensePlate, seatCount, convertible, engineType);
        }
    }

}
