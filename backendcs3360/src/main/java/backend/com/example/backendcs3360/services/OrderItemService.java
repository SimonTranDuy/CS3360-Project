package backend.com.example.backendcs3360.services;

import backend.com.example.backendcs3360.dto.ItemDTO;
import backend.com.example.backendcs3360.dto.OrderItemDTO;

import backend.com.example.backendcs3360.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    // Add an item to the cart
    public OrderItemDTO addItemToCart(int customer_id, ItemDTO newItem) {
        List<OrderItemDTO> cartItems = orderItemRepository.findByCustomerIdAndDateOfPurchaseIsNull(customer_id);
        OrderItemDTO orderItem = new OrderItemDTO();
        // If the cart is empty, generate a unique order code for the new item.
        if (cartItems.isEmpty()) {
            orderItem.setOrder_code(generateUniqueOrderCode());

        // If the cart is not empty, set the order code to the order code of the first item in the cart.
        } else {
            orderItem.setOrder_code(cartItems.get(0).getOrder_code());
        }

        // Set the customer ID and quantity of the new item.
        orderItem.setItem(newItem);
        return orderItemRepository.save(orderItem);
    }

    // Checkout the cart to purchase the items
    public void checkout(int customer_id) {
        List<OrderItemDTO> cartItems = orderItemRepository.findByCustomerIdAndDateOfPurchaseIsNull(customer_id);
        // If the cart is not empty, set the date of purchase to the current date and save the order items.
        if (!cartItems.isEmpty()) {
            for (OrderItemDTO orderItem : cartItems) {
                orderItem.setDate_of_purchase(new Date());
                orderItemRepository.save(orderItem);
            }
        }
    }

    // Generate a unique order code
    private String generateUniqueOrderCode() {
        return UUID.randomUUID().toString();
    }

    // Remove an item from the cart
    public void removeItemFromCart(int customer_id, String order_code) {
        List<OrderItemDTO> cartItems = orderItemRepository.findByCustomerIdAndDateOfPurchaseIsNull(customer_id);
        for (OrderItemDTO orderItem : cartItems) {
            if (orderItem.getOrder_code() == order_code) {
                orderItemRepository.delete(orderItem);
                break;
            }
        }
    }

    public List<OrderItemDTO> getPurchaseHistoryDesc(int customer_id) {
        return orderItemRepository.findByCustomerIdAndDateOfPurchaseIsNotNullOrderByDateOfPurchaseDesc(customer_id);
    }

    public List<OrderItemDTO> getPurchaseHistoryAsc(int customer_id) {
        return orderItemRepository.findByCustomerIdAndDateOfPurchaseIsNotNullOrderByDateOfPurchaseAsc(customer_id);
    }

}