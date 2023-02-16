package org.example_feign;

import io.reactivex.observers.TestObserver;
import org.example_feign.dto.ExchangeRatesResponse;
import org.example_feign.feign.PrivatBankApiClient;
import org.example_feign.feign.PrivatBankApiReactiveClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.LinkedList;
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

    @BeforeEach
    public void setUp() {
        //send two requests to equalize response time of future requests
        client.getExchangeRatesPBAndNB("01.12.2014");
        client.getExchangeRatesPBAndNB("01.12.2014");
    }

    @Test
    public void performanceTest() {
        int executionTimes = 2;

        Date onStart = new Date();
        List<ExchangeRatesResponse> listFromClient = sendRequestNTimesViaClient(executionTimes);
        long spentTime = new Date().getTime() - onStart.getTime();

        onStart = new Date();
        List<ExchangeRatesResponse> listFromRxClient = sendRequestNTimesViaReactiveClient(executionTimes);
        long spentTimeRx = new Date().getTime() - onStart.getTime();

        System.out.println("spentTime: %d\nspentTimeRx: %d".formatted(spentTime, spentTimeRx));
        Assert.assertEquals(listFromClient, listFromRxClient);
    }

    private List<ExchangeRatesResponse> sendRequestNTimesViaClient(int n) {
        List<ExchangeRatesResponse> responses = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            responses.add(client.getExchangeRatesPBAndNB("01.0%d.2015".formatted(i)));
        }
        return responses;
    }

    private List<ExchangeRatesResponse> sendRequestNTimesViaReactiveClient(int n) {
        LinkedList<TestObserver<ExchangeRatesResponse>> testObserverList = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            testObserverList.add(new TestObserver<>());
            clientRx.getExchangeRatesPBAndNB("01.0%d.2015".formatted(i)).subscribe(testObserverList.getLast());
        }
        return testObserverList.stream().map(observer -> {
            try {
                return observer.await()
                        .assertComplete()
                        .assertNoErrors()
                        .values().get(0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

}
