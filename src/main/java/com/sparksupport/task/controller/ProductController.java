package com.sparksupport.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparksupport.task.custom.exception.BusinessException;
import com.sparksupport.task.custom.exception.ControllerException;
import com.sparksupport.task.entity.Product;
import com.sparksupport.task.entity.Sale;
import com.sparksupport.task.repos.ProductRepo;
import com.sparksupport.task.service.ProductServiceInterface;


@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	private ProductServiceInterface productServiceInterface;
	
	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestBody Product product){
		try {
		Product productSaved = productServiceInterface.addProduct(product);
		return new ResponseEntity<Product>(productSaved, HttpStatus.CREATED);
	}catch (BusinessException e) {
		ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
		return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
	}catch (Exception e) {
		ControllerException ce = new ControllerException("611","Something went wrong in controller");
		return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
	}
	}
	 @GetMapping("/product")
	    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page,
	                                                        @RequestParam(defaultValue = "10") int size) {
	        Page<Product> productPage = productServiceInterface.getAllProducts(PageRequest.of(page, size));
	        List<Product> products = productPage.getContent();
	        return ResponseEntity.ok(products);
	    }
	
	
	@GetMapping("/product/{productid}")
	public ResponseEntity<?> getProductById(@PathVariable("productid") int prodid){
		try {
		Product prodRetrieved = productServiceInterface.getProductById(prodid);
		return new ResponseEntity<Product>(prodRetrieved, HttpStatus.OK);
		}catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(),e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			ControllerException ce = new ControllerException("612","Something went wrong in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/admin/product/{productid}")
	public ResponseEntity<Void> deleteProductById(@PathVariable("productid") int prodid){
		
		productServiceInterface.deleteProduct(prodid);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/admin/product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		Product productSaved = productServiceInterface.addProduct(product);
		return new ResponseEntity<Product>(productSaved, HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/admin/sale")
	public ResponseEntity<Sale> addSale(@RequestBody Sale sale){
		Sale saleSaved = productServiceInterface.addsaleProduct(sale);
		return new ResponseEntity<Sale>(saleSaved, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/admin/sale/{prodid}")
	public ResponseEntity<Void> deleteSaleById(@PathVariable("prodid") int prodid){
		
		productServiceInterface.deleteSaleProduct(prodid);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/admin/sale")
	public ResponseEntity<Sale> updateSale(@RequestBody Sale sale){
		Sale saleSaved = productServiceInterface.addsaleProduct(sale);
		return new ResponseEntity<Sale>(saleSaved, HttpStatus.CREATED);
	}
	@GetMapping("/login")
	    public String login() {
	        return "login";
	}
	@GetMapping("/")
	public String homepage() {
		return "<h1>Welcome</h1>";
	}
	@GetMapping("/user")
	public String userpage() {
		return "<h1>Welcome User</h1>";
	}
	
	@GetMapping("/admin")
	public String adminpage() {
		return "<h1>Welcome Admin</h1>";
	}
	@GetMapping("/revenue/product/{productId}")
    public ResponseEntity<Integer> getRevenueByProduct(@PathVariable int productId) {
        int revenue = productServiceInterface.getRevenueByProduct(productId);
        return new ResponseEntity<>(revenue, HttpStatus.OK);
    }
	@GetMapping("/revenue/total")
    public ResponseEntity<Integer> getTotalRevenue() {
        int totalRevenue = productServiceInterface.getTotalRevenue();
        return new ResponseEntity<>(totalRevenue, HttpStatus.OK);
    }
	
	


}
