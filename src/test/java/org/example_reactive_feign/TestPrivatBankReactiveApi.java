package org.example_reactive_feign;

import org.example_feign.PrivatBankApiClient;
import org.example_feign.dto.ExchangeRatesResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reactivefeign.spring.config.EnableReactiveFeignClients;
import reactivefeign.spring.config.ReactiveFeignAutoConfiguration;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableReactiveFeignClients
@EnableFeignClients
@ImportAutoConfiguration({ReactiveFeignAutoConfiguration.class, FeignAutoConfiguration.class})
public class TestPrivatBankReactiveApi {

    @Autowired
    public PrivatBankApiReactiveClient clientRx;

    @Autowired
    public PrivatBankApiClient client;

    @Test
    public void performanceTest(){
        int executionTimes = 10;
        List<ExchangeRatesResponse> listFromClient = new ArrayList<>();
        List<Flux<ExchangeRatesResponse>> listFromRxClient= new ArrayList<>();
        Date onStart = new Date();
        for (int i = 0; i< executionTimes; i++){
            listFromClient.add(client.getExchangeRatesPBAndNB("01.%d.2014".formatted(i)));
        }
        long spentTime = new Date().getTime() - onStart.getTime();
        onStart = new Date();
        for (int i = 0; i< executionTimes; i++){
            listFromRxClient.add(clientRx.getExchangeRatesPBAndNB("01.%d.2014".formatted(i)));
        }
        long spentTimeRx = new Date().getTime() - onStart.getTime();
        System.out.println("spentTime: %d\nspentTimeRx: %d".formatted(spentTime, spentTimeRx));
    }

}
