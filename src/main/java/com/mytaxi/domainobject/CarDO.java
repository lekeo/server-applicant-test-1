package com.mytaxi.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.mytaxi.domainvalue.EngineType;

@Entity
@Table(name = "car", uniqueConstraints = @UniqueConstraint(name = "uc_license_plate", columnNames = {"license_plate"}))
public class CarDO
{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column
    private String manufacturer = "Unknown Manufacturer";

    @Column
    @NotNull(message = "Car should have a model name from related manufacturer")
    private String model;

    //Example license plates in Turkey(FORMAT: XXYYZZZZ or XXYYZZZ) --> 34KY1120, 01LY1201, 06AB230 etc..
    @Column(nullable = false, name = "license_plate")
    @NotNull(message = "License Plate can not be empty!")
    @Size(min = 7, max = 8)
    private String licensePlate;

    @Column(nullable = false, name = "seat_count")
    @Min(2)
    private Integer seatCount = 4;

    @Column(nullable = false)
    private Boolean convertible = Boolean.FALSE;

    @Column
    @Min(1)
    @Max(5)
    private Integer rating;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Each car has an engine type!")
    private EngineType engineType;

    @OneToOne(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private DriverDO currentDriver;

    @Column
    private Boolean carInUse = Boolean.FALSE;

    @Column(nullable = false)
    private Boolean deleted = Boolean.FALSE;


    CarDO()
    {}


    public CarDO(String model, String licensePlate, EngineType engineType, String manufacturer, Integer seatCount, Boolean convertible)
    {
        this.model = model;
        this.licensePlate = licensePlate;
        this.engineType = engineType;

        if (null != manufacturer)
        {
            this.manufacturer = manufacturer;
        }

        if (null != seatCount)
        {
            this.seatCount = seatCount;
        }

        if (null != convertible)
        {
            this.convertible = convertible;
        }
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public ZonedDateTime getDateCreated()
    {
        return dateCreated;
    }


    public void setDateCreated(ZonedDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
    }


    public String getManufacturer()
    {
        return manufacturer;
    }


    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }


    public String getModel()
    {
        return model;
    }


    public void setModel(String model)
    {
        this.model = model;
    }


    public String getLicensePlate()
    {
        return licensePlate;
    }


    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }


    public Integer getSeatCount()
    {
        return seatCount;
    }


    public void setSeatCount(Integer seatCount)
    {
        this.seatCount = seatCount;
    }


    public Boolean getConvertible()
    {
        return convertible;
    }


    public void setConvertible(Boolean convertible)
    {
        this.convertible = convertible;
    }


    public Integer getRating()
    {
        return rating;
    }


    public void setRating(Integer rating)
    {
        this.rating = rating;
    }


    public EngineType getEngineType()
    {
        return engineType;
    }


    public void setEngineType(EngineType engineType)
    {
        this.engineType = engineType;
    }


    public DriverDO getCurrentDriver()
    {
        return currentDriver;
    }


    public void setCurrentDriver(DriverDO currentDriver)
    {
        this.currentDriver = currentDriver;
    }


    public Boolean getCarInUse()
    {
        return this.carInUse;
    }


    public void setCarInUse(Boolean carInUse)
    {
        this.carInUse = carInUse;
    }


    public Boolean getDeleted()
    {
        return deleted;
    }


    public void setDeleted(Boolean deleted)
    {
        this.deleted = deleted;
    }

}
