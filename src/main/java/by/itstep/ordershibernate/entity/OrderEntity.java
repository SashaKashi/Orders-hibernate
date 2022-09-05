package by.itstep.ordershibernate.entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="orders")
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Integer id;
    @Column(name="order_date")
    private LocalDate orderDate;
    @Column(name="comments")
    private String comment;
    @Column(name="shipped_date")
    private LocalDate shippedDate;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name="status")
    private OrderStatusEntity status;

    @ManyToOne
    @JoinColumn(name="shipper_id")
    private ShipperEntity shipper;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItems;



}
