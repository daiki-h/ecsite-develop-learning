package com.example.ecsitedeveloplearning.ec.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecsitedeveloplearning.ec.shop.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
