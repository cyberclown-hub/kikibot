package com.cyberclown.bot;

import com.cyberclown.basic.CommandRegistry;
import com.cyberclown.basic.EventRouter;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class ApplicationModel{

	private boolean botActive;
	private final String token = "MTQ3NDg4MzYyNTkxMTcxNzk3OA.GHj0B7.xI0yG6bYM9Cf6oAkoVoFHGKU0yHViqF6Ry1cb4";
	private final String GUILD_ID = "1391081808078307438";
	private JDA jda;

	public ApplicationModel(){

	}

	public void setBotActive( boolean botActive ){

		this.botActive = botActive;

	}

	public boolean isBotActive(){

		return botActive;

	}

	public String getToken(){

		return token;

	}

	public void newJda() throws InterruptedException{

		jda = JDABuilder.createDefault( token )
				.addEventListeners( new EventRouter() )
				.build();

		jda.awaitReady();
		CommandRegistry.aplly( jda, GUILD_ID );

		botActive = true;

	}

	public void shutdownJda(){

		if( jda != null )
			jda.shutdown();
		botActive = false;
		jda = null;

	}

	public JDA getJda(){

		return jda;

	}

}
