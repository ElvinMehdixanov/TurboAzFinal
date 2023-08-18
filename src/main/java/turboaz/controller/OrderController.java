package turboaz.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import turboaz.dto.OrderDto;
import turboaz.entity.Order;
import turboaz.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@SecurityRequirement(name = "Bearer Authentication")
public class OrderController {
    private final OrderService orderService;


    @GetMapping("/all")
    public List<Order> getAll() {
        return orderService.getAllOrder();
    }

    @PostMapping("/save")
    public Order saveOrder(@RequestBody OrderDto orderDto) {
        return orderService.saveOrder(orderDto);
    }

    @PutMapping("/update/{id}")
    public Order updateOrder(@RequestBody OrderDto orderDto) {
        return orderService.updateOrder(orderDto);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteorder(id);

    }
}

