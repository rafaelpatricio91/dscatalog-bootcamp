package com.rafa.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafa.dscatalog.dto.CategoryDTO;
import com.rafa.dscatalog.entities.Category;
import com.rafa.dscatalog.repository.CategoryRepository;
import com.rafa.dscatalog.services.exceptions.DatabaseException;
import com.rafa.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRep;
	
	@Transactional(readOnly = true) //garante a transação - como é so leitura, readonly true melhora a performance
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
		Page<Category> list = categoryRep.findAll(pageRequest);
		
		return list.map(x -> new CategoryDTO(x));
	}
	
	@Transactional(readOnly = true) //garante a transação - como é so leitura, readonly true melhora a performance
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = categoryRep.findById(id);
		//tenta pegar o obj. Senao conseguir lança exceção.
		Category category = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found") );
		
		return new CategoryDTO(category);
	}
	
	@Transactional
	public CategoryDTO insert(CategoryDTO cat) {
		Category entity = new Category();
		entity.setName(cat.getName() );
		entity = categoryRep.save(entity);
		
		return new CategoryDTO(entity);
	}
	
	@Transactional
	public CategoryDTO update(CategoryDTO cat, Long id) {
		try {
			//getOne nao toca no bd - por isso nao usamos findById
			Category entity = categoryRep.getOne(id);
			entity.setName(cat.getName() );
			entity = categoryRep.save(entity);
			return new CategoryDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found - "+id);
		}
		
	}

	public void delete(Long id) {
		try {
			categoryRep.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found - "+id);
		}
		catch (DataIntegrityViolationException e) {
			//catch para caso apague categoria que tenha produtos associados
			throw new DatabaseException("Integrity violation");
		}
	}
}
