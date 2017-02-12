package pl.piomin.services.order;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

import pl.piomin.service.common.message.Order;
import pl.piomin.service.common.message.OrderStatus;
import pl.piomin.service.common.message.OrderType;
import pl.piomin.service.common.message.Product;
import pl.piomin.service.common.message.Shipment;


@SpringBootApplication
@EnableBinding(Source.class)
public class Application {

	protected Logger logger = Logger.getLogger(Application.class.getName());
	
	private int index = 0;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
	public MessageSource<Order> orderSource() {
		return () -> {
			Order o = new Order(index++, OrderType.PURCHASE, LocalDateTime.now(), OrderStatus.NEW, new Product(), new Shipment());
			logger.info("Sending order: " + o);
			return new GenericMessage<>(o); 
		};
	}
	
	@Bean
	public AlwaysSampler defaultSampler() {
	  return new AlwaysSampler();
	}
	
}
