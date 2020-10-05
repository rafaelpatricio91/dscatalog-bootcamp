package com.rafa.dscatalog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafa.dscatalog.entities.Category;
import com.rafa.dscatalog.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRep;
	
	public List<Category> findAll() {
		return categoryRep.findAll();
	}
}