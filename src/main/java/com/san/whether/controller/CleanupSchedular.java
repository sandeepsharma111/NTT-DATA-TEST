package com.san.whether.controller;

import com.san.whether.ForecaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class CleanupSchedular {


    @Autowired
    ForecaseService forecaseService;

    @Value("${delete.records.older.than.days}")
    private int days;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteOlderRecords(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1 * days); //minus number would decrement the days
        System.out.println("Deleting older records");
        forecaseService.deleteByCreatedAtBefore(new java.sql.Date(cal.getTime().getTime()));
        System.out.println("Records deleted");
    }
}
