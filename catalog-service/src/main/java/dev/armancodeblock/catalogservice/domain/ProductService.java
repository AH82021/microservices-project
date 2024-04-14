package dev.armancodeblock.catalogservice.domain;

import dev.armancodeblock.catalogservice.ApplicationProperties;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final ApplicationProperties applicationProperties;

    public ProductService(ProductRepository productRepository, ApplicationProperties applicationProperties) {
        this.productRepository = productRepository;
        this.applicationProperties = applicationProperties;
    }

    public PageResult<Product> getProducts(int pageNo){
        Sort sort = Sort.by("name").ascending();
        pageNo = pageNo <=1 ? 0 : pageNo -1;
        Pageable pageable = PageRequest.of(pageNo, applicationProperties.pageSize(),sort);
        Page<Product> productPage = productRepository.findAll(pageable).map(ProductMapper::toProduct);

        return new PageResult<>(
                productPage.getContent(),
                productPage.getTotalElements(),
                productPage.getNumber() +1,
                productPage.getTotalPages(),
                productPage.isFirst(),
                productPage.isLast(),
                productPage.hasNext(),
                productPage.hasPrevious()
        );

    }
    public Optional<Product> getProductByCode(String code){
        return productRepository.findByCode(code).map(ProductMapper::toProduct);
    }
}
