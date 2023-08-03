package turboaz.dto;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private String customerName;
    private String customerSurname;
    private String customerPhone;
    private String make;
    private String model;
    private Integer odometer;
    private String engine;
    private Integer year;
    private String fuel;
    private Integer price;
}
