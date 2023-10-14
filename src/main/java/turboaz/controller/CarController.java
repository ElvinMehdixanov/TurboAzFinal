package turboaz.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import turboaz.dto.Car.MakeModelDto;
import turboaz.dto.Car.PriceDto;
import turboaz.dto.Car.CarResponseDto;
import turboaz.handler.SuccessDetails;
import turboaz.service.Car.CarService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
@SecurityRequirement(name = "Bearer Authentication")
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<SuccessDetails<List<CarResponseDto>>> getAll(){
        return ResponseEntity.ok(new SuccessDetails<>( carService.getAll(), HttpStatus.OK.value(),true));
    }

    @PostMapping
    public ResponseEntity<SuccessDetails<List<CarResponseDto>>> beetweenPrice(@RequestBody PriceDto priceDto){
        return ResponseEntity.ok(new SuccessDetails<>( carService.betweenPriceService(priceDto), HttpStatus.OK.value(),true));
    }

    @PostMapping("/make/model")
    public ResponseEntity<SuccessDetails<List<CarResponseDto>>> searchByMakeAndModel(@RequestBody MakeModelDto makeModelDto){
        return ResponseEntity.ok(new SuccessDetails<>( carService.searchByMakeAndModel(makeModelDto), HttpStatus.OK.value(),true));
    }

}

