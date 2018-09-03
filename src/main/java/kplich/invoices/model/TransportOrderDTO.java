package kplich.invoices.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TransportOrderDTO {

    private static final BigDecimal TAX = new BigDecimal("0.23");

    private TransportOrder order;
    private BigDecimal taxValue;
    private BigDecimal bruttoValue;

    public TransportOrderDTO(TransportOrder order) {
        this.order = order;
        this.taxValue = order.getValue().multiply(TAX).setScale(2, RoundingMode.HALF_UP);
        this.bruttoValue = order.getValue().add(taxValue);
    }

    public TransportOrder getOrder() {

        return order;
    }

    public BigDecimal getTaxValue() {

        return taxValue;
    }

    public BigDecimal getBruttoValue() {

        return bruttoValue;
    }
}
