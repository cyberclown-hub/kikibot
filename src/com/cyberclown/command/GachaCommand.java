package com.cyberclown.command;

import java.awt.Color;

import com.cyberclown.basic.SlashCommand;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class GachaCommand implements SlashCommand{

	@Override
	public String getName(){

		return "gacha";

	}

	@Override
	public String getDescription(){

		return "Шанс получить редкого кикпога";

	}

	@Override
	public void execute( SlashCommandInteractionEvent event ){

		GachaItem result = Gacha.roll();

		EmbedBuilder embed = new EmbedBuilder()
				.setTitle( "🎲 Твой результат: " + result.getName() )
				.setImage( result.getImageUrl() )
				.setColor( Color.CYAN );

		event.replyEmbeds( embed.build() ).queue();

	}

}
