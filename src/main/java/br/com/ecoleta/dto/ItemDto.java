package br.com.ecoleta.dto;

import br.com.ecoleta.model.Item;

public class ItemDto {

	private Integer id;
	private String title;
	private String image;
	
	public ItemDto(Item item, String path) {
		this.id = item.getId();
		this.title = item.getTitle();
		this.image = path.concat(item.getImage());
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getImage() {
		return image;
	}

}
