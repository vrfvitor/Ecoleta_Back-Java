package br.com.ecoleta.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecoleta.dto.ItemDto;
import br.com.ecoleta.repository.ItemRepository;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	private ItemRepository repository;

	@Value("${application.path.url}")
	private String pathUrl;
	
	@GetMapping
	public ResponseEntity<List<ItemDto>> index() {
		List<ItemDto> serializedItems = repository
				.findAll().stream()
				.map(item -> new ItemDto(item, pathUrl))
				.collect(Collectors.toList());
		return ResponseEntity.ok(serializedItems);	
	}
	
}
