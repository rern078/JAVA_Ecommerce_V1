package com.example.firstProject.ecommerce.service;

import com.example.firstProject.ecommerce.dto.CreateCategoryRequest;
import com.example.firstProject.ecommerce.dto.UpdateCategoryRequest;
import com.example.firstProject.ecommerce.exception.NotFoundException;
import com.example.firstProject.ecommerce.model.Category;
import com.example.firstProject.ecommerce.repository.CategoryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Category> listCategories() {
		return categoryRepository.findAll();
	}

	public Category getCategory(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Category not found: " + id));
	}

	public Category createCategory(CreateCategoryRequest request) {
		Category category = new Category(request.categoryName());
		category.setDescription(request.description());
		category.setPicture(request.picture());
		category.setActive(request.active());
		return categoryRepository.save(category);
	}

	public Category updateCategory(Long id, UpdateCategoryRequest request) {
		Category category = getCategory(id);
		category.setCategoryName(request.categoryName());
		category.setDescription(request.description());
		category.setPicture(request.picture());
		category.setActive(request.active());
		return categoryRepository.save(category);
	}

	public void deleteCategory(Long id) {
		Category category = getCategory(id);
		categoryRepository.delete(category);
	}
}
