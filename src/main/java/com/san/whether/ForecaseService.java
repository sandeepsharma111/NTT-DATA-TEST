package com.san.whether;

import com.san.whether.repository.ForecastRepository;
import com.san.whether.model.ForecastTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class ForecaseService {

    @Autowired
    ForecastRepository forecastRepository;

    public ForecastTable get(String location){
        return forecastRepository.findByLocation(location);
    }

    public void deleteByCreatedAtBefore(Date date){
        forecastRepository.deleteByCreatedDateBefore(date);
    }

    public ForecastTable save(ForecastTable table){
        return forecastRepository.save(table);
    }


}
