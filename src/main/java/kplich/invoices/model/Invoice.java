package kplich.invoices.model;

import org.springframework.format.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;
import java.util.List;

@Entity
public class Invoice {
	@Id
	private String invoiceId;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate issueDate;

	@NotNull
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private LocalDate saleDate;

	@NotNull
    @OneToMany
    private List<TransportOrder> orders;

	public Invoice() {
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDate getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDate saleDate) {
		this.saleDate = saleDate;
	}

    public List<TransportOrder> getOrders() {

        return orders;
    }

    public void setOrders(List<TransportOrder> orders) {

        this.orders = orders;
    }

	@Override
	public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(invoiceId)
                .append(", ")
                .append(issueDate.toString())
                .append(", ")
                .append(saleDate.toString());

        for(TransportOrder order: orders) {
            stringBuilder.append(order.getNumber());
            stringBuilder.append(", ");
        }

        return stringBuilder.toString();
	}
}
