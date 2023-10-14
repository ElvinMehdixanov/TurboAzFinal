package turboaz.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_surname")
    private String customerSurname;

    @Column(name = "engine")
    private String engine;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "odometer")
    private Integer odometer;

    @Column(name = "year_of_car")
    private Integer year;

    @Column(name = "fuel")
    private String fuel;

    @Column(name = "price")
    private Integer price;
}
