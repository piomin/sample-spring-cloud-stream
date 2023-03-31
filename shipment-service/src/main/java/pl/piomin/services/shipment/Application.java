package pl.piomin.services.shipment;

import java.util.function.Function;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pl.piomin.service.common.message.Order;

@SpringBootApplication
public class Application {

	@Autowired
	private ShipmentService shipmentService;
	
	protected Logger logger = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Function<Order, Order> input() {
		return order -> {
			logger.info("Processing order: " + order);
			order.setShipment(shipmentService.processOrder(order));
			logger.info("Output order: " + order);
			return order;
		};
	}

}
