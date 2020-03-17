package com.san.whether.repository;

import com.san.whether.model.ForecastTable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public interface ForecastRepository  extends MongoRepository<ForecastTable, String> {

    // Find record by location name
    ForecastTable findByLocation(String location);

    // To delete records prior by supplied date
    public void deleteByCreatedDateBefore(Date createdDate);

}
