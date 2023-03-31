package pl.piomin.service.common.message;

import java.time.LocalDate;

public class Shipment {

	private Integer id;
	private ShipmentType type;
	private LocalDate date;
	private int price;

	public Shipment() {

	}

	public Shipment(ShipmentType type) {
		super();
		this.type = type;
	}

	public Shipment(ShipmentType type, LocalDate date, int price) {
		this.type = type;
		this.date = date;
		this.price = price;
	}

	public Shipment(Integer id, ShipmentType type, LocalDate date, int price) {
		this.id = id;
		this.type = type;
		this.date = date;
		this.price = price;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Shipment{" +
				"id=" + id +
				", type=" + type +
				", date=" + date +
				", price=" + price +
				'}';
	}
}
