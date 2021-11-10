package kitchenpos.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class MenuProductTest {

    @DisplayName("객체 생성 : 객체가 정상적으로 생성된다.")
    @Test
    void create() {
        // given
        Long seq = 1L;
        Menu menu = null;
        Product product = new Product(1L, "바삭치킨", new BigDecimal(16000));
        long quantity = 2;

        // when
        MenuProduct menuProduct = new MenuProduct(seq, menu, product, quantity);

        // then
        assertThat(menuProduct.getSeq()).isEqualTo(seq);
        assertThat(menuProduct.getMenu()).isEqualTo(menu);
        assertThat(menuProduct.getProduct()).isEqualTo(product);
        assertThat(menuProduct.getQuantity()).isEqualTo(quantity);
    }

    @DisplayName("Getter : 메뉴의 id를 반환한다.")
    @Test
    void getMenuId() {
        // given
        Long seq = 1L;
        Menu menu = new Menu(1L, "바삭치킨 두 마리", new BigDecimal(20000), new MenuGroup(1L, "메인메뉴"), Collections.emptyList());
        Product product = new Product(1L, "바삭치킨", new BigDecimal(16000));
        long quantity = 2;
        MenuProduct menuProduct = new MenuProduct(seq, menu, product, quantity);

        // when
        Long expectedMenuId = 1L;
        Long menuId = menuProduct.getMenuId();

        // then
        assertThat(menuId).isEqualTo(expectedMenuId);
    }

    @DisplayName("Setter : MenuProduct 객체에 메뉴를 초기화한다.")
    @Test
    void connectMenu() {
        // given
        Long seq = 1L;
        Menu menu = null;
        Product product = new Product(1L, "바삭치킨", new BigDecimal(16000));
        long quantity = 2;
        MenuProduct menuProduct = new MenuProduct(seq, menu, product, quantity);

        Menu connectMenu = new Menu(1L, "바삭치킨 두 마리", new BigDecimal(20000), new MenuGroup(1L, "메인메뉴"), Collections.singletonList(menuProduct));

        // when
        menuProduct.connectMenu(connectMenu);

        // then
        assertThat(menuProduct.getMenu()).isNotNull();
    }

    @DisplayName("Getter : 상품의 id를 반환한다.")
    @Test
    void getProductId() {
        // given
        Long seq = 1L;
        Menu menu = new Menu(1L, "바삭치킨 두 마리", new BigDecimal(20000), new MenuGroup(1L, "메인메뉴"), Collections.emptyList());
        Product product = new Product(1L, "바삭치킨", new BigDecimal(16000));
        long quantity = 2;
        MenuProduct menuProduct = new MenuProduct(seq, menu, product, quantity);

        // when
        Long expectedProductId = 1L;
        Long productId = menuProduct.getProductId();

        // then
        assertThat(productId).isEqualTo(expectedProductId);
    }
}
