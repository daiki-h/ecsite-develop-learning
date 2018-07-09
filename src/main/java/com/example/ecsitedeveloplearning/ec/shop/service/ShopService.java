package com.example.ecsitedeveloplearning.ec.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecsitedeveloplearning.ec.shop.model.Category;
import com.example.ecsitedeveloplearning.ec.shop.model.Product;
import com.example.ecsitedeveloplearning.ec.shop.repository.CategoryRepository;
import com.example.ecsitedeveloplearning.ec.shop.repository.ProductRepository;

@Service
public class ShopService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public List<Category> findCategories() {
		return categoryRepository.findAll();
	}

}
