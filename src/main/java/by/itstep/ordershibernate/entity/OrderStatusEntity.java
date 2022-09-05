package by.itstep.ordershibernate.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="order_statuses")
@Data
public class OrderStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_status_id")
    private Integer id;
    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<OrderEntity> orders;

}
