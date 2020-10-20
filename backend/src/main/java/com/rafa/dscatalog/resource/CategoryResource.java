package com.rafa.dscatalog.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafa.dscatalog.dto.CategoryDTO;
import com.rafa.dscatalog.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<CategoryDTO> list = new ArrayList<>();
		list = categoryService.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
		CategoryDTO category = new CategoryDTO();
		category = categoryService.findById(id);
		
		return ResponseEntity.ok().body(category);
	}
	
	@PostMapping
	public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO cat) {
		cat = categoryService.insert(cat);
		//cria head location - boas praticas
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();
		
		return ResponseEntity.created(uri).body(cat);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO cat) {
		cat = categoryService.update(cat, id);
		
		return ResponseEntity.ok().body(cat);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<CategoryDTO> update(@PathVariable Long id) {
		categoryService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}
