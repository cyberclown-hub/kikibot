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

		addItem( new GachaItem( "Кики горничная",
				"https://cyberclown-hub.github.io/kikibot/assets/gacha_item_001.png",
				Rarity.LEGENDARY ) );
		addItem( new GachaItem( "Кики Рыцарь",
				"https://cyberclown-hub.github.io/kikibot/assets/gacha_item_002.png",
				Rarity.LEGENDARY ) );
		addItem( new GachaItem( "Искатель примогемов",
				"https://cyberclown-hub.github.io/kikibot/assets/gacha_item_003.png",
				Rarity.RARE ) );
		addItem( new GachaItem( "Эссенция духа",
				"https://cyberclown-hub.github.io/kikibot/assets/gacha_item_004.png",
				Rarity.COMMON ) );
		addItem( new GachaItem( "Кики колдун",
				"https://cyberclown-hub.github.io/kikibot/assets/gacha_item_005.png",
				Rarity.RARE ) );

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
