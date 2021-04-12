package by.bsu.tp.lab2.accountant.service;

import by.bsu.tp.lab2.accountant.model.OrderRequest;

public interface BillService {
    byte[] issueBill(OrderRequest orderRequest);
}
