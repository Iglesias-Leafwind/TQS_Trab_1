package com.api.qa;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class APITest {

    @Mock(lenient = true)
    private CityInfoCache cache = mock(CityInfoCache.class);
    private Info portoInf = mock(Info.class);
    private Info americaInf = mock(Info.class);

    @InjectMocks
    private API api;

    @BeforeEach
    public void setUp() throws IOException, JSONException {
        String porto = "porto";
        String THISISAMERICA = "washington";
        when(cache.insertInfo(porto)).thenReturn(true);
        when(cache.insertInfo(THISISAMERICA)).thenReturn(true);
        when(cache.getInfo(porto)).thenReturn(portoInf);
        when(cache.getInfo(THISISAMERICA)).thenReturn(americaInf);
        when(portoInf.getOverall_quality()).thenReturn(20);
        when(americaInf.getOverall_quality()).thenReturn(200);
    }
    @Test
    void searchPorto() {
        System.out.println(api.search("porto"));
        Info port = cache.getInfo("porto");
        System.out.println(port);
        assertThat(port.getOverall_quality()).isEqualTo(20);
    }
    @Test
    void searchAmerica() {
        System.out.println(api.search("washington"));
        Info americ = cache.getInfo("washington");
        System.out.println(americ);
        assertThat(americ.getOverall_quality()).isEqualTo(200);
    }
    @Test
    void searchRealPorto() throws IOException, JSONException {
        api.cacheInfo("Porto");
        System.out.println(api.search("Porto").getOverall_quality());
        assertThat(api.search("Porto").getOverall_quality()).isNotEqualTo(0);
    }
    @Test
    void searchRealAmerica() throws IOException, JSONException {
        api.cacheInfo("Washington");
        System.out.println(api.search("Washington").getOverall_quality());
        assertThat(api.search("Washington").getOverall_quality()).isNotEqualTo(0);

    }
}
