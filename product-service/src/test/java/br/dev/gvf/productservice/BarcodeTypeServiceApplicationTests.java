package br.dev.gvf.productservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BarcodeTypeServiceApplicationTests {

    @Value("${productService.rest.response.pagination.pageSize}")
    private Integer pageSize;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(pageSize);
        Assertions.assertEquals(10, pageSize);
    }

}
