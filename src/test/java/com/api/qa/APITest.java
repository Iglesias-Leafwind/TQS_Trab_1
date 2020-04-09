package com.api.qa;

import com.api.qa.city.API;
import com.api.qa.city.CityInfoCache;
import com.api.qa.city.Info;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
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
        when(portoInf.getOverallQuality()).thenReturn(20);
        when(americaInf.getOverallQuality()).thenReturn(200);
    }
    @Test
    void searchPorto() throws IOException, JSONException {
        System.out.println(api.search("porto"));
        Info port = cache.getInfo("porto");
        System.out.println(port);
        assertThat(port.getOverallQuality()).isEqualTo(20);
    }
    @Test
    void searchAmerica() throws IOException, JSONException {
        System.out.println(api.search("washington"));
        Info americ = cache.getInfo("washington");
        System.out.println(americ);
        assertThat(americ.getOverallQuality()).isEqualTo(200);
    }
    @Test
    void searchRealPorto() throws IOException, JSONException {
        api.cacheInfo("Porto");
        System.out.println(api.search("Porto").getOverallQuality());
        assertThat(api.search("Porto").getOverallQuality()).isNotEqualTo(0);
    }
    @Test
    void searchRealAmerica() throws IOException, JSONException {
        api.cacheInfo("Washington");
        System.out.println(api.search("Washington").getOverallQuality());
        assertThat(api.search("Washington").getOverallQuality()).isNotEqualTo(0);

    }
}
