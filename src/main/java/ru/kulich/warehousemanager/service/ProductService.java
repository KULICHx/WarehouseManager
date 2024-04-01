package ru.kulich.warehousemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kulich.warehousemanager.entity.Product;
import ru.kulich.warehousemanager.exception.ResourceNotFoundException;
import ru.kulich.warehousemanager.repository.ProductRepository;

import java.util.List;
import java.util.UUID;
import java.util.Date;

/**
 * Сервис для управления товарами.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Создает новый продукт.
     * @param product Продукт для создания.
     * @return Созданный продукт.
     */
    public Product createProduct(Product product) {
        product.setCreatedAt(new Date());
        return productRepository.save(product);
    }

    /**
     * Получает список всех продуктов.
     * @return Список всех продуктов.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Получает продукт по его идентификатору.
     * @param id Идентификатор продукта.
     * @return Найденный продукт.
     * @throws ResourceNotFoundException если продукт не найден.
     */
    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    /**
     * Обновляет данные продукта.
     * @param id Идентификатор продукта для обновления.
     * @param product Объект с обновленными данными о продукте.
     * @return Обновленный продукт.
     * @throws ResourceNotFoundException если продукт не найден.
     */
    public Product updateProduct(UUID id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setArticle(product.getArticle());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setLastQuantityChange(new Date()); // Установка даты последнего изменения количества
        return productRepository.save(existingProduct);
    }

    /**
     * Удаляет продукт по его идентификатору.
     * @param id Идентификатор продукта для удаления.
     * @throws ResourceNotFoundException если продукт не найден.
     */
    public void deleteProduct(UUID id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
