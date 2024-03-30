package ru.kulich.warehousemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kulich.warehousemanager.entity.Product;
import ru.kulich.warehousemanager.exception.ResourceNotFoundException;
import ru.kulich.warehousemanager.repository.ProductRepository;

import java.util.List;
import java.util.UUID;
import java.util.Date;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        // Установка значений по умолчанию перед сохранением товара, если необходимо
        // Например, установка даты создания
        product.setCreatedAt(new Date());
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product updateProduct(UUID id, Product product) {
        Product existingProduct = getProductById(id);
        // Обновление существующего товара данными из переданного товара
        existingProduct.setName(product.getName());
        existingProduct.setArticle(product.getArticle());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setLastQuantityChange(new Date()); // Установка даты последнего изменения количества
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(UUID id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}
