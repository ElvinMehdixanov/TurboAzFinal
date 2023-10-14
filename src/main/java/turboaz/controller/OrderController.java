package turboaz.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import turboaz.dto.OrderDto;
import turboaz.entity.Order;
import turboaz.handler.SuccessDetails;
import turboaz.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
@SecurityRequirement(name = "Bearer Authentication")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<SuccessDetails<List<Order>>> getAll() {
        return ResponseEntity.ok(new SuccessDetails<>(orderService.getAllOrder(), HttpStatus.OK.value(),true));
        }


    @PostMapping
    public ResponseEntity<SuccessDetails<String>> create(@Valid @RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderDto);
        return  ResponseEntity.ok(new SuccessDetails<>("Order Save Successfuly ", HttpStatus.OK.value(),true));
    }


    @PutMapping("/{id}")
    public ResponseEntity<SuccessDetails<String>> update(@Valid @RequestBody OrderDto orderDto) {
        orderService.updateOrder(orderDto);
        return ResponseEntity.ok(new SuccessDetails<>("Order Updated Successfully ", HttpStatus.OK.value(),true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessDetails<String>> delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
      return ResponseEntity.ok(new SuccessDetails<>("Order Deleted Successfuly ", HttpStatus.OK.value(),true));


    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDetails<String>> getById(@Valid @PathVariable Long id) {
        orderService.getOrderById(id);
        return ResponseEntity.ok(new SuccessDetails<>("Order Find Successfuly", HttpStatus.OK.value(), true));
    }
}

