package kplich.invoices.model;

import javax.persistence.*;
import java.math.*;

@Entity
public class TransportOrder {
	@Id
	private int number;

	private String loadingLocation;
	private String unloadingLocation;
	private BigDecimal value;

	@ManyToOne
	private Invoice invoice;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getLoadingLocation() {
		return loadingLocation;
	}

	public void setLoadingLocation(String loadingLocation) {
		this.loadingLocation = loadingLocation;
	}

	public String getUnloadingLocation() {
		return unloadingLocation;
	}

	public void setUnloadingLocation(String unloadingLocation) {
		this.unloadingLocation = unloadingLocation;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
