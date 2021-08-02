package com.ecommerce.shopping.repository;

import com.ecommerce.shopping.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "states",path="states")
public interface StateRepository extends JpaRepository<State,Integer> {

    List<State> findByCountryCode(@Param("code") String code);
}
