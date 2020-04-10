package com.api.qa.city;


import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
@Controller
public class QaController {

    @Autowired
    private API myService;

    @RequestMapping("/cities/{name}")
    public String getInfo(@PathVariable String name, Model model) throws IOException, JSONException {
        Info cityInfo;
        model.addAttribute("name",name);
        if(name == null){
            cityInfo = new Info();
            model.addAttribute("cityName",cityInfo.getName());
            model.addAttribute("name",name);
        } else {
            cityInfo = myService.search(name);
            model.addAttribute("cityName",cityInfo.getName());
            model.addAttribute("cityTime",cityInfo.getTime());
            model.addAttribute("cityQuality",cityInfo.getOverallQuality());
            model.addAttribute("cityTemperature",cityInfo.getTemperature());
            model.addAttribute("cityWind",cityInfo.getWind());
            model.addAttribute("cityHumidity",cityInfo.getHumidity());
            model.addAttribute("cityPressure",cityInfo.getPressure());
        }

        if(cityInfo.getError()){
            return "geterror.html";
        }
        return "getcity.html";
    }
}
