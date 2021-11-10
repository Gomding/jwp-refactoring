package kitchenpos.builder;

import kitchenpos.domain.Menu;
import kitchenpos.domain.MenuGroup;
import kitchenpos.domain.MenuProduct;

import java.math.BigDecimal;
import java.util.List;

public class MenuBuilder {

    private Long id;
    private String name;
    private BigDecimal price;
    private MenuGroup menuGroup;
    private List<MenuProduct> menuProducts;

    public MenuBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public MenuBuilder name(String name) {
        this.name = name;
        return this;
    }

    public MenuBuilder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public MenuBuilder menuGroup(MenuGroup menuGroup) {
        this.menuGroup = menuGroup;
        return this;
    }

    public MenuBuilder menuProducts(List<MenuProduct> menuProducts) {
        this.menuProducts = menuProducts;
        return this;
    }

    public Menu build() {
        return new Menu(this.id, this.name, this.price, this.menuGroup, this.menuProducts);
    }
}
