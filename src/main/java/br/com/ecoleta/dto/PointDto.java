package br.com.ecoleta.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ecoleta.model.Point;

public class PointDto extends SimplePointDto {

	private String city;
	private String uf;
	private String email;
	private String whatsapp;
	private List<String> items;

	public PointDto(Point point, String pathUrl) {
		super(point, pathUrl);
		this.uf = point.getUf();
		this.city = point.getCity();
		this.email = point.getEmail();
		this.whatsapp = point.getWhatsapp();
		this.items = point.getItems().stream().map(item -> item.getTitle()).collect(Collectors.toList());
	}

	public String getEmail() {
		return email;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public String getCity() {
		return city;
	}

	public String getUf() {
		return uf;
	}

	public List<String> getItems() {
		return items;
	}

}
