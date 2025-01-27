package com.ecommerce.project.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("api/public/categories")
	public List<Category> getAllCategories(){
		return categoryService.getAllCategories();
	}
	@PostMapping("api/public/categories")
	public String createCategory(@RequestBody Category category) {
	    System.out.println("Received category: " + category);
	    categoryService.createCategory(category);
	    return "Category Added Successfully";
	}
	
	@DeleteMapping("api/admin/categories/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
		try {
			String status=categoryService.deleteCategory(categoryId);
			return new ResponseEntity<>(status,HttpStatus.OK);
		}catch(ResponseStatusException e){
			return new ResponseEntity<>(e.getReason(),e.getStatusCode());
		}
		
	}
	
}
