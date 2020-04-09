package com.api.qa.city;


import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RestController
public class QaController {

    @Autowired
    private API myService;

    @RequestMapping("/cities/{name}")
    public String getInfo(@PathVariable String name) throws IOException, JSONException {
        Info cityInfo;
        if(name == null){
            cityInfo = new Info();
        } else {
            cityInfo = myService.search(name);
        }
        if(cityInfo.getError()){
            return "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>"+ name +"</title>\n" +
                    "</head>\n" +
                    "<body style=\"background-color:Orange;\">\n" +
                    "    <div>\n" +
                    "        <center><h1>"+ cityInfo.getName() +"</h1></center>\n" +
                    "        <center><p>I couldn't find "+ name+"</p></center>" +
                    "        <center><p> Press the button to go back to the search page! </p></center>" +
                    "       <center><form action=\"/\">\n" +
                    "            <input type=\"submit\" value=\"Go back\" class=\"btn btn-warning\" style=\"background-color: orange,font-size: 24px\"/>\n" +
                    "        </form></center>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>";
        }
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>"+ name +"</title>\n" +
                "</head>\n" +
                "<body style=\"background-color:Orange;\">\n" +
                "    <div>\n" +
                "        <center><h1>"+ cityInfo.getName() +"</h1></center>\n" +
                "    </div>\n" +
                "    <div>\n" +
                "        <center>\n" +
                "            <p>Time: "+ cityInfo.getTime() +"</p>\n" +
                "            <p>Quality: "+ cityInfo.getOverallQuality() +"</p>\n" +
                "            <p>Temperature: "+cityInfo.getTemperature() +"</p>\n" +
                "            <p>Wind: "+cityInfo.getWind()+"</p>\n" +
                "            <p>Humidity: "+cityInfo.getHumidity()+"</p>\n" +
                "            <p>Pressure: "+cityInfo.getPressure()+"</p>\n" +
                "        </center>\n" +
                "    </div>\n" +
                "    <div>\n"+
                "       <center><p> Press the button to go back to the search page! </p></center>" +
                "       <center><form action=\"/\">\n" +
                "            <input type=\"submit\" value=\"Go back\" class=\"btn btn-warning\" style=\"background-color: orange, font-size: 24px\"/>\n" +
                "       </form></center>\n" +
                "    </div>" +
                "</body>\n" +
                "</html>";
    }
}
