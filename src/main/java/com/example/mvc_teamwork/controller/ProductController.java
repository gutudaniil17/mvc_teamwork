package com.example.mvc_teamwork.controller;

import com.example.mvc_teamwork.entity.Product;
import com.example.mvc_teamwork.entity.ProductDTO;
import com.example.mvc_teamwork.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/find_by_name_product/{product}")
    public String findProductByName(@PathVariable String product){
        Product product1 = productService.findByName(product);
        return product1 == null ? "No such product" : product1.toString();
    }

    @PostMapping("/admin/add_product")
    public String addProduct(@RequestBody ProductDTO productDTO){
        productService.addProduct(productDTO);
        return "ok";
    }

    @DeleteMapping("/admin/delete_product/{id}")
    public String deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return "ok";
    }

    @PostMapping("/admin/update_product/{id}")
    public String updateProduct(
            @PathVariable int id,
            @RequestBody ProductDTO productDTO
    ){
        productService.changeProduct(productDTO,id);
        return "ok";
    }
}
