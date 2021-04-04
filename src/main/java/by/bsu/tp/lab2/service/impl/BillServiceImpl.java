package by.bsu.tp.lab2.service.impl;

import by.bsu.tp.lab2.model.OrderPosition;
import by.bsu.tp.lab2.model.OrderRequest;
import by.bsu.tp.lab2.service.BillService;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

@Service
public class BillServiceImpl implements BillService {

    @Override
    public void issueBill(OrderRequest orderRequest) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("Issuer: TP_LAB2 Inc.\n")
                .append("Issued At: ").append(new Timestamp(System.currentTimeMillis()).toString()).append("\n")
                .append("Order created at: ").append(orderRequest.getCreationDate().toString()).append("\n")
                .append("Customer: ").append(orderRequest.getCustomerName()).append("\n")
                .append("Delivery to: ").append(orderRequest.getCustomerAddress()).append("\n")
        .append("Order Positions : Quantity : Price apiece\n");
        for (OrderPosition orderPosition : orderRequest.getOrderPositions()) {
            stringBuilder.append(orderPosition.getProduct().getName())
                    .append(" : ")
                    .append(orderPosition.getQuantity())
                    .append(" : ")
                    .append(orderPosition.getPricePerProduct())
                    .append("\n");
        }
        stringBuilder.append("Total price: ").append(orderRequest.getTotalPrice()).append("\n");
        orderRequest.setBill(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
    }
}
