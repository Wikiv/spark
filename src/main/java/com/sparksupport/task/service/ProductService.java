package com.sparksupport.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


import com.sparksupport.task.custom.exception.BusinessException;
import com.sparksupport.task.entity.Product;
import com.sparksupport.task.entity.Sale;
import com.sparksupport.task.repos.ProductRepo;
import com.sparksupport.task.repos.SaleRepo;



@Service
public class ProductService implements ProductServiceInterface{
	
	@Autowired
	private ProductRepo repo;
	@Autowired
	private SaleRepo salerepo;

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public Product addProduct(Product product) {
		if(product.getName().isEmpty() || product.getName().length() == 0 ) {
			throw new BusinessException("601","Please send proper name, It's blank");
		}
		try {
		Product savedProduct = repo.save(product);
		return savedProduct;
		}catch (IllegalArgumentException e) {
			throw new BusinessException("602","given product is null " + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("603","Something went wrong while saving the product " + e.getMessage());
		}

		
	}

	@Override
	public Product getProductById(int productid) {
		// TODO Auto-generated method stub
		try {
		return repo.findById(productid).get();
		}catch (IllegalArgumentException e) {
			throw new BusinessException("606","given product id is null, please send some id to be searched" + e.getMessage());
		}
		catch (java.util.NoSuchElementException e) {
			throw new BusinessException("607","given product id doesnot exist in DB " + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("609","Something went wrong while fetching all the products " + e.getMessage());
		}
		
	}


	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public Product updateProduct(int productid, Product product) {
		// TODO Auto-generated method stub
		if(product.getName().isEmpty() || product.getName().length() == 0 ) {
			throw new BusinessException("601","Please add proper name, It's blank");
		}
		try {
		Product savedProduct = repo.save(product);
		return savedProduct;
		}catch (IllegalArgumentException e) {
			throw new BusinessException("602","given product is null" + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("603","Something went wrong while saving the product" + e.getMessage());
		}

		
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> prodList = null;
		// TODO Auto-generated method stub
		try {
			prodList = repo.findAll();
		}
		catch (Exception e) {
			throw new BusinessException("605","Something went wrong while fetching all the products" + e.getMessage());
		}
		if(prodList.isEmpty())
			throw new BusinessException("604", "empty records");
		return prodList;
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")	
	public void deleteProduct(int productid) {
		try {
		repo.deleteById(productid);
	}catch (IllegalArgumentException e) {
		throw new BusinessException("608","given product id is null, please send some id to be deleted" + e.getMessage());
	}catch (Exception e) {
		throw new BusinessException("610","Something went wrong while fetching all products" + e.getMessage());
	}
	}

	@Override
    public int getRevenueByProduct(int productId) {
        return salerepo.getRevenueByProductId(productId);
    }
	@Override
    public int getTotalRevenue() {
        return salerepo.findAll().stream()
                .mapToInt(sale -> sale.getProduct().getPrice() * sale.getQuantity())
                .sum();
    }

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public Sale addsaleProduct(Sale sale) {
		// TODO Auto-generated method stub
		try {
			Sale savedProduct = salerepo.save(sale);
			return savedProduct;
			}catch (IllegalArgumentException e) {
				throw new BusinessException("602","given sale is null " + e.getMessage());
			}catch (Exception e) {
				throw new BusinessException("603","Something went wrong while saving the sale " + e.getMessage());
			}
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public Sale updateSaleProduct(int productId, Sale sale) {
		// TODO Auto-generated method stub
		try {
			Sale savedProduct = salerepo.save(sale);
			return savedProduct;
			}catch (IllegalArgumentException e) {
				throw new BusinessException("602","given sale is null " + e.getMessage());
			}catch (Exception e) {
				throw new BusinessException("603","Something went wrong while saving the sale " + e.getMessage());
			}
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteSaleProduct(int productId) {
		// TODO Auto-generated method stub
		try {
			salerepo.deleteById(productId);
		}catch (IllegalArgumentException e) {
			throw new BusinessException("608","given sale id is null, please send some id to be deleted" + e.getMessage());
		}catch (Exception e) {
			throw new BusinessException("610","Something went wrong while fetching all sales" + e.getMessage());
		}
	}

	@Override
	public Page<Product> getAllProducts(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return repo.findAll(pageRequest);
	}

	

}
