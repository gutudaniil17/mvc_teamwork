package com.example.mvc_teamwork.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import com.example.mvc_teamwork.controller.ProductController;
import com.example.mvc_teamwork.entity.Product;
import com.example.mvc_teamwork.entity.ProductDTO;
import com.example.mvc_teamwork.service.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindProductByName() {
        String productName = "Test Product";
        Product mockProduct = new Product();
        mockProduct.setId(1);
        mockProduct.setName(productName);

        when(productService.findByName(productName)).thenReturn(mockProduct);

        String result = productController.findProductByName(productName);
        assertThat(result).isEqualTo(mockProduct.toString());
    }

    @Test
    void testAddProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Test Product");

        String result = productController.addProduct(productDTO);
        assertThat(result).isEqualTo("ok");

        verify(productService).addProduct(productDTO);
    }

    @Test
    void testDeleteProduct() {
        int productId = 1;

        String result = productController.deleteProduct(productId);
        assertThat(result).isEqualTo("ok");

        verify(productService).deleteProduct(productId);
    }

    @Test
    void testUpdateProduct() {
        int productId = 1;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Updated Product");

        String result = productController.updateProduct(productId, productDTO);
        assertThat(result).isEqualTo("ok");

        verify(productService).changeProduct(productDTO, productId);
    }

}

