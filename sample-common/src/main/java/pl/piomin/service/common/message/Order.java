package pl.piomin.service.common.message;

import java.time.LocalDateTime;

public class Order {

	private Integer id;
	private OrderType type;
	private LocalDateTime createdAt;
	private OrderStatus status;
	private Product product;
	private Shipment shipment;

	public Order(Integer id, OrderType type, LocalDateTime createdAt, OrderStatus status, Product product, Shipment shipment) {
		this.id = id;
		this.type = type;
		this.createdAt = createdAt;
		this.status = status;
		this.product = product;
		this.shipment = shipment;
	}

	public Order() {

	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrderType getType() {
		return type;
	}

	public void setType(OrderType type) {
		this.type = type;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", type=" + type +
				", createdAt=" + createdAt +
				", status=" + status +
				", product=" + product +
				", shipment=" + shipment +
				'}';
	}
}
