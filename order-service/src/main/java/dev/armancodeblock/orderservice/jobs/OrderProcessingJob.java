package dev.armancodeblock.orderservice.jobs;

import dev.armancodeblock.orderservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessingJob {
    private static final Logger logger = LoggerFactory.getLogger(OrderProcessingJob.class);
    private final OrderService orderService;

    public OrderProcessingJob(OrderService orderService) {
        this.orderService = orderService;
    }
}
