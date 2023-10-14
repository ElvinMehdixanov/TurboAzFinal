package turboaz.service.Car;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import turboaz.dto.Car.MakeModelDto;
import turboaz.dto.Car.PriceDto;
import turboaz.dto.Car.CarResponseDto;
import turboaz.entity.CarEntity;
import turboaz.mapper.CarMapper;
import turboaz.repository.CarRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarJsoupService carJsoupService;
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public void test() throws IOException {
        carJsoupService.jsoupCar();
    }

    public List<CarResponseDto> getAll(){
        List<CarEntity> all = carRepository.findAll();
        List<CarResponseDto> carResponseDtos = carMapper.mapEntityToResponseDto(all);

        return carResponseDtos;
    }

    public List<CarResponseDto> betweenPriceService(PriceDto priceDto){
        ArrayList<CarEntity> allByPriceBetween = carRepository.findAllByPriceBetween(priceDto.getMinPrice(), priceDto.getMaxPrice());
       return carMapper.mapEntityToResponseDto(allByPriceBetween);

    }

    public List<CarResponseDto> searchByMakeAndModel(MakeModelDto makeModelDto){
        ArrayList<CarEntity> allByMakeAndModel = carRepository.findAllByMakeAndModel(makeModelDto.getMake(), makeModelDto.getModel());
        List<CarResponseDto> carResponseDtos = carMapper.mapEntityToResponseDto(allByMakeAndModel);
        return carResponseDtos;
    }
}
