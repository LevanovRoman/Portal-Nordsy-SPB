package com.myapp.portalnordsyspb.statisticPPU;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    public List<MyDataDto> getDataFromExternalApi() {
        String url = "http://dev1c/Design1CDO3/hs/online-ppu/getOnline_ppu";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("erpagent", "123");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<MyDataDto>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<MyDataDto>>() {});
        return response.getBody();
//        return restTemplate.exchange(url, HttpMethod.GET, entity, MyDataDto.class).getBody();
    }

    @Scheduled(cron = "0 51 11 * * *")
    public void getDataFrom1S(){
        String url = "http://dev1c/Design1CDO3/hs/online-ppu/getOnline_ppu";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("erpagent", "123");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<MyDataDto>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<MyDataDto>>() {});
        List<MyDataDto> result = response.getBody();
        assert result != null;
        System.out.println(result.getFirst().Zaregistrirovano());
    }
}
