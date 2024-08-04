package com.sparksupport.task.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.sparksupport.task.entity.Product;
import com.sparksupport.task.entity.Sale;

public interface ProductServiceInterface {

	public Product addProduct(Product product);
	
	public Product updateProduct (int productId, Product product);

	public Page<Product> getAllProducts(PageRequest pageRequest);

	public Product getProductById(int productId);

	public void deleteProduct(int productId);
	
    public Sale addsaleProduct(Sale sale);
	
	public Sale updateSaleProduct (int productId, Sale sale);

	public void deleteSaleProduct(int productId);
	
	public int getTotalRevenue();
	
	public int getRevenueByProduct(int productId);

	List<Product> getAllProducts();

}
