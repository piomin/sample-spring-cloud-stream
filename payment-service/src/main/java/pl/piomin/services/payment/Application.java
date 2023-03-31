package pl.piomin.services.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.piomin.service.common.message.Order;

import java.util.function.Consumer;
import java.util.logging.Logger;

@SpringBootApplication
//@EnableBinding(Sink.class)
public class Application {

	@Autowired
	private PaymentService paymentService;

	protected Logger logger = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
//	@StreamListener(Sink.INPUT)
	public Consumer<Order> processOrder(Order order) {
		return ret -> {
			logger.info("Processing order: " + order);
			Order o = paymentService.processOrder(order);
			if (o != null)
				logger.info("Final response: " + (o.getProduct().getPrice() + o.getShipment().getPrice()));
		};
	}

}
