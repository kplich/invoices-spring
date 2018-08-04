package kplich.invoices.repository;

import kplich.invoices.model.*;
import org.springframework.data.repository.*;

public interface OrderRepository extends CrudRepository<TransportOrder, Integer> {
}
