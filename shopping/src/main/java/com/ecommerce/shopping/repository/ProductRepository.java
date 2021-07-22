package com.ecommerce.shopping.repository;

import com.ecommerce.shopping.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@CrossOrigin
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("select p from Product p where p.productCategory.id = :id")
    Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);

    Page<Product> findByNameContaining(@RequestParam("searchString") String searchString, Pageable pageable);
}
