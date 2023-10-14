package turboaz.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import turboaz.dto.Car.CarRequestDto;
import turboaz.dto.Car.CarResponseDto;
import turboaz.entity.CarEntity;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CarMapper {
    CarEntity mapRequestDtoToEntity(CarRequestDto carRequestDto);
    List<CarResponseDto> mapEntityToResponseDto(List<CarEntity> carEntity);

    CarResponseDto mapEntityToResponseDto(CarEntity carEntity);


}
