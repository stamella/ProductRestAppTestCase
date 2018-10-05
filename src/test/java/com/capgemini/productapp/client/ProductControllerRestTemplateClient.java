package com.capgemini.productapp.client;

import org.springframework.web.client.RestTemplate;

import com.capgemini.productapp.entity.Product;

public class ProductControllerRestTemplateClient {
	
	private static final RestTemplate REST_TEMPLATE = new RestTemplate();
	private static final String baseUrl = "http://localhost:7777/";
	
	public static void main(String[] args) {
		
		/*//Adding a new product into database.
		String url = baseUrl + "product";
		Product product = new Product(101, "Apple iPhone X", "Mobile", 100000);
		product = addProduct(url, product);
		System.out.println("After saving product into dababase : " + product);*/
		
		/*//Getting product from database
		String url = baseUrl + "/products/12";
		Product product = findProductById(url);
		System.out.println("Product from DB : " + product);*/
		
		// Deleting product from database
		String url = baseUrl + "products/1";		
		deleteProduct(url);
		
		/*//Updating product into database
		String url = baseUrl + "product";
		Product product = new Product(11, "Apple iPhone X", "Mobile", 95000);
		updateProduct(url, product);
		product = findProductById(baseUrl + "products/11");
		System.out.println(product);*/
	}

	public static void updateProduct(String url, Product product) {
		 REST_TEMPLATE.put(url, product);
		 System.out.println("--success--");
	}

	public static void deleteProduct(String url) {
		REST_TEMPLATE.delete(url);
		System.out.println("--success--");
	}

	public static Product findProductById(String url) {
		return REST_TEMPLATE.getForObject(url, Product.class);
	}

	public static Product addProduct(String url, Product product) {
		Product productAfterSavingIntoDb =  REST_TEMPLATE.postForObject(url, product, Product.class);
		System.out.println("--success--");
		return productAfterSavingIntoDb;
				
	}
}