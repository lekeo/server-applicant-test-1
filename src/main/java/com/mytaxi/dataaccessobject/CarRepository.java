package com.mytaxi.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.EngineType;

public interface CarRepository extends CrudRepository<CarDO, Long>
{
    @Query("select c from #{#entityName} c"
        + " where ((?1 IS NULL) OR (?1 IS NOT NULL AND c.manufacturer = ?1)) AND ((?2 IS NULL) OR (?2 IS NOT NULL AND c.model = ?2))"
        + " AND ((?3 IS NULL) OR (?3 IS NOT NULL AND c.licensePlate = ?3)) AND ((?4 IS NULL) OR (?4 IS NOT NULL AND c.engineType = ?4))")
    List<CarDO> findByParams(String manufacturer, String model, String licensePlate, EngineType engineType);
}
