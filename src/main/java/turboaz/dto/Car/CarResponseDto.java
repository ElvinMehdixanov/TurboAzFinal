package turboaz.dto.Car;

import lombok.*;

import javax.persistence.Column;
import java.util.Date;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarResponseDto {

    private String make;
    private String model;
    private String region;
    private Integer price;
    private String vehicleType;
    private Integer year;
    private String color;
    private String fuel;
    private String gear;
    private String transmission;
    private String engine;
    private String horsePower;
    private Integer odometer;
    private String date;
    private String time;
}
