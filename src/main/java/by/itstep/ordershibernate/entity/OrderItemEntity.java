package by.itstep.ordershibernate.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="order_items")
@Data
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="quantity")
    private Integer quantity;
    @Column(name="unit_price")
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name="order_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;



}
