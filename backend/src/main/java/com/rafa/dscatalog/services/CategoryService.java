package com.rafa.dscatalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafa.dscatalog.dto.CategoryDTO;
import com.rafa.dscatalog.entities.Category;
import com.rafa.dscatalog.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRep;
	
	@Transactional(readOnly = true) //garante a transação - como é so leitura, readonly true melhora a performance
	public List<CategoryDTO> findAll() {
		List<Category> list = categoryRep.findAll();
		
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList() );
	}
}
