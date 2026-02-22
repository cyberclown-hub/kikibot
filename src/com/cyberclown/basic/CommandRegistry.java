package com.cyberclown.basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cyberclown.command.MemeCommand;
import com.cyberclown.command.PingCommand;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class CommandRegistry{

	private static final Map<String, SlashCommand> commands = new HashMap<>();

	static{

		register( new PingCommand() );
		register( new MemeCommand() );

	}

	private static void register( SlashCommand command ){

		commands.put( command.getName(), command );

	}

	public static void handle( SlashCommandInteractionEvent event ){

		SlashCommand command = commands.get( event.getName() );

		if( command != null ){

			command.execute( event );

		}

	}

	public static void aplly( JDA jda, String guildId ){

		Guild guild = jda.getGuildById( guildId );

		guild.updateCommands().queue();
		jda.updateCommands().queue();

		List<CommandData> data = new ArrayList<>();
		for( SlashCommand command : commands.values() )
			data.add( Commands.slash( command.getName(), command.getDescription() ) );

		guild.updateCommands()
				.addCommands( data )
				.queue();

	}

}
