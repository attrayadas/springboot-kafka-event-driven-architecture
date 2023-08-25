package com.attraya.stockservice.kafka;

import com.attraya.basedomains.dto.OrderEvent;
import com.attraya.stockservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @Autowired
    private OrderRepository orderRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}"
                   , groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent event){
        LOGGER.info(String.format("Order event received in stock service -> %s", event.toString()));

        // save the order event into the database
        com.attraya.stockservice.entity.OrderEvent orderEvent = new com.attraya.stockservice.entity.OrderEvent();
        orderEvent.setMessage(event.getMessage());
        orderEvent.setStatus(event.getStatus());
        orderEvent.setOrderName(event.getOrder().getName());
        orderEvent.setOrderQty(event.getOrder().getQty());
        orderEvent.setOrderPrice(event.getOrder().getPrice());
        orderEvent.setOrderId(event.getOrder().getOrderId());

        orderRepository.save(orderEvent);
    }
}
