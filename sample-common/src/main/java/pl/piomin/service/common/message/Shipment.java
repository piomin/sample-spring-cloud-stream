package pl.piomin.service.common.message;

import java.time.LocalDate;

public class Shipment {

	private Integer id;
	private ShipmentType type;
	private LocalDate date;

	public Shipment() {

	}

	public Shipment(ShipmentType type, LocalDate date) {
		this.type = type;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ShipmentType getType() {
		return type;
	}

	public void setType(ShipmentType type) {
		this.type = type;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
