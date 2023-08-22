package com.example.mvc_teamwork.service.product;

import com.example.mvc_teamwork.entity.Product;
import com.example.mvc_teamwork.entity.ProductDTO;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getAllProducts();
    Product findByName(String name);
    Product addProduct(ProductDTO productDTO);
    void deleteProduct(int id);
    void changeProduct(ProductDTO productDTO, int id);
    Product DTOtoProduct(ProductDTO productDTO);
    ProductDTO productToDTO(Product product);
    void fillReportToAdmin();
}
