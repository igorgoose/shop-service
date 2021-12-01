package by.bsu.tp.lab2.model.dto;

import by.bsu.tp.lab2.model.Product;
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

    private interface Description {
        String getDescription();
    }

    private interface SeasonsString {
        String getSeasonsString();
    }

    public enum Response {
        ;

        @Data
        public static class Short implements Id, Name, Price, SeasonsString, Description {
            private final Long id;
            private final String name;
            private final double price;
            private final String description;
            private final String seasonsString;

            public Short(Product product) {
                this.id = product.getId();
                this.name = product.getName();
                this.price = product.getPrice();
                this.description = product.getDescription();
                this.seasonsString = product.getSeasonsString();
            }
        }
    }
}
