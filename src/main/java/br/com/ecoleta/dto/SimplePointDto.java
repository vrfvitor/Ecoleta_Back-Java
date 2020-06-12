package br.com.ecoleta.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ecoleta.model.Point;

public class SimplePointDto {

	private Integer id;
	private String name;
	private double latitude;
	private double longitude;
	private String image_url;

	public SimplePointDto(Point point, String pathUrl) {
		this.id = point.getId();
		this.name = point.getName();
		this.latitude = point.getLatitude();
		this.longitude = point.getLongitude();
		this.image_url = pathUrl.concat(point.getImage());
	}

	public Integer getId() {
		return id;
	}

	public String getImage_url() {
		return image_url;
	}

	public String getName() {
		return name;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}
	
	public static List<SimplePointDto> convert(List<Point> points, String pathUrl) {
		return points.stream().map(point -> new SimplePointDto(point, pathUrl) ).collect(Collectors.toList());
	}

}
