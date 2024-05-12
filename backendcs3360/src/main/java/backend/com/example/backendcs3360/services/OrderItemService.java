package backend.com.example.backendcs3360.services;

import backend.com.example.backendcs3360.dto.OrderItemDTO;
import backend.com.example.backendcs3360.dto.ItemDTO;
import backend.com.example.backendcs3360.dto.CustomerDTO;

import backend.com.example.backendcs3360.models.Cart;
import backend.com.example.backendcs3360.models.OrderItem;
import backend.com.example.backendcs3360.repositories.CustomerRepository;
import backend.com.example.backendcs3360.repositories.ItemRepository;
import backend.com.example.backendcs3360.repositories.OrderItemRepository;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository, CustomerRepository customerRepository,
            ItemRepository itemRepository) {
        this.orderItemRepository = orderItemRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
    }

    public OrderItemDTO addItemToCart(int customerId, int itemId) {
        List<OrderItemDTO> cartItems = orderItemRepository.findByCustomer_CustomerIdAndDateOfPurchaseIsNull(customerId);
        OrderItemDTO orderItem = new OrderItemDTO();
        // If the cart is empty, generate a unique order code for the new item.
        if (cartItems.isEmpty()) {
            orderItem.setOrderCode(generateUniqueOrderCode());
            // If the cart is not empty, set the order code to the order code of the first
            // item in the cart.
        } else {
            orderItem.setOrderCode(cartItems.get(0).getOrderCode());
        }

        // Set the customer and item of the new order item.
        CustomerDTO customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        ItemDTO item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        orderItem.setCustomer(customer);
        orderItem.setItem(item);
        orderItem.setQuantity(1); // Set quantity to 1
        return orderItemRepository.save(orderItem);
    }

    public OrderItemDTO updateItemQuantity(OrderItemDTO newOrderItem, int customerId, int itemId) {
        OrderItemDTO orderItem = orderItemRepository
                .findByCustomer_CustomerIdAndItem_ItemIdAndDateOfPurchaseIsNull(customerId, itemId);
        if (orderItem != null) {
            // Update the quantity of the item
            
            orderItem.setQuantity(newOrderItem.getQuantity());
            return orderItemRepository.save(orderItem);
        } else {
            throw new RuntimeException("Item not found in cart");
        }
    }

    // Checkout the cart to purchase the items
    public void checkout(int customerId) {
        List<OrderItemDTO> cartItems = orderItemRepository.findByCustomer_CustomerIdAndDateOfPurchaseIsNull(customerId);
        // If the cart is not empty, set the date of purchase to the current date and
        // save the order items.
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

//    public List<OrderItemDTO> getPurchaseHistoryDesc(int customerId) {
//        return orderItemRepository
//                .findByCustomer_CustomerIdAndDateOfPurchaseIsNotNullOrderByDateOfPurchaseDesc(customerId);
//    }
    public Cart getPurchaseHistoryDesc(int customerId) {
    List<OrderItem> lists = orderItemRepository
            .findByCustomer_CustomerIdAndDateOfPurchaseIsNotNullOrderByDateOfPurchaseDesc(customerId)
            .stream()
            .map(OrderItemDTO::convertToOrderItems)
            .collect(Collectors.toList());
        double total = lists.stream()
                .mapToDouble(orderItem -> orderItem.getQuantity() * orderItem.getItem().getPrice())
                .sum();
    int customerID = lists.get(0).getCustomer().getCustomerId();
    String phoneNumber = lists.get(0).getCustomer().getPhoneNumber();
    Cart newCart = new Cart();
    newCart.setOrderItems(lists);
    newCart.setPhoneNumber(phoneNumber);
    newCart.setTotal(total);

    return newCart;
}

    public List<OrderItemDTO> getPurchaseHistoryAsc(int customerId) {
        return orderItemRepository
                .findByCustomer_CustomerIdAndDateOfPurchaseIsNotNullOrderByDateOfPurchaseAsc(customerId);
    }

}