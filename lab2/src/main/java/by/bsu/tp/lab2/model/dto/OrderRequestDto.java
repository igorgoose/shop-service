package by.bsu.tp.lab2.model.dto;

import by.bsu.tp.lab2.model.OrderRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public enum OrderRequestDto {
    ;

    private interface Id {
        Long getId();
    }

    private interface TotalPrice {
        double getTotalPrice();
    }

    private interface OrderStatus {
        String getOrderStatus();
    }

    private interface Bill {
        byte[] getBill();
    }

    private interface CreationDate {
        Timestamp getCreationDate();
    }

    private interface CustomerName {
        String getCustomerName();
    }

    private interface CustomerAddress {
        String getCustomerAddress();
    }

    private interface OrderPositions {
        List<OrderPositionDto.Response.Short> getOrderPositions();
    }

    public enum Response {
        ;

        @Data
        public static class Short implements Id, TotalPrice, OrderStatus, Bill, CreationDate, CustomerName, CustomerAddress {
            private Long id;
            private double totalPrice;
            private String orderStatus;
            private byte[] bill;
            private Timestamp creationDate;
            private String customerName;
            private String customerAddress;

            public Short(OrderRequest orderRequest) {
                id = orderRequest.getId();
                totalPrice = orderRequest.getTotalPrice();
                orderStatus = orderRequest.getOrderStatus();
                bill = orderRequest.getBill();
                creationDate = orderRequest.getCreationDate();
                customerName = orderRequest.getCustomerName();
                customerAddress = orderRequest.getCustomerAddress();
            }
        }

        @AllArgsConstructor
        @Data
        public static class Full implements Id, TotalPrice, OrderStatus, Bill, CreationDate, CustomerName, CustomerAddress, OrderPositions {
            private Long id;
            private double totalPrice;
            private String orderStatus;
            private byte[] bill;
            private Timestamp creationDate;
            private String customerName;
            private String customerAddress;
            private List<OrderPositionDto.Response.Short> orderPositions;

            public Full(OrderRequest orderRequest) {
                this(orderRequest.getId(), orderRequest.getTotalPrice(), orderRequest.getOrderStatus(), orderRequest.getBill(),
                        orderRequest.getCreationDate(), orderRequest.getCustomerName(), orderRequest.getCustomerAddress(),
                        orderRequest.getOrderPositions().stream().map(OrderPositionDto.Response.Short::new).collect(Collectors.toList()));
            }
        }
    }
}
