package org.example_feign;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import org.example_feign.feign.PrivatBankApiClient;
import org.example_feign.feign.PrivatBankApiReactiveClient;
import org.example_feign.dto.ExchangeRatesResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reactivefeign.spring.config.EnableReactiveFeignClients;
import reactivefeign.spring.config.ReactiveFeignAutoConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableFeignClients
@EnableReactiveFeignClients
@ImportAutoConfiguration({ReactiveFeignAutoConfiguration.class, FeignAutoConfiguration.class})
public class TestPrivatBankReactiveApi {

    @Autowired
    public PrivatBankApiClient client;

    @Autowired
    public PrivatBankApiReactiveClient clientRx;

    @Test
    public void performanceTest() {
        int executionTimes = 9;
        List<ExchangeRatesResponse> listFromClient = new ArrayList<>();
        List<Observable<ExchangeRatesResponse>> listObservableFromRxClient = new ArrayList<>();

        Date onStart = new Date();
        for (int i = 1; i <= executionTimes; i++) {
            listFromClient.add(client.getExchangeRatesPBAndNB("01.0%d.2015".formatted(i)));
        }
        long spentTime = new Date().getTime() - onStart.getTime();

        onStart = new Date();
        for (int i = 1; i <= executionTimes; i++) {
            listObservableFromRxClient.add(clientRx.getExchangeRatesPBAndNB("01.0%d.2015".formatted(i)));
        }
        List<ExchangeRatesResponse> listFromRxClient = listObservableFromRxClient.stream().map(Observable::blockingFirst).toList();
        long spentTimeRx = new Date().getTime() - onStart.getTime();

        System.out.println("spentTime: %d\nspentTimeRx: %d".formatted(spentTime, spentTimeRx));
        Assert.assertEquals(listFromClient, listFromRxClient);
    }

}
