package by.bsu.tp.lab2.service;

import by.bsu.tp.lab2.model.OrderRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface BillService {
    void issueBill(OrderRequest orderRequest) throws IOException;
}
