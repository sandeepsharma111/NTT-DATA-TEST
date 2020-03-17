package com.san.whether.controller;
import com.google.gson.*;
import com.san.whether.ForecaseService;
import com.san.whether.model.Forecast;
import com.san.whether.model.ForecastTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Type;
import java.time.Instant;

@Controller
public class WeatherController {

    @Value("${darkskyapi.url}")
    private String darkSkyUrl;



    @Autowired
    ForecaseService forecaseService;

    //Opens entry form on start
    @GetMapping(value = "/")
    public String home() {
        return "EntryForm";
    }

    //Receives default post from entry form
    @RequestMapping(value ="/Forecast", method = RequestMethod.POST)
    public String selectedLocationInput(@RequestParam("locationTextField") String inputAddress, RedirectAttributes redir, Model model){

        //If entered location dosnt exist redirect back to home

        Forecast forecast = null;

        // Forecast object in database
        String city=inputAddress.split(",")[2];
        ForecastTable forecastTable = forecaseService.get(inputAddress.split(",")[0]+inputAddress.split(",")[1]);
        if(forecastTable != null){
            System.out.println("Forecast found in database");
            forecast = forecastTable.getForecast();
        }else{
            // Call darksky api
            final String uri = darkSkyUrl +inputAddress.split(",")[0]+","+inputAddress.split(",")[1];

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
            System.out.println(result);

            GsonBuilder builder = new GsonBuilder();

            // Register an adapter to manage the date types as long values
            builder.registerTypeAdapter(Instant.class, new JsonDeserializer() {
                public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return Instant.ofEpochMilli(json.getAsJsonPrimitive().getAsLong());
                }
            });

            Gson gson = builder.create();

            forecast = gson.fromJson(result, Forecast.class);
            System.out.println(forecast);

            ForecastTable record = new ForecastTable();
            record.setLocation(inputAddress);
            record.setForecast(forecast);
            ForecastTable savedRec = forecaseService.save(record);
            System.out.println("Record inserted to db");
        }

        try{

            model.addAttribute("weatherObject",forecast.getDaily().getData());
            model.addAttribute("weatherLocation",city);
            model.addAttribute("currently",forecast.getCurrently());
        } catch (Exception ex){
            ex.printStackTrace();
            redir.addFlashAttribute("Error","FORECAST COULD NOT BE RETRIEVED.");
            return "redirect:/";
        }
        return "Forecast";
    }

}