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
import com.rafa.dscatalog.entities.Product;
import com.rafa.dscatalog.entities.ProductDTO;
import com.rafa.dscatalog.repository.CategoryRepository;
import com.rafa.dscatalog.repository.ProductRepository;
import com.rafa.dscatalog.services.exceptions.DatabaseException;
import com.rafa.dscatalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> list = repository.findAll(pageRequest);
		return list.map(x -> new ProductDTO(x) );
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product p = obj.orElseThrow( () -> new ResourceNotFoundException("Entity Not Found") );
		
		return new ProductDTO(p,p.getCategories());
	}
	
	@Transactional
	 public ProductDTO insert(ProductDTO dto) {
		 Product product = new Product();
		 copyDtoToEntity(dto, product);
		 repository.save(product);
		 
		 return new ProductDTO(product);
	 }
	

	@Transactional
	public ProductDTO update(ProductDTO dto, Long id) {
		try {
			Product product = repository.getOne(id);
			copyDtoToEntity(dto, product);
			repository.save(product);
			
			return new ProductDTO(product);
		} catch (EntityNotFoundException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found - "+id);
		}
		catch (DataIntegrityViolationException e) {
			//catch para caso apague categoria que tenha produtos associados
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private void copyDtoToEntity(ProductDTO dto, Product product) {
		product.setName(dto.getName() );
		product.setDescription(dto.getDescription() );
		product.setPrice(dto.getPrice() );
		product.setImgUrl(dto.getImgUrl() );
		product.setDate(dto.getDate() );
		
		product.getCategories().clear();
		for (CategoryDTO cat : dto.getCategories()) {
			Category c = categoryRepository.getOne(cat.getId() );
			product.getCategories().add(c);
		}
	}
}
