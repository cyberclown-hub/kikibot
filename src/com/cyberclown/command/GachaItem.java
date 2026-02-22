package com.cyberclown.command;

public class GachaItem{

	private final String name;
	private final String imageUrl;
	private final Rarity rarity;

	public GachaItem( String name, String imageUrl, Rarity rarity ){

		this.name = name;
		this.imageUrl = imageUrl;
		this.rarity = rarity;

	}

	public String getName(){

		return name;

	}

	public String getImageUrl(){

		return imageUrl;

	}

	public Rarity getRarity(){

		return rarity;

	}

}
