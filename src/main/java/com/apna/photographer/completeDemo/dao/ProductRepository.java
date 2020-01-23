package com.apna.photographer.completeDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apna.photographer.completeDemo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	
}
 