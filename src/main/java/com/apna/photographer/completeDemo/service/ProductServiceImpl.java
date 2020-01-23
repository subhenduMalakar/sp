package com.apna.photographer.completeDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apna.photographer.completeDemo.dao.ProductRepository;
import com.apna.photographer.completeDemo.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository theProductRepository) {
		this.productRepository = theProductRepository;
	}

	@Transactional
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Transactional
	public Product findById(int theId) {

		Optional<Product> result = productRepository.findById(theId);

		Product theProduct = null;

		if (result.isPresent()) {
			theProduct = result.get();
		} else {
			// we did not find the employee
			throw new RuntimeException("Did not find Product Id - " + theId);
		}
		return theProduct;
	}

	
	@Transactional
	public void save(Product theProduct) {
		productRepository.save(theProduct);

	}

	@Transactional
	public void deleteById(int theId) {
		productRepository.deleteById(theId);
	}

}
