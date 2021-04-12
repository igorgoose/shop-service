package by.bsu.tp.lab2.service.impl;

import by.bsu.tp.lab2.model.OrderRequest;
import by.bsu.tp.lab2.service.BillService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class BillServiceImpl implements BillService {

    private final String accountantUrl;
    private static final String ACCOUNTANT_URL_PROPERTY = "module.accountant.url";
    private static final String BILL_ENDPOINT_URL = "/bill";

    public BillServiceImpl(Environment environment) {
        accountantUrl = environment.getProperty(ACCOUNTANT_URL_PROPERTY);
    }

    @Override
    public void issueBill(OrderRequest orderRequest) throws IOException {
        String url = accountantUrl + BILL_ENDPOINT_URL;
        MediaType mediaType = MediaType.parse("application/json");
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody requestBody = RequestBody.create(new ObjectMapper().writeValueAsString(orderRequest), mediaType);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", requestBody)
                .build();
        Response response = client.newCall(request).execute();
        byte[] bill = Objects.requireNonNull(response.body()).bytes();
        orderRequest.setBill(bill);
    }
}
