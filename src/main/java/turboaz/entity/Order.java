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
@Table(name = "Customer_Order")
public class Order {
    @Id
    @Column(name = "order_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_Name")
    private String customerName;

    @Column(name = "customer_Surname")
    private String customerSurname;

    @Column(name = "engine")
    private String engine;

    @Column(name = "customer_Phone")
    private String customerPhone;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "odometer")
    private Integer odometer;

    @Column(name = "year_Of_Car")
    private Integer year;

    @Column(name = "fuel")
    private String fuel;

    @Column(name = "price")
    private Integer price;
}
