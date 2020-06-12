package br.com.ecoleta.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.com.ecoleta.model.Item;
import br.com.ecoleta.model.Point;
import br.com.ecoleta.repository.ItemRepository;

public class PointForm {

	@NotBlank
	private String name;
	@NotBlank
	private String whatsapp;
	@NotBlank @Email
	private String email;
	@NotNull
	private Double longitude;
	@NotNull
	private Double latitude;
	@NotBlank
	private String city;
	@NotBlank @Size(max = 2)
	private String uf;
	@NotBlank
	private String items;

	@NotNull
	private MultipartFile image;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public static List<Integer> toIntegerList(String items) {
		if (items.isEmpty() || items == null) {
			return null;
		}
		return ((List<String>) Arrays.asList(items.split(","))).stream().map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	public Point convert(ItemRepository itemRepository) {
		List<Integer> list = toIntegerList(this.items);
		List<Item> items = itemRepository.findAllById(list);
		return new Point(this, items);
	}

}
