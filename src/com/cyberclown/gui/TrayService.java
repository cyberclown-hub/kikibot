package com.cyberclown.gui;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.cyberclown.bot.ApplicationModel;
import com.cyberclown.bot.ApplicationRootController;

public class TrayService{

	private JFrame frame;
	private TrayIcon trayIcon;
	private SystemTray tray;

	public void apply( JFrame frame, ApplicationRootController controller, ApplicationModel model ){

		this.frame = frame;
		frame.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );

		frame.addWindowListener( new WindowAdapter(){

			@Override
			public void windowClosing( WindowEvent e ){

				if( shouldMinimizeToTray( model ) )
					minimizeToTray();
				else
					exitApplication();

			}

		} );

		if( SystemTray.isSupported() ){

			tray = SystemTray.getSystemTray();
			Image image = new ImageIcon(
			        getClass().getResource("/resources/tray.png")
					).getImage();

			PopupMenu popup = new PopupMenu();

			MenuItem openItem = new MenuItem( "Open" );
			openItem.addActionListener( e -> restoreFromTray() );

			MenuItem exitItem = new MenuItem( "Exit" );
			exitItem.addActionListener( e -> exitApplication() );

			popup.add( openItem );
			popup.add( exitItem );

			trayIcon = new TrayIcon( image, frame.getTitle(), popup );
			trayIcon.setImageAutoSize( true );

			trayIcon.addActionListener( e -> restoreFromTray() );

		}

	}

	private boolean shouldMinimizeToTray( ApplicationModel model ){

		return model.isBotActive();

	}

	private void minimizeToTray(){

		try{

			tray.add( trayIcon );
			frame.setVisible( false );

		}catch( AWTException ex ){

			ex.printStackTrace();

		}

	}

	private void restoreFromTray(){

		tray.remove( trayIcon );
		frame.setVisible( true );
		frame.setExtendedState( JFrame.NORMAL );
		frame.toFront();

	}

	private void exitApplication(){

		System.exit( 0 );

	}

}
