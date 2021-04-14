package by.bsu.tp.lab2.accountant.model.dto;

import by.bsu.tp.lab2.accountant.model.Product;
import lombok.Data;

public enum ProductDto {
    ;

    private interface Id {
        Long getId();
    }

    private interface Name {
        String getName();
    }

    private interface Price {
        double getPrice();
    }

    private interface SeasonsString {
        String getSeasonsString();
    }

    public enum Response {
        ;

        @Data
        public static class Short implements Id, Name, Price, SeasonsString {
            private final Long id;
            private final String name;
            private final double price;
            private final String seasonsString;

            public Short(Product product) {
                this.id = product.getId();
                this.name = product.getName();
                this.price = product.getPrice();
                this.seasonsString = product.getSeasonsString();
            }
        }
    }
}
