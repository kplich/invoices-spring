package kplich.invoices.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.*;

@Entity
public class TransportOrder {

	@Id
	private int number;

	@NotNull
	private String loadingLocation;

	@NotNull
	private String unloadingLocation;

	@NotNull
	private BigDecimal value;

    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
    private Invoice invoice;

	//for JPA or sth?
	//TODO: immutability clashes with Hibernate and testing?
	public TransportOrder() {
	}

	public TransportOrder(int number, String loadingLocation, String unloadingLocation, BigDecimal value) {
		this.number = number;
		this.loadingLocation = loadingLocation;
		this.unloadingLocation = unloadingLocation;
		this.value = value;
	}

	public int getNumber() {
		return number;
	}

	public String getLoadingLocation() {
		return loadingLocation;
	}

	public String getUnloadingLocation() {
		return unloadingLocation;
	}

	public BigDecimal getValue() {
		return value;
	}

    public Invoice getInvoice() {
        return invoice;
    }

    public void setNumber(int number) {
		this.number = number;
	}

	public void setLoadingLocation(String loadingLocation) {
		this.loadingLocation = loadingLocation;
	}

	public void setUnloadingLocation(String unloadingLocation) {
		this.unloadingLocation = unloadingLocation;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @Override
	public boolean equals(Object obj) {
		boolean result = false;

		if(this != obj) {
			if (obj instanceof TransportOrder) {
				TransportOrder compared = (TransportOrder) obj;

				result = (this.number == compared.number &&
						  this.loadingLocation.equals(compared.loadingLocation) &&
						  this.unloadingLocation.equals(compared.unloadingLocation) &&
						  this.value.equals(compared.value) &&
                          this.invoice.equals(compared.invoice));
			}
		}
		else {
			result = true;
		}

		return result;
	}

	@Override
	public int hashCode() {
		int result = Integer.hashCode(number);

		result = 31 * result + loadingLocation.hashCode();
		result = 31 * result + unloadingLocation.hashCode();
		result = 31 * result + value.hashCode();
		result = 31 * result + invoice.hashCode();

		return result;
	}

	@Override
	public String toString() {
		return "number: " + number + ",\nloading location: " + loadingLocation + ",\nunloading location: " +
			   unloadingLocation + ",\nvalue: " + value.toString();
	}
}
