package com.example.mvc_teamwork.service.product;

import com.example.mvc_teamwork.entity.Category;
import com.example.mvc_teamwork.entity.Product;
import com.example.mvc_teamwork.entity.ProductDTO;
import com.example.mvc_teamwork.repository.CategoryRepository;
import com.example.mvc_teamwork.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public Product findByName(String name) {
        Optional<Product> product = productRepository.findByName(name);
        return product.isPresent()?product.get():null;
    }
    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> list = new ArrayList<>();
        for(Product product : productList){
            list.add(productToDTO(product));
        }
        return list;
    }
    @Override
    public Product addProduct(ProductDTO productDTO) {
        Product product = DTOtoProduct(productDTO);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public void changeProduct(ProductDTO productDTO, int id) {
        Product product = DTOtoProduct(productDTO);
        String name = product.getName();
        int price = product.getPrice();
        productRepository.updateProductNameById(name,id);
        productRepository.updateProductPriceById(price,id);
    }

    @Override
    public Product DTOtoProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId()).get();
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setCategoryId(category);
        return product;
    }

    @Override
    public ProductDTO productToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        Category category = product.getCategoryId();
        productDTO.setCategoryId(category.getId());
        return productDTO;
    }
}
