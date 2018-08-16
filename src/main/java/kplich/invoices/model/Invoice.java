package kplich.invoices.model;

import org.springframework.format.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.*;

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

	@Override
	public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(invoiceId)
                .append(", ")
                .append(issueDate.toString())
                .append(", ")
                .append(saleDate.toString());

        return stringBuilder.toString();
	}
}
