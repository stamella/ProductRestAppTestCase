package com.capgemini.productapp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.productapp.controller.ProductController;
import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductControllerTest {
	
	@InjectMocks
	private ProductController productController;
	
	@Mock
	private ProductService productService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}
	
	@Test
	public void testaddProduct() throws Exception {
		
		String content = "{\"productId\":1,\"productName\":\"kitkat\",\"productCategory\":\"chocos\",\"productPrice\":5000.0}";

		when(productService.addProduct(Mockito.isA(Product.class))).thenReturn(new Product(1, "kitkat", "chocos", 5000.0));
			mockMvc.perform(post("/product")

			.contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andDo(print())
			.andExpect(jsonPath("$.productId").value(1));
		
	}
	
	@Test
	public void testUpdateProduct() throws Exception {
		String content = "{ \"productId\": 1,\"productName\": \"DairyMilk\",\"productCategory\": \"chocos\",\"productPrice\": 1.0}";

		when(productService.updateProduct(Mockito.isA(Product.class))).thenReturn(new Product(1, "DairyMilk", "chocos", 1.0));
		when(productService.findProductById(1)).thenReturn(new Product(1, "DairyMilk", "chocos", 1.0));
		mockMvc.perform(put("/product")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(content)).andDo(print())
				.andExpect(jsonPath("$.productId").value("1"));

	}
	
	
	@Test
	public void testfindproductbyId() throws Exception {
		when(productService.findProductById(12)).thenReturn(new Product(12, "munch", "chocolates", 10.0));
		mockMvc.perform(get("/products/12").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
				.andExpect(jsonPath("$.productId").exists())
				.andExpect(jsonPath("$.productCategory").exists())
				.andExpect(jsonPath("$.productName").exists())
				.andExpect(jsonPath("$.productPrice").exists())
				.andExpect(jsonPath("$.productId").value(12))
				.andExpect(jsonPath("$.productCategory").value("chocolates"))
				.andExpect(jsonPath("$.productName").value("munch"))
				.andExpect(jsonPath("$.productPrice").value(10.0));

	}
	
	@Test
	public void testDelete() throws Exception {
		when(productService.findProductById(13)).thenReturn(new Product(13, "apple", "fruits", 20.0));
		mockMvc.perform(delete("/products/13")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	


}
