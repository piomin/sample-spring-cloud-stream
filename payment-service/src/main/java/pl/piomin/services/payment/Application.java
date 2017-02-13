package pl.piomin.services.payment;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;

import pl.piomin.service.common.message.Order;

@SpringBootApplication
@EnableBinding(Sink.class)
public class Application {

	@Autowired
	private PaymentService paymentService;

	protected Logger logger = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@StreamListener(Sink.INPUT)
	public void processOrder(Order order) {
		logger.info("Processing order: " + order);
		Order o = paymentService.processOrder(order);
		if (o != null)
			logger.info("Final response: " + (o.getProduct().getPrice() + o.getShipment().getPrice()));
	}

	@Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}

}
