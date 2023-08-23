package com.example.mvc_teamwork.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.mvc_teamwork.entity.Category;
import com.example.mvc_teamwork.entity.Product;
import com.example.mvc_teamwork.entity.ProductDTO;
import com.example.mvc_teamwork.jasper.ProductReportAdmin;
import com.example.mvc_teamwork.repository.CategoryRepository;
import com.example.mvc_teamwork.repository.ProductRepository;
import com.example.mvc_teamwork.service.product.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductReportAdmin productReportAdmin;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByName() {
        String productName = "Test Product";
        Product mockProduct = new Product();
        mockProduct.setId(1);
        mockProduct.setName(productName);

        when(productRepository.findByName(productName)).thenReturn(Optional.of(mockProduct));

        Product foundProduct = productService.findByName(productName);
        assertThat(foundProduct).isEqualTo(mockProduct);
    }


    @Test
    void testAddProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Test Product");
        productDTO.setPrice(100);
        productDTO.setCategoryId(1);

        Category mockCategory = new Category();
        mockCategory.setId(1);

        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(mockCategory));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> {
            Product product = invocation.getArgument(0);
            product.setId(1);
            return product;
        });

        Product addedProduct = productService.addProduct(productDTO);
        assertThat(addedProduct).isNotNull();
        assertThat(addedProduct.getId()).isEqualTo(1);
    }

    @Test
    void testDeleteProduct() {
        int productId = 1;

        productService.deleteProduct(productId);

        verify(productRepository).deleteById(productId);
    }

    @Test
    void testChangeProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Updated Name");
        productDTO.setPrice(200);
        productDTO.setCategoryId(1);

        Category mockCategory = new Category();
        mockCategory.setId(1);

        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(mockCategory));

        productService.changeProduct(productDTO, 1);

        verify(productRepository).updateProductNameById(anyString(), anyInt());
        verify(productRepository).updateProductPriceById(anyInt(), anyInt());
    }

    @Test
    void testDTOtoProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Test Product");
        productDTO.setPrice(100);
        productDTO.setCategoryId(1);

        Category mockCategory = new Category();
        mockCategory.setId(1);

        when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(mockCategory));

        Product product = productService.DTOtoProduct(productDTO);
        assertThat(product.getName()).isEqualTo(productDTO.getName());
        assertThat(product.getPrice()).isEqualTo(productDTO.getPrice());
        assertThat(product.getCategoryId()).isEqualTo(mockCategory);
    }

    @Test
    void testProductToDTO() {
        Product product = new Product();
        product.setId(1);
        product.setName("Test Product");
        product.setPrice(100);

        Category mockCategory = new Category();
        mockCategory.setId(1);
        product.setCategoryId(mockCategory);

        ProductDTO productDTO = productService.productToDTO(product);
        assertThat(productDTO.getId()).isEqualTo(product.getId());
        assertThat(productDTO.getName()).isEqualTo(product.getName());
        assertThat(productDTO.getPrice()).isEqualTo(product.getPrice());
        assertThat(productDTO.getCategoryId()).isEqualTo(mockCategory.getId());
    }
}

