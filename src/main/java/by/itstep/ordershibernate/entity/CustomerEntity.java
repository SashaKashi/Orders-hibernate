package by.itstep.ordershibernate.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="customers")
@Data
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Integer id;
    @Column(name = "first_name")
    private String name;
    @Column(name="last_name")
    private String lastName;
    @Column(name="birth_date")
    private LocalDate birthDate;
    @Column(name="phone")
    private String phone;
    @Column(name="address")
    private String address;
    @Column(name="city")
    private String city;
    @Column(name="state")
    private String state;
    @Column(name="points")
    private Integer points;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<OrderEntity> orders;



}
