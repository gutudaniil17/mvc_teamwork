package com.example.mvc_teamwork.jasper;

import com.example.mvc_teamwork.entity.Category;
import com.example.mvc_teamwork.entity.Product;
import com.example.mvc_teamwork.entity.ProductDTO;
import com.example.mvc_teamwork.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductReportAdmin {

    private final ProductRepository repository;
    private JRDataSource dataSource;

    public void fillReport(File jrxmlTemplate, File fileToBeImported) throws JRException {
        JasperReport report = JasperCompileManager.compileReport(jrxmlTemplate.getAbsolutePath());
        dataSource = new JRBeanCollectionDataSource(repository.findAll());
        JasperPrint print = JasperFillManager.fillReport(report, null, dataSource);
        JasperExportManager.exportReportToPdfFile(print, fileToBeImported.getAbsolutePath());
    }

    private JRDataSource createDataSourceFromDB(){
        List<Product> productList = repository.findAll();
        List<ProductDTO> list = new ArrayList<>();
        for(Product product : productList){
            list.add(productToDTO(product));
        }
        return new JRBeanCollectionDataSource(list);
    }

    public ProductDTO productToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setAvailableQuantity(product.getAvailableQuantity());
        Category category = product.getCategoryId();
        productDTO.setCategoryId(category.getId());
        return productDTO;
    }



}
