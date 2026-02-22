package com.cyberclown.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemeRepository{

	private static final Random random = new Random();
	private static final List<String> memes = new ArrayList<>();
	
	static{
		
		addMeme( "При входе в зетку выпало 20 полихромов - десять сундуков подумал кикпог" );
		addMeme( "Идет кикпог по лесу - видит хуй машико, а он ему как раз" );
		addMeme( "Кеша дал мут кикпогу, \"проиграл 50/50\" подумал машико" );
		
	}

	public static String next(){

		if( memes.size() == 0 )
			return "мемов нет";

		return memes.get( random.nextInt( memes.size() ) );

	}

	public static void addMeme( String meme ){

		memes.add( meme );

	}

}
