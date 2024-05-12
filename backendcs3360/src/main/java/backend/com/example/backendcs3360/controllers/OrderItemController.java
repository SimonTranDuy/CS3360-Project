package backend.com.example.backendcs3360.controllers;

import backend.com.example.backendcs3360.dto.OrderItemDTO;
import backend.com.example.backendcs3360.models.Cart;
import backend.com.example.backendcs3360.models.ResponseObject;
import backend.com.example.backendcs3360.services.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController {
    private OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/add/{customerId}/{itemId}")
    public ResponseEntity<ResponseObject> addItemToCart(@PathVariable int customerId, @PathVariable int itemId) {
        OrderItemDTO orderItem = orderItemService.addItemToCart(customerId, itemId);
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("success", "Add Item to Cart successfully", orderItem));
    }

    @PutMapping("change-quantity/{customerId}/{itemId}")
    public ResponseEntity<ResponseObject> updateItemQuantity(@RequestBody OrderItemDTO newOrderItem, @PathVariable int customerId, @PathVariable int itemId) {
        OrderItemDTO updatedItem = orderItemService.updateItemQuantity(newOrderItem, customerId, itemId);
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("success", "Update Item to Cart successfully", updatedItem));
    }

    @PostMapping("/checkout/{customerId}")
    public ResponseEntity<ResponseObject> checkout(@PathVariable int customerId) {
        orderItemService.checkout(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Purchase successfully", null));
    }

    @DeleteMapping("/remove/{customerId}/{orderCode}")
    public ResponseEntity<ResponseObject> removeItemFromCart(@PathVariable int customerId,
            @PathVariable String orderCode) {
        orderItemService.removeItemFromCart(customerId, orderCode);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Remove Item from Cart successfully", null));
    }

    @GetMapping("/historyDesc/{customerId}")
    public ResponseEntity<ResponseObject> getPurchaseHistoryDesc(@PathVariable int customerId) {
        Cart purchaseHistory = orderItemService.getPurchaseHistoryDesc(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "See History successfully", purchaseHistory));
    }

    @GetMapping("/historyAsc/{customerId}")
    public ResponseEntity<ResponseObject> getPurchaseHistoryAsc(@PathVariable int customerId) {
        List<OrderItemDTO> purchaseHistory = orderItemService.getPurchaseHistoryAsc(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "See History successfully", purchaseHistory));
    }
}