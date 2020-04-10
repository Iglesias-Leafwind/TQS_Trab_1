package com.api.qa;

import com.api.qa.city.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InfoTest {

    private Info city;
    @BeforeEach
    void setUp(){
        city = new Info("name","2020-04-08//16:57",0,1.1,2.1,3.1,4.0);
    }
    @Test
    void createCity() {
        city = new Info("name","2020-04-08//16:57",0,1.1,2.1,3.1,4.0);
        assertThat(city.getError()).isEqualTo(false);
    }
    @Test
    void createInvalidCity() {
        Info city = new Info();
        assertThat(city.getError()).isEqualTo(true);
    }
    @Test
    void getName() {
        assertThat(city.getName()).isEqualTo("name");
    }
    @Test
    void getTime() {
        assertThat(city.getTime()).isEqualTo("2020-04-08//16:57");
    }
    @Test
    void getQuality() {
        assertThat(city.getOverallQuality()).isEqualTo(0);
    }
    @Test
    void getHumidity() {
        assertThat(city.getHumidity()).isEqualTo(1.1);
    }
    @Test
    void getPressure() {
        assertThat(city.getPressure()).isEqualTo(2.1);
    }
    @Test
    void getTemperature() {
        assertThat(city.getTemperature()).isEqualTo(3.1);
    }
    @Test
    void getWind() {
        assertThat(city.getWind()).isEqualTo(4.0);
    }

}
