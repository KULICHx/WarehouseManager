package ru.kulich.warehousemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kulich.warehousemanager.entity.Product;

import java.util.UUID;

/**
 * Репозиторий для работы с продуктами в базе данных.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    // Здесь можно добавить дополнительные методы, если это необходимо
}
