package kitchenpos.builder;

import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderLineItems;

import java.time.LocalDateTime;

public class OrderBuilder {

    private Long id;
    private Long orderTableId;
    private String orderStatus;
    private LocalDateTime orderedTime;
    private OrderLineItems orderLineItems;

    public OrderBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public OrderBuilder orderTableId(Long orderTableId) {
        this.orderTableId = orderTableId;
        return this;
    }

    public OrderBuilder orderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public OrderBuilder orderedTime(LocalDateTime orderedTime) {
        this.orderedTime = orderedTime;
        return this;
    }

    public OrderBuilder orderLineItems(OrderLineItems orderLineItems) {
        this.orderLineItems = orderLineItems;
        return this;
    }

    public Order build() {
        return new Order(
                this.id,
                this.orderTableId,
                this.orderStatus,
                this.orderedTime,
                this.orderLineItems
        );
    }
}
