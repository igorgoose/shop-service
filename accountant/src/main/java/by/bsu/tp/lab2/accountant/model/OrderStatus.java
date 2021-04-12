package by.bsu.tp.lab2.accountant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    CREATED("created", "Created"),
    PREPARING("preparing", "Preparing"),
    SENT_TO_CUSTOMER("sent-to-customer", "Sent to customer"),
    DECLINED("declined", "Declined");

    private final String internalName;
    private final String value;

    public static String getValueByInternalName(String internalName) {
        return Arrays.stream(values())
                .filter(orderStatus -> orderStatus.internalName.equals(internalName))
                .map(OrderStatus::getValue)
                .findAny().orElseThrow(() -> new IllegalArgumentException("No order status " + internalName));
    }
}
