package contract.utils;

import contract.entities.User;
import lombok.Data;

import java.io.Serializable;

@Data
public class CartUserDto implements Serializable {
    private User user;
    private ShoppingCart shoppingCart;

    public CartUserDto(User user, ShoppingCart shoppingCart) {
        this.user = user;
        this.shoppingCart = shoppingCart;
    }
}
