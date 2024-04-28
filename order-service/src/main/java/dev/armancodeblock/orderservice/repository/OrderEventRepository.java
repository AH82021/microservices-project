package dev.armancodeblock.orderservice.repository;
import dev.armancodeblock.orderservice.domain.OrderEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventRepository extends JpaRepository<OrderEventEntity, Long> {}
