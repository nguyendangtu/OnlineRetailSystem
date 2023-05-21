package edu.miu.cs.cs544.customer.enums;

/**
 * @author : JOHNNGUYEN
 * @since : 5/21/2023, Sun
 **/
public enum CustomerAddressType {
    BILLING_ADDRESS("BILLING_ADDRESS"),
    SHIPPING_ADDRESS("BILLING_ADDRESS");

    private String type;

    CustomerAddressType(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }
}
