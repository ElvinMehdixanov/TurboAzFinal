package turboaz.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import turboaz.dto.OrderDto;
import turboaz.entity.Order;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface OrderMapper {
    Order mapDtoToEntity(OrderDto orderDto);


    Order mapDtoToEntityFOrUpdate(@MappingTarget Order order, OrderDto orderDto);


}
