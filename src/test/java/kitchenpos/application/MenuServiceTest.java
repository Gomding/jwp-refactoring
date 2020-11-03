package kitchenpos.application;

import static kitchenpos.helper.EntityCreateHelper.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.domain.MenuGroupRepository;
import kitchenpos.menu.dto.MenuCreateRequest;
import kitchenpos.menu.dto.MenuProductRequest;
import kitchenpos.menu.dto.MenuResponse;
import kitchenpos.menu.exception.InvalidMenuPriceException;
import kitchenpos.menu.exception.MenuPriceExceededException;
import kitchenpos.menu.service.MenuService;
import kitchenpos.product.domain.Product;
import kitchenpos.product.domain.ProductRepository;

@SpringBootTest
@Sql(value = "/truncate.sql")
class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MenuGroupRepository menuGroupRepository;

    @DisplayName("메뉴를 추가한다.")
    @Test
    void create() {
        Product product = createProduct(null, "콜라", BigDecimal.valueOf(2000L));
        Product savedProduct = productRepository.save(product);

        MenuGroup menuGroup = createMenuGroup(null, "음료류");
        MenuGroup savedMenuGroup = menuGroupRepository.save(menuGroup);

        MenuCreateRequest request = new MenuCreateRequest("콜라 세트", 1900L, savedMenuGroup.getId(),
            Arrays.asList(new MenuProductRequest(savedProduct.getId(), 1L)));

        Long id = menuService.create(request);

        assertThat(id).isNotNull();
    }

    @DisplayName("메뉴 가격이 음수인 경우 예외가 발생하는지 확인한다.")
    @Test
    void createInvalidPriceMenu() {
        MenuGroup menuGroup = menuGroupRepository.save(createMenuGroup(null, "음료"));

        MenuCreateRequest request = new MenuCreateRequest("콜라 세트", -1L, menuGroup.getId(), Collections.emptyList());
        assertThatThrownBy(
            () -> menuService.create(request)
        ).isInstanceOf(InvalidMenuPriceException.class);
    }

    @DisplayName("메뉴가 아무 메뉴 그룹에도 속하지 않을 시 예외가 발생하는지 확인한다.")
    @Test
    void createInvalidMenuGroup() {
        Product product = createProduct(null, "콜라", BigDecimal.valueOf(2000L));
        Product savedProduct = productRepository.save(product);

        MenuGroup menuGroup = createMenuGroup(null, "음료류");
        MenuGroup savedMenuGroup = menuGroupRepository.save(menuGroup);

        MenuCreateRequest request = new MenuCreateRequest("콜라 세트", 1900L, -1L,
            Arrays.asList(new MenuProductRequest(savedProduct.getId(), 1L)));

        assertThatThrownBy(
            () -> menuService.create(request)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴 가격이 개별 상품 가격의 총합보다 비쌀 시 예외가 발생하는지 확인한다.")
    @Test
    void createInvalidDiscountMenu() {
        Product product = createProduct(1L, "콜라", BigDecimal.valueOf(2000L));

        MenuGroup menuGroup = createMenuGroup(null, "음료류");

        MenuGroup savedMenuGroup = menuGroupRepository.save(menuGroup);
        Product savedProduct = productRepository.save(product);

        MenuCreateRequest request = new MenuCreateRequest("콜라 세트", 2100L, savedMenuGroup.getId(),
            Arrays.asList(new MenuProductRequest(savedProduct.getId(), 1L)));

        assertThatThrownBy(
            () -> menuService.create(request)
        ).isInstanceOf(MenuPriceExceededException.class);
    }

    @DisplayName("메뉴그룹을 조회한다.")
    @Test
    void list() {
        Product product = createProduct(null, "콜라", BigDecimal.valueOf(2000L));
        Product savedProduct = productRepository.save(product);

        MenuGroup menuGroup = createMenuGroup(null, "음료류");
        MenuGroup savedMenuGroup = menuGroupRepository.save(menuGroup);

        MenuCreateRequest request = new MenuCreateRequest("콜라 세트", 1900L, savedMenuGroup.getId(),
            Arrays.asList(new MenuProductRequest(savedProduct.getId(), 1L)));

        menuService.create(request);
        List<MenuResponse> menus = menuService.list().getMenuResponses();

        assertAll(
            () -> assertThat(menus.size()).isEqualTo(1),
            () -> assertThat(menus.get(0).getId()).isNotNull(),
            () -> assertThat(menus.get(0).getPrice().longValue()).isEqualTo(1900L),
            () -> assertThat(menus.get(0).getName()).isEqualTo("콜라 세트")
        );
    }
}
