package by.bsu.tp.lab2.accountant.controller;

import by.bsu.tp.lab2.accountant.model.dto.OrderRequestDto;
import by.bsu.tp.lab2.accountant.service.BillService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class BillController {

    private final BillService billService;

    @PostMapping(value = "/bill", produces = "text/plain")
    public byte[] issueBill(@RequestBody OrderRequestDto.Response.Full orderRequest) {
        return billService.issueBill(orderRequest);
    }

}
