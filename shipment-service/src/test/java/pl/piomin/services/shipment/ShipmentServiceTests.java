package pl.piomin.services.shipment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import pl.piomin.service.common.message.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import({TestChannelBinderConfiguration.class})
public class ShipmentServiceTests {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(ShipmentServiceTests.class);

    @Autowired
    private InputDestination input;
    @Autowired
    private OutputDestination output;
    @Autowired
    private ObjectMapper mapper;

    @Test
    void paymentOrder() throws JsonProcessingException {
        Shipment s = new Shipment(ShipmentType.CAR);
        Order o1 = new Order(1, OrderType.PURCHASE, LocalDateTime.now(), OrderStatus.NEW, null, s);
        input.send(MessageBuilder.withPayload(o1).build());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Message<byte[]> received = output.receive();
        assertNotNull(received.getPayload());
        String json = new String(received.getPayload());
        LOGGER.info("Order response received: {}", json);

        Order o = mapper.readValue(json, Order.class);
        assertTrue(o.getShipment().getPrice() > 0);
    }
}
