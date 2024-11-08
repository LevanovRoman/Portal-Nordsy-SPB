package com.myapp.portalnordsyspb.statisticPPU;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ApiService {

    private final RestTemplate restTemplate;

    private final String url = "http://dev1c/Design1CDO3/hs/online-ppu/getOnline_ppu";

    public List<MyDataDto> getDataFromExternalApi() {
//        String url = "http://dev1c/Design1CDO3/hs/online-ppu/getOnline_ppu";
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("erpagent", "123");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<MyDataDto>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<MyDataDto>>() {});
        return response.getBody();
//        return restTemplate.exchange(url, HttpMethod.GET, entity, MyDataDto.class).getBody();
    }

//    @Scheduled(cron = "0 51 11 * * *", zone = "Europe/Moscow")
    @Scheduled(cron = "0 */5 * * * * ") // Например, каждые пять минут
    public void getDataFrom1S(){
//        String url = "http://dev1c/Design1CDO3/hs/online-ppu/getOnline_ppu";
        try {

            System.out.println("Start request to 1C");
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth("erpagent", "123");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<List<MyDataDto>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<List<MyDataDto>>() {
                    });
            List<MyDataDto> result = response.getBody();
            System.out.println("result: " + result);
            assert result != null;
            System.out.println(result.getFirst().Zaregistrirovano());
        } catch (Exception e){
            System.err.println("Error with 1C");
        }
    }
}
