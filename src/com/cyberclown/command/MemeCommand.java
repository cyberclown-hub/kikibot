package com.cyberclown.command;

import com.cyberclown.basic.SlashCommand;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class MemeCommand implements SlashCommand{

	@Override
	public String getName(){

		return "meme";

	}

	@Override
	public String getDescription(){

		return "Get random local meme";

	}

	@Override
	public void execute( SlashCommandInteractionEvent event ){

		event.reply( MemeRepository.next() ).queue();

	}

}
