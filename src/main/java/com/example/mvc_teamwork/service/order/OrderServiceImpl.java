package com.example.mvc_teamwork.service.order;

import com.example.mvc_teamwork.entity.Order;
import com.example.mvc_teamwork.entity.Product;
import com.example.mvc_teamwork.entity.ShoppingCart;
import com.example.mvc_teamwork.jasper.OrderReportUser;
import com.example.mvc_teamwork.jasper.ProductReportAdmin;
import com.example.mvc_teamwork.repository.OrderRepository;
import com.example.mvc_teamwork.repository.ProductRepository;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }
    @Override
    public Order getOrderDetail(int orderId) {
        Optional<Order> order = this.orderRepository.findById(orderId);
        return order.isPresent() ? order.get() : null;
    }
    @Override
    public float getCartAmount(List<ShoppingCart> shoppingCartList) {

        float totalCartAmount = 0f;
        float singleCartAmount = 0f;
        int availableQuantity = 0;

        for (ShoppingCart cart : shoppingCartList) {

            int productId = cart.getProductId();
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                Product product1 = product.get();
                if (product1.getAvailableQuantity() < cart.getQuantity()) {
                    singleCartAmount = product1.getPrice() * product1.getAvailableQuantity();
                    cart.setQuantity(product1.getAvailableQuantity());
                } else {
                    singleCartAmount = cart.getQuantity() * product1.getPrice();
                    availableQuantity = product1.getAvailableQuantity() - cart.getQuantity();
                }
                totalCartAmount = totalCartAmount + singleCartAmount;
                product1.setAvailableQuantity(availableQuantity);
                availableQuantity=0;
                cart.setProductName(product1.getName());
                cart.setAmount(singleCartAmount);
                productRepository.save(product1);
            }
        }
        return totalCartAmount;
    }
    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
    @Override
    public void fillReportToUser(int id) {

        File template = new File(new ClassPathResource("src/main/resources/jasper-templates/OrderReportUser.jrxml").getPath());
        OrderReportUser orderReportUser = new OrderReportUser();
        File orderInformation = new File("src/main/resources/jasper-templates/OrderReportUser.pdf");
        Order order = orderRepository.getReferenceById(id);
        try {
            orderReportUser.fillReport(template,orderInformation,order);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
