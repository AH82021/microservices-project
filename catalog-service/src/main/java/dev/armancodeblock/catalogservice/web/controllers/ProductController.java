package dev.armancodeblock.catalogservice.web.controllers;

import dev.armancodeblock.catalogservice.domain.PageResult;
import dev.armancodeblock.catalogservice.domain.Product;
import dev.armancodeblock.catalogservice.domain.ProductNotFoundException;
import dev.armancodeblock.catalogservice.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PageResult<Product> getProduct(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
