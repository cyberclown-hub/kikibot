package com.cyberclown.bot;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class BotLauncher{

	public static void main( String[] args ) throws Exception{

		try{

			UIManager.setLookAndFeel( new FlatIntelliJLaf() );

		}catch( Exception ignored ){}

		SwingUtilities.invokeLater( () -> {

			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			new ApplicationRootController( frame );
			frame.pack();
			frame.setVisible( true );

		} );

	}

}
