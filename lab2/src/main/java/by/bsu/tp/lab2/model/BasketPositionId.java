package by.bsu.tp.lab2.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class BasketPositionId implements Serializable {
    private Long userId;
    private Long productId;

}
