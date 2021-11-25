package kitchenpos.application;

import kitchenpos.builder.*;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.menugroup.domain.MenuGroup;
import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderLineItem;
import kitchenpos.order.domain.OrderLineItems;
import kitchenpos.product.domain.Product;
import kitchenpos.table.domain.OrderTable;
import kitchenpos.table.domain.OrderTables;
import kitchenpos.table.domain.TableGroup;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class TestFixtureFactory {

    public static Product 상품_생성(String name, BigDecimal price) {
        return new ProductBuilder()
                .id(null)
                .name(name)
                .price(price)
                .build();
    }

    public static Product 상품_후라이드_치킨() {
        return new ProductBuilder()
                .id(null)
                .name("후라이드 치킨")
                .price(new BigDecimal(16000))
                .build();
    }

    public static MenuGroup 메뉴그룹_생성(String name) {
        return new MenuGroupBuilder()
                .id(null)
                .name(name)
                .build();
    }

    public static MenuGroup 메뉴그룹_인기_메뉴() {
        return new MenuGroupBuilder()
                .id(null)
                .name("인기 메뉴")
                .build();
    }

    public static MenuProduct 메뉴상품_매핑_생성(Long productId, long quantity) {
        return new MenuProductBuilder()
                .seq(null)
                .menu(null)
                .productId(productId)
                .quantity(quantity)
                .build();
    }

    public static Menu 메뉴_생성(String name, BigDecimal price, Long menuGroupId, MenuProduct... menuProduct) {
        return new MenuBuilder()
                .id(null)
                .name(name)
                .price(price)
                .menuGroupId(menuGroupId)
                .menuProducts(Arrays.asList(menuProduct))
                .build();
    }

    public static Menu 메뉴_후라이드_치킨_한마리(Long menuGroupId, MenuProduct... menuProduct) {
        return new MenuBuilder()
                .id(null)
                .name("후라이드 치킨 한마리")
                .price(new BigDecimal(16000))
                .menuGroupId(menuGroupId)
                .menuProducts(Arrays.asList(menuProduct))
                .build();
    }

    public static OrderTable 빈_테이블_생성() {
        return new OrderTableBuilder()
                .id(null)
                .tableGroupId(null)
                .numberOfGuests(0)
                .empty(true)
                .build();
    }

    public static OrderTable 테이블_생성(Long id, Long tableGroupId, int numberOfGuests, boolean empty) {
        return new OrderTableBuilder()
                .id(id)
                .tableGroupId(tableGroupId)
                .numberOfGuests(numberOfGuests)
                .empty(empty)
                .build();
    }

    public static OrderTable 테이블_생성(boolean empty) {
        return new OrderTableBuilder()
                .id(null)
                .tableGroupId(null)
                .numberOfGuests(0)
                .empty(empty)
                .build();
    }

    public static OrderTable 테이블_생성(int numberOfGuests) {
        return new OrderTableBuilder()
                .id(null)
                .tableGroupId(null)
                .numberOfGuests(numberOfGuests)
                .empty(false)
                .build();
    }

    public static TableGroup 테이블_그룹_생성(OrderTable... orderTables) {
        return new TableGroupBuilder()
                .id(null)
                .createdDate(LocalDateTime.now())
                .orderTables(new OrderTables(Arrays.asList(orderTables)))
                .build();
    }

    public static Order 주문_생성(OrderTable orderTable, OrderLineItem... orderLineItems) {
        return new OrderBuilder()
                .id(null)
                .orderTableId(orderTable.getId())
                .orderedTime(null)
                .orderStatus(null)
                .orderLineItems(new OrderLineItems(Arrays.asList(orderLineItems)))
                .build();
    }

    public static OrderLineItem 주문_항목_생성(Long menuId, long quantity) {
        return new OrderLineItemBuilder()
                .seq(null)
                .menuId(menuId)
                .order(null)
                .quantity(quantity)
                .build();
    }
}
