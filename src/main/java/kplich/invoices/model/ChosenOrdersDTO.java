package kplich.invoices.model;

import java.util.List;

public class ChosenOrdersDTO {
    private List<TransportOrder> chosenOrders;

    public ChosenOrdersDTO(List<TransportOrder> chosenOrders) {

        this.chosenOrders = chosenOrders;
    }

    public List<TransportOrder> getChosenOrders() {

        return chosenOrders;
    }

    public void setChosenOrders(List<TransportOrder> chosenOrders) {

        this.chosenOrders = chosenOrders;
    }
}
