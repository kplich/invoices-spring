package kplich.invoices.repository;

import kplich.invoices.model.*;
import org.springframework.data.repository.*;

import java.util.*;

public interface OrderRepository extends CrudRepository<TransportOrder, Integer> {
	List<TransportOrder> findByInvoice(Invoice invoice);
}
