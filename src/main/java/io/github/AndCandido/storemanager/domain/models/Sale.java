package io.github.AndCandido.storemanager.domain.models;

import io.github.AndCandido.storemanager.domain.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "TB_SALES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<ProductSold> productsSold = new ArrayList<>();

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<Installment> installments;

    @Column(nullable = false)
    private BigDecimal price;

    private LocalDateTime createdAt;

    @PrePersist
    void createCreatedAt() {
        setCreatedAt(LocalDateTime.now().withNano(0));
    }
}