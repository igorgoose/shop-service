package by.bsu.tp.lab2.accountant.service;

import by.bsu.tp.lab2.accountant.model.dto.OrderRequestDto;

public interface BillService {
    byte[] issueBill(OrderRequestDto.Response.Full orderRequest);
}
