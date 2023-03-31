package pl.piomin.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.piomin.service.common.message.Order;

import java.util.function.Function;
import java.util.logging.Logger;

@SpringBootApplication
public class Application {

	@Autowired
	private ProductService productService;
	
	protected Logger logger = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Function<Order, Order> input() {
		return order -> {
			logger.info("Processing order: " + order);
			order.setProduct(productService.processOrder(order));
			logger.info("Output order: " + order);
			return order;
		};
	}

}
