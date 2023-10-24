package io.github.AndCandido.storemanager.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "TB_PRODUCTS_SOLD")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSoldModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel productModel;

    @Column(nullable = false)
    private Integer quantity;
}

