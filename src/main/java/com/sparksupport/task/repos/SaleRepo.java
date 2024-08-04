package com.sparksupport.task.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sparksupport.task.entity.Sale;

@Repository
public interface SaleRepo extends JpaRepository<Sale,Integer> {
	
	
    @Query("SELECT COALESCE(SUM(s.product.price * s.quantity), 0) FROM Sale s WHERE s.product.id = :productId")
    int getRevenueByProductId(@Param("productId") int productId);



	
}
