package pl.piomin.services.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.piomin.service.common.message.*;

import java.time.LocalDateTime;
import java.util.function.Supplier;
import java.util.logging.Logger;


@SpringBootApplication
public class Application {

	protected Logger logger = Logger.getLogger(Application.class.getName());
	
	private int index = 0;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Supplier<Order> output() {
		return () -> {
			Order o = new Order(index++, OrderType.PURCHASE, LocalDateTime.now(), OrderStatus.NEW, new Product("Example#2"), new Shipment(ShipmentType.SHIP));
			logger.info("Sending order: " + o);
			return o;
		};
	}
	
}
