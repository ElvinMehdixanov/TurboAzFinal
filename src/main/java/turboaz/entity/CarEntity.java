package turboaz.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
public class CarEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "region")
    private String region;

    @Column(name = "price")
    private Integer price;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "year_of_car")
    private Integer year;

    @Column(name = "color")
    private String color;

    @Column(name = "fuel")
    private String fuel;

    @Column(name = "gear")
    private String gear;

    @Column(name = "transmission")
    private String transmission;

    @Column(name = "engine")
    private String engine;

    @Column(name = "horse_power")
    private String horsePower;

    @Column(name = "odometer")
    private Integer odometer;


    private Date date;

    private String time;
}
