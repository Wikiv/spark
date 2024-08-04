package com.sparksupport.task.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparksupport.task.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
	
	

}
