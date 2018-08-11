package kplich.invoices.repository;

import kplich.invoices.model.*;
import org.springframework.data.repository.*;

import java.util.*;

public interface OrderRepository extends CrudRepository<TransportOrder, Integer> {
    Iterable<TransportOrder> findByInvoice(Invoice invoice);
}
