package internetshop.dao;

import internetshop.model.Order;
import internetshop.model.ShoppingCart;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getUserOrders(Long userId);

    Optional<Order> get(Long id);

    List<Order> getAll();

    boolean delete(Long id);
}
