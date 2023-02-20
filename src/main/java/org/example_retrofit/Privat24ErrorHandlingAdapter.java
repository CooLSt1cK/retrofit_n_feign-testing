package org.example_retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.example_feign.dto.ExchangeRateDTO;
import org.example_feign.dto.ExchangeRatesResponse;
import org.example_retrofit.utils.ErrorHandlingCallAdapterFactory;
import org.example_retrofit.utils.MyCall;
import org.example_retrofit.utils.MyCallback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.stream.Collectors;

public final class Privat24ErrorHandlingAdapter {

    public static void main(String... args) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit =
                new Retrofit.Builder()
                        .baseUrl("https://api.privatbank.ua")
                        .client(client)
                        .addCallAdapterFactory(new ErrorHandlingCallAdapterFactory())
                        .addConverterFactory(JacksonConverterFactory.create())
                        .build();

        BasicApi service = retrofit.create(BasicApi.class);
        MyCall<ExchangeRatesResponse> call = service.getExchangeRatesTest("01.12.2014");

        call.enqueue(
                new MyCallback<ExchangeRatesResponse>() {
                    @Override
                    public void success(Response<ExchangeRatesResponse> response) {
                        System.out.println(
                                "SUCCESS! " + response.body().exchangeRate.stream().map(ExchangeRateDTO::toString).collect(Collectors.joining("\n")));
                    }

                    @Override
                    public void unauthenticated(Response<?> response) {
                        System.out.println("UNAUTHENTICATED");
                    }

                    @Override
                    public void clientError(Response<?> response) {
                        System.out.println("CLIENT ERROR " + response.code() + " " + response.message());
                    }

                    @Override
                    public void serverError(Response<?> response) {
                        System.out.println("SERVER ERROR " + response.code() + " " + response.message());
                    }

                    @Override
                    public void networkError(IOException e) {
                        System.err.println("NETWORK ERROR " + e.getMessage());
                    }

                    @Override
                    public void unexpectedError(Throwable t) {
                        System.err.println("FATAL ERROR " + t.getMessage());
                    }
                });
    }
}
