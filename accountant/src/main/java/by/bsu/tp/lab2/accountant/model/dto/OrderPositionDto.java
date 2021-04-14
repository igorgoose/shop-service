package by.bsu.tp.lab2.accountant.model.dto;

import by.bsu.tp.lab2.accountant.model.OrderPosition;
import lombok.AllArgsConstructor;
import lombok.Data;

public enum OrderPositionDto {
    ;

    private interface Id {
        Long getId();
    }

    private interface Quantity {
        int getQuantity();
    }

    private interface PricePerProduct {
        double getPricePerProduct();
    }

    private interface ProductName {
        String getProductName();
    }


    public enum Response {
        ;

        @AllArgsConstructor
        @Data
        public static class Short implements Id, Quantity, PricePerProduct, ProductName {
            private Long id;
            private int quantity;
            private double pricePerProduct;
            private String productName;

            public Short(OrderPosition orderPosition) {
                this(orderPosition.getId(), orderPosition.getQuantity(), orderPosition.getPricePerProduct(), orderPosition.getProduct().getName());
            }
        }
    }

}
