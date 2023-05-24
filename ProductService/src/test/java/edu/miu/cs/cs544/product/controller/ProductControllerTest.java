package edu.miu.cs.cs544.product.controller;

import edu.miu.cs.cs544.product.domain.Product;
import edu.miu.cs.cs544.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : JOHNNGUYEN
 * @since : 5/24/2023, Wed
 **/
@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mock;

    @MockBean
    ProductService productService;

    @Test
    void getProduct() throws Exception {
        Mockito.when(productService.getProduct(1l)).thenReturn(new Product("IPHONE", 10.00, "IMAGE", "123", 5));
        mock.perform(MockMvcRequestBuilders.get("/product/1")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("IPHONE"));
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void getAllProducts() {
    }
}