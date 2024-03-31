package ru.kulich.warehousemanager.entity;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Date;
import java.util.UUID;

/**
 * Класс, представляющий продукт.
 */
@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Article is required")
    @Column(unique = true, nullable = false)
    private String article;

    private String description;

    private String category;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be positive or zero")
    private double price;

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity must be positive or zero")
    private int quantity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastQuantityChange;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    /**
     * Получает идентификатор продукта.
     * @return Идентификатор продукта.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Получает название продукта.
     * @return Название продукта.
     */
    public String getName() {
        return name;
    }

    /**
     * Получает артикул продукта.
     * @return Артикул продукта.
     */
    public String getArticle() {
        return article;
    }
}
