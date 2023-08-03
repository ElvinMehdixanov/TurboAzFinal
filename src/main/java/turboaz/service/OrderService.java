package turboaz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import turboaz.dto.OrderDto;
import turboaz.entity.Order;
import turboaz.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Order saveOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .customerName(orderDto.getCustomerName())
                .customerSurname(orderDto.getCustomerSurname())
                .customerPhone(orderDto.getCustomerPhone())
                .make(orderDto.getMake())
                .model(orderDto.getModel())
                .odometer(orderDto.getOdometer())
                .year(orderDto.getYear())
                .fuel(orderDto.getFuel())
                .price(orderDto.getPrice())
                .build();
        return orderRepository.save(order);

    }

    public Order updateOrder(OrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getOrderId()).orElseThrow();

        order.setCustomerName(orderDto.getCustomerName());
        order.setCustomerSurname(orderDto.getCustomerSurname());
        order.setCustomerPhone(orderDto.getCustomerPhone());
        order.setMake(orderDto.getMake());
        order.setModel(orderDto.getModel());
        order.setOdometer(orderDto.getOdometer());
        order.setYear(orderDto.getYear());
        order.setFuel(orderDto.getFuel());
        order.setPrice(orderDto.getPrice());

        return orderRepository.save(order);
    }

    public void deleteorder(Long orderId) {
        orderRepository.deleteById(orderId);
    }


}
