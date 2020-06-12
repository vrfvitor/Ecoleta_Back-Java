package br.com.ecoleta.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecoleta.dto.PointDto;
import br.com.ecoleta.dto.PointForm;
import br.com.ecoleta.dto.SimplePointDto;
import br.com.ecoleta.infra.FileSaver;
import br.com.ecoleta.model.Point;
import br.com.ecoleta.repository.ItemRepository;
import br.com.ecoleta.repository.PointRepository;

@RestController
@RequestMapping("/points")
public class PointController {

	@Autowired
	private FileSaver fileSaver;
	
	@Autowired
	private PointRepository pointRepository;

	@Autowired
	private ItemRepository itemRepository;
	
	@Value("${application.path.url}")
	private String pathUrl;

	@GetMapping
	public ResponseEntity<List<SimplePointDto>> index(@RequestParam(value = "uf", required = true) String uf,
													  @RequestParam(value = "city", required = true) String city,
													  @RequestParam(value = "items", required = true) String itemsString) {
		List<Integer> items = PointForm.toIntegerList(itemsString);
		List<SimplePointDto> points = SimplePointDto.convert(pointRepository.findBy(uf, city, items), pathUrl);
		return ResponseEntity.ok(points);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PointDto> show(@PathVariable("id") Integer id) {
		Optional<Point> optional = pointRepository.findById(id);
		if (optional.isPresent()) {
			PointDto pointDto = new PointDto(optional.get(), pathUrl);
			return ResponseEntity.ok(pointDto);
		}
		return ResponseEntity.notFound().build();
	}

	@Transactional
	@PostMapping(consumes = {"multipart/form-data"})
	public ResponseEntity<PointDto> create(@Valid @ModelAttribute PointForm form) {
		String fileName = fileSaver.saveUploadedFile(form.getImage());
		Point point = form.convert(itemRepository);
		point.setImage(fileName);
		pointRepository.save(point);
		return ResponseEntity.ok(new PointDto(point, pathUrl));
	}

}
