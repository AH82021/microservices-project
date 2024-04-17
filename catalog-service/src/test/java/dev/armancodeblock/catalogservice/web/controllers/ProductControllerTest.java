package dev.armancodeblock.catalogservice.web.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import dev.armancodeblock.catalogservice.AbstractIT;
import dev.armancodeblock.catalogservice.domain.Product;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIT {
    @Test
    void shouldReturnProducts() {
        given().contentType(ContentType.JSON)
                .when()
                .get("api/v1/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("totalElements", is(15))
                .body("pageNumber", is(1))
                .body("totalPages", is(2))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));
    }

    @Test
    void shouldGetProductByCode() {
        Product product = given().contentType(ContentType.JSON)
                .when()
                .get("api/v1/products/{code}", "P100")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(Product.class);
    }

    @Test
    void shouldReturnsNotFoundWhenProductCodeNotExists() {
        String code = "Wrong_product_code";
        given().contentType(ContentType.JSON)
                .when()
                .get("api/v1/products/{code}", code)
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("title", is("Product Not Found"))
                .body("detail", is("Product with code " + code + " not found"));
    }
}
