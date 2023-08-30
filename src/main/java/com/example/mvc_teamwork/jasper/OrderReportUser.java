package com.example.mvc_teamwork.jasper;

import com.example.mvc_teamwork.entity.Order;
import com.example.mvc_teamwork.entity.Product;
import com.example.mvc_teamwork.entity.ShoppingCart;
import com.example.mvc_teamwork.entity.ShoppingCartDTO;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;


@Component
@RequiredArgsConstructor
public class OrderReportUser {

    private JRDataSource dataSource;

    public void fillReport(File jrxmlTemplate, File fileToBeImported, Order order) throws JRException {
        JasperReport report = JasperCompileManager.compileReport(jrxmlTemplate.getAbsolutePath());
        dataSource = createDataSourceFromOrder(order);
        BigDecimal totalSum = calculateTotalSum(order);
        JasperPrint print = JasperFillManager.fillReport(report, createReportParameters(totalSum), dataSource);
        JasperExportManager.exportReportToPdfFile(print, fileToBeImported.getAbsolutePath());
    }

    private JRDataSource createDataSourceFromOrder(Order order) {
        List<ShoppingCart> cartItems = order.getCartItems();
        List<ShoppingCartDTO> cartDTOList = new ArrayList<>();
        for (ShoppingCart cart : cartItems) {
            ShoppingCartDTO cartDTO = cartToDTO(cart);
            cartDTOList.add(cartDTO);
        }
        return new JRBeanCollectionDataSource(cartDTOList);
    }

    public ShoppingCartDTO cartToDTO(ShoppingCart cart) {
        ShoppingCartDTO cartDTO = new ShoppingCartDTO();
        cartDTO.setProductName(cart.getProductName());
        cartDTO.setQuantity(cart.getQuantity());
        cartDTO.setAmount(cart.getAmount());

        // Calculate total price
        BigDecimal totalPrice = BigDecimal.valueOf(cart.getAmount()).multiply(BigDecimal.valueOf(cart.getQuantity()));
//        cartDTO.set(totalPrice);

        return cartDTO;
    }

    private BigDecimal calculateTotalSum(Order order) {
        BigDecimal totalSum = BigDecimal.ZERO;
        for (ShoppingCart cart : order.getCartItems()) {
            BigDecimal itemTotal = BigDecimal.valueOf(cart.getAmount()).multiply(BigDecimal.valueOf(cart.getQuantity()));
            totalSum = totalSum.add(itemTotal);
        }
        return totalSum;
    }

    private static Map<String, Object> createReportParameters(BigDecimal totalSum) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("totalSum", totalSum);
        return parameters;
    }

}