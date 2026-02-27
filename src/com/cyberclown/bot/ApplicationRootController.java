package com.cyberclown.bot;

import javax.swing.JFrame;

import com.cyberclown.gui.IconService;
import com.cyberclown.gui.MainPanel;
import com.cyberclown.gui.TrayService;

public class ApplicationRootController{

	private final ApplicationModel model;

	public ApplicationRootController( JFrame frame ){

		model = new ApplicationModel();
		frame.setContentPane( new MainPanel( model, this ) );
		new IconService().apply( frame );
		new TrayService().apply( frame, this, model );

		Runtime.getRuntime().addShutdownHook( new Thread( this::stopBot ) );

	}

	public void runBot(){

		if( model.isBotActive() )
			return;

		try{

			model.newJda();
			System.out.println( "bot run" );

		}catch( InterruptedException e ){

			System.err.println( "bot run with exception" );

		}

	}

	public void stopBot(){

		if( ! model.isBotActive() )
			return;

		model.shutdownJda();
		System.out.println( "bot stopped" );

	}

	public void sendMessage( String channel, String pings, String message ){

		if( model.isBotActive() )
			if( pings.length() == 0 )
				new BotService( model.getJda() ).sendMessage( Long.parseLong( channel ), message );
			else
				new BotService( model.getJda() ).pingUsers( Long.parseLong( channel ), pings, message );

	}

}
