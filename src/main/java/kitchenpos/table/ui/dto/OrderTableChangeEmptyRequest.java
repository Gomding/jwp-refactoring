package kitchenpos.table.ui.dto;

import kitchenpos.table.domain.OrderTable;

public class OrderTableChangeEmptyRequest {
    private boolean empty;

    public OrderTableChangeEmptyRequest() {
    }

    public OrderTableChangeEmptyRequest(boolean empty) {
        this.empty = empty;
    }

    public boolean isEmpty() {
        return empty;
    }

    public OrderTable toEntity() {
        return new OrderTable(null, 0, this.empty);
    }
}