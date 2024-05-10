package backend.com.example.backendcs3360.services;

import backend.com.example.backendcs3360.dto.ItemDTO;
import backend.com.example.backendcs3360.dto.OrderItemDTO;

import backend.com.example.backendcs3360.repositories.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    // Add an item to the cart
    public OrderItemDTO addItemToCart(int customerId, ItemDTO newItem) {
        List<OrderItemDTO> cartItems = orderItemRepository.findByCustomer_CustomerIdAndDateOfPurchaseIsNull(customerId);
        OrderItemDTO orderItem = new OrderItemDTO();
        // If the cart is empty, generate a unique order code for the new item.
        if (cartItems.isEmpty()) {
            orderItem.setOrderCode(generateUniqueOrderCode());

        // If the cart is not empty, set the order code to the order code of the first item in the cart.
        } else {
            orderItem.setOrderCode(cartItems.get(0).getOrderCode());
        }

        // Set the customer ID and quantity of the new item.
        orderItem.setItem(newItem);
        return orderItemRepository.save(orderItem);
    }

    // Checkout the cart to purchase the items
    public void checkout(int customerId) {
        List<OrderItemDTO> cartItems = orderItemRepository.findByCustomer_CustomerIdAndDateOfPurchaseIsNull(customerId);
        // If the cart is not empty, set the date of purchase to the current date and save the order items.
        if (!cartItems.isEmpty()) {
            for (OrderItemDTO orderItem : cartItems) {
                orderItem.setDateOfPurchase(new Date());
                orderItemRepository.save(orderItem);
            }
        }
    }

    // Generate a unique order code
    private String generateUniqueOrderCode() {
        return UUID.randomUUID().toString();
    }

    // Remove an item from the cart
    public void removeItemFromCart(int customerId, String order_code) {
        List<OrderItemDTO> cartItems = orderItemRepository.findByCustomer_CustomerIdAndDateOfPurchaseIsNull(customerId);
        for (OrderItemDTO orderItem : cartItems) {
            if (orderItem.getOrderCode() == order_code) {
                orderItemRepository.delete(orderItem);
                break;
            }
        }
    }

    public List<OrderItemDTO> getPurchaseHistoryDesc(int customerId) {
        return orderItemRepository.findByCustomer_CustomerIdAndDateOfPurchaseIsNotNullOrderByDateOfPurchaseDesc(customerId);
    }

    public List<OrderItemDTO> getPurchaseHistoryAsc(int customerId) {
        return orderItemRepository.findByCustomer_CustomerIdAndDateOfPurchaseIsNotNullOrderByDateOfPurchaseAsc(customerId);
    }

}