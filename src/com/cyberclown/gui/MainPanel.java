package com.cyberclown.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.cyberclown.basic.NumericTextField;
import com.cyberclown.bot.ApplicationModel;
import com.cyberclown.bot.ApplicationRootController;

public class MainPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	public MainPanel( ApplicationModel model, ApplicationRootController controller ){

		setLayout( new BorderLayout() );
		setBorder( new EmptyBorder( 10, 15, 10, 5 ) );

		JPanel top = new JPanel();
		top.setLayout( new BoxLayout( top, BoxLayout.X_AXIS ) );

		JLabel label = new JLabel( "run bot   " );
		top.add( label );

		JRadioButton button = new JRadioButton();
		button.addActionListener( e -> {

			if( button.isSelected() )
				controller.runBot();
			else
				controller.stopBot();

		} );
		button.setSelected( model.isBotActive() );
		top.add( button );

		add( top, BorderLayout.NORTH );

		JPanel center = new JPanel( new GridBagLayout() );

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets( 5, 25, 5, 25 );
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridx = 0;
		gbc.gridy = 0;
		center.add( new JLabel( "Chanel:" ), gbc );
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		NumericTextField idField = new NumericTextField( 20 );

		center.add( idField, gbc );

		gbc.gridx = 0;
		gbc.gridy = 1;
		center.add( new JLabel( "Pings:" ), gbc );
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		JTextField pingField = new JTextField( 20 );

		center.add( pingField, gbc );
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		center.add( new JLabel( "Text:" ), gbc );
		gbc.gridx = 1;
		gbc.weightx = 1.0;
		JTextField msgField = new JTextField( 20 );

		center.add( msgField, gbc );

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		JButton sendButton = new JButton( "send" );

		sendButton.addActionListener( e -> controller.sendMessage( idField.getText(), pingField.getText(), msgField.getText() ) );

		center.add( sendButton, gbc );

		add( center, BorderLayout.CENTER );

	}

}
