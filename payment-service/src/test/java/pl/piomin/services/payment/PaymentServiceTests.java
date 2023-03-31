package pl.piomin.services.payment;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.messaging.support.MessageBuilder;
import pl.piomin.service.common.message.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PaymentServiceTests {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(PaymentServiceTests.class);

    @Autowired
    private InputDestination input;
    @Autowired
    private PaymentService service;

    @Test
    void paymentOrder() {
        Product p = new Product("Test1", 1000);
        Order o1 = new Order(1, OrderType.PURCHASE, LocalDateTime.now(), OrderStatus.NEW, p, null);
        input.send(MessageBuilder.withPayload(o1).build());

        Shipment s = new Shipment(ShipmentType.CAR, LocalDate.now(), 2000);
        Order o2 = new Order(1, OrderType.PURCHASE, LocalDateTime.now(), OrderStatus.NEW, null, s);
        input.send(MessageBuilder.withPayload(o2).build());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Order o = service.getOrder(1);
        assertNotNull(o);
    }

}
