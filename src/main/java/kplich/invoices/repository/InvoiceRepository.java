package kplich.invoices.repository;

import kplich.invoices.model.*;
import org.springframework.data.repository.*;

public interface InvoiceRepository extends CrudRepository<Invoice, String> {
}
