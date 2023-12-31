package com.pratibha.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratibha.ecommerce.entity.Product;
import com.pratibha.ecommerce.entity.SearchCriteria;
import com.pratibha.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product getProductById(Integer productId) {
		Product product = null;
		try {
			product = productRepository.findById(productId).get();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return product;
		
	}
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public List<Product> getProductsByCategory(String category) {
		return productRepository.getProductsByCategory(category);
	}

	public List<Product> getProductsUsingSearchKey(String searchKey) {
		return productRepository.getProductsBySearchKey(searchKey);
	}

	public List<Product> getProductsUsingCategoryAndSearchKey(String category, String searchKey) {
		return productRepository.getProductsUsingCategoryAndSearchKey(category, searchKey);
	}

	public List<Product> sortUsingCriteria(List<Product> products, String criteria) {
		if(criteria.equals("PriceLowToHigh")) {
			return products.stream().sorted((Product p1, Product p2) -> Float.compare(p1.getPrice(), p2.getPrice())).collect(Collectors.toList());
		}
        if(criteria.equals("PriceHighToLow")) {
        	return products.stream().sorted((Product p1, Product p2) -> Float.compare(p2.getPrice(), p1.getPrice())).collect(Collectors.toList());
		}
		return products;
	}

}
