package com.capgemini.productapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.exception.ProductNotFoundException;
import com.capgemini.productapp.repository.ProductRepository;
import com.capgemini.productapp.service.ProductService;
import com.capgemini.productapp.service.impl.ProductServiceImpl;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductServiceTest {
	
	private MockMvc mockMvc;
	
	@Mock
	public ProductRepository productRepository;
	
	@InjectMocks
	public ProductServiceImpl productServiceImpl;
	
	@Before
	public void setUP() {
	MockitoAnnotations.initMocks(this);
	mockMvc = MockMvcBuilders.standaloneSetup(productServiceImpl).build();
	}
	
	@Test
	public void testAddProduct() {
		Product product = new Product(2, "nokia", "phone", 5000);
		when(productRepository.save(product)).thenReturn(product);
		Product addProduct = productServiceImpl.addProduct(product);
		assertEquals(2, addProduct.getProductId());
	}
	
	@Test
	public void testUpdateProduct() {
		Product product = new Product(2, "samsung", "phone", 5000);
		when(productRepository.save(product)).thenReturn(product);
		Product updateProduct = productServiceImpl.updateProduct(product);
		assertEquals(2, updateProduct.getProductId());
	}

     @Test
	public void testdeleteproduct() {
		Product product = new Product(2, "samsung", "phone", 5000);
		productServiceImpl.delete(product);

	}
     
     @Test
 	public void testFindProductById() throws ProductNotFoundException {
 		Product product = new Product(12, "Munch", "chocolates", 10.0);
 		Optional<Product> product1 = Optional.of(product);

 		when(productRepository.findById(product.getProductId())).thenReturn(product1);
 		assertEquals(productServiceImpl.findProductById(product.getProductId()), product);
 	}
	
}