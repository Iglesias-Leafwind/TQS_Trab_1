package com.kys.qa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InfoTest {

    @Test
    void createCity() {
        Info city = new Info("name","2020-04-08//16:57",0,1.1,2.1,3.1,4.0);
        assertThat(city.getName()).isEqualTo("name");
    }

}
