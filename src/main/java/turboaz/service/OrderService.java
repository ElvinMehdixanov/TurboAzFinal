package turboaz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import turboaz.dto.OrderDto;
import turboaz.entity.Order;
import turboaz.exception.NotFoundException;
import turboaz.mapper.OrderMapper;
import turboaz.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    public List<Order> getAllOrder() {
        if (orderRepository.findAll().isEmpty()){
            throw  new NotFoundException("Order is empty" );
        }
        return orderRepository.findAll();
    }

    public Order saveOrder(OrderDto orderDto) {
        Order order = orderMapper.mapDtoToEntity(orderDto);
        return orderRepository.save(order);
    }

    public Order updateOrder(OrderDto orderDto) {

        Order order = orderRepository.findById(orderDto.getOrderId()).orElseThrow(()->new NotFoundException("Order Id is not found "));

        Order order1 = orderMapper.mapDtoToEntityFOrUpdate(order, orderDto);
        return orderRepository.save(order1);
    }

    public void deleteOrder(Long orderId) {
        if (!orderRepository.findById(orderId).isPresent()) {
            throw new NotFoundException("Order Id is not found ");
        }
        orderRepository.deleteById(orderId);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order with ID " + orderId + " not found"));
    }

}
