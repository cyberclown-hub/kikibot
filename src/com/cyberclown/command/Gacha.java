package com.cyberclown.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gacha{

	private static final Random random = new Random();
	private static final List<GachaItem> common = new ArrayList<>();
	private static final List<GachaItem> rare = new ArrayList<>();
	private static final List<GachaItem> legendary = new ArrayList<>();

	static{

	}

	public static void addItem( GachaItem item ){

		switch( item.getRarity() ){

		case COMMON -> common.add( item );
		case RARE -> rare.add( item );
		case LEGENDARY -> legendary.add( item );
		default ->
			throw new IllegalArgumentException( "Unexpected value: " + item.getRarity() );

		}

	}

	public static GachaItem roll(){

		double res = random.nextDouble();
		if( res < 0.006 )
			return getRandom( legendary );
		if( res < 0.06 )
			return getRandom( rare );
		return getRandom( common );

	}

	private static GachaItem getRandom( List<GachaItem> items ){

		return items.get( random.nextInt( items.size() ) );

	}

}
