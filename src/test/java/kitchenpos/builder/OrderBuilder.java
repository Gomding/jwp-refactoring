package kitchenpos.builder;

import kitchenpos.domain.Order;
import kitchenpos.domain.OrderLineItem;
import kitchenpos.domain.OrderTable;

import java.time.LocalDateTime;
import java.util.List;

public class OrderBuilder {

    private Long id;
    private Long orderTableId;
    private String orderStatus;
    private LocalDateTime orderedTime;
    private List<OrderLineItem> orderLineItems;

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

    public OrderBuilder orderLineItems(List<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
        return this;
    }

    public Order build() {
        return new Order(
                this.id,
                new OrderTable(this.orderTableId),
                this.orderStatus,
                this.orderedTime,
                this.orderLineItems
        );
    }
}
