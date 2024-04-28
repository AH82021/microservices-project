package dev.armancodeblock.orderservice.valdators;

import dev.armancodeblock.orderservice.client.catalog.ProductServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class OrderValidator {
    private static final Logger log = LoggerFactory.getLogger(OrderValidator.class);
    private final ProductServiceClient productServiceClient;

    public OrderValidator(ProductServiceClient productServiceClient) {
        this.productServiceClient = productServiceClient;
    }
}
