package ru.kulich.warehousemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kulich.warehousemanager.entity.Product;
import ru.kulich.warehousemanager.service.ProductService;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер для управления продуктами.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Создает новый продукт.
     * @param product Продукт для создания.
     * @return Ответ с созданным продуктом и статусом HTTP 201 (CREATED).
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    /**
     * Получает список всех продуктов.
     * @return Ответ с списком всех продуктов и статусом HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Получает продукт по его идентификатору.
     * @param productId Идентификатор продукта.
     * @return Ответ с найденным продуктом и статусом HTTP 200 (OK).
     */
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID productId) {
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    /**
     * Обновляет продукт с заданным идентификатором.
     * @param productId Идентификатор продукта для обновления.
     * @param product Новые данные продукта.
     * @return Ответ с обновленным продуктом и статусом HTTP 200 (OK).
     */
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID productId, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(productId, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    /**
     * Удаляет продукт с заданным идентификатором.
     * @param productId Идентификатор продукта для удаления.
     * @return Ответ без содержимого и статусом HTTP 204 (NO CONTENT).
     */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
