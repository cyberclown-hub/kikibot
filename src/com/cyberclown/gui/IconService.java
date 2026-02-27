package com.cyberclown.gui;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class IconService{

	public void apply( JFrame frame ){

		List<Image> icons = List.of(
				new ImageIcon( getClass().getResource( "/resources/app16.png" ) ).getImage(),
				new ImageIcon( getClass().getResource( "/resources/app32.png" ) ).getImage(),
				new ImageIcon( getClass().getResource( "/resources/app48.png" ) ).getImage(),
				new ImageIcon( getClass().getResource( "/resources/app256.png" ) ).getImage() );

		frame.setIconImages( icons );

	}

}
