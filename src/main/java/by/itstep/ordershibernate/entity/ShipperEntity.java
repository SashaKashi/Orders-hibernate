package by.itstep.ordershibernate.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="shippers")
@Data
public class ShipperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shipper_id")
    private Integer id;
    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "shipper", fetch = FetchType.LAZY)
    private List<OrderEntity> orders;



}
