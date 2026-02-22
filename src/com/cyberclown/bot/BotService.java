package com.cyberclown.bot;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class BotService{

	private final JDA jda;

	public BotService( JDA jda ){

		this.jda = jda;

	}

	public void sendMessage( long channelId, String message ){

		TextChannel channel = jda.getTextChannelById( channelId );

		if( channel != null ){

			channel.sendMessage( message ).queue();

		}

	}

	public void pingUsers( long channelId, String users, String message ){

		TextChannel channel = jda.getTextChannelById( channelId );

		if( channel != null ){

			List<Long> usersList = Arrays.stream( users.split( "," ) )
					.map( s -> Long.parseLong( s.trim() ) ).toList();
			StringBuilder builder = new StringBuilder();

			for( long user : usersList ){

				if( builder.length() > 0 )
					builder.append( ", " );
				builder.append( "<@" + user + ">" );

			}

			channel.sendMessage( builder.toString() + " " + message )
					.setAllowedMentions( Collections.singleton( Message.MentionType.USER ) )
					.queue();

		}

	}

}
