package com.cyberclown.bot;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class ApplicationRootController{

	private final ApplicationModel model;

	public ApplicationRootController( JFrame frame ){

		model = new ApplicationModel();
		frame.setContentPane( new MainPanel( model, this ) );
		frame.addWindowListener( new WindowAdapter(){

			@Override
			public void windowClosing( WindowEvent e ){

				stopBot();

			}

		} );

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
