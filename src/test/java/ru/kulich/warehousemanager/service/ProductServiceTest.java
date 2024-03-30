package ru.kulich.warehousemanager.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.kulich.warehousemanager.entity.Product;
import ru.kulich.warehousemanager.exception.ResourceNotFoundException;
import ru.kulich.warehousemanager.repository.ProductRepository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdateProduct() {
        UUID productId = UUID.randomUUID();
        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Existing Product");

        Product updatedProductData = new Product();
        updatedProductData.setName("Updated Product");
        updatedProductData.setArticle("123456");

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        Product updatedProduct = productService.updateProduct(productId, updatedProductData);

        assertNotNull(updatedProduct);
        assertEquals(updatedProductData.getName(), updatedProduct.getName());
        assertEquals(updatedProductData.getArticle(), updatedProduct.getArticle());
        assertEquals(existingProduct.getId(), updatedProduct.getId());
    }

    @Test
    void testDeleteProduct() {
        UUID productId = UUID.randomUUID();
        Product productToDelete = new Product();
        productToDelete.setId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(productToDelete));

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).delete(productToDelete);
    }

    @Test
    void testGetProductByIdNotFound() {
        UUID nonExistingProductId = UUID.randomUUID();

        when(productRepository.findById(nonExistingProductId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(nonExistingProductId));
    }
}
