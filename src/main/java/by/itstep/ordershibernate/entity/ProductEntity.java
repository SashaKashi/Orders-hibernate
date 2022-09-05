package by.itstep.ordershibernate.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="products")
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "quantity_in_stock")
    private Integer quantity_in_stock;
    @Column(name = "unit_price")
    private double unit_price;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItems;




}
