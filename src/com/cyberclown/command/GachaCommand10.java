package com.cyberclown.command;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cyberclown.basic.SlashCommand;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class GachaCommand10 implements SlashCommand{

	@Override
	public String getName(){

		return "gacha10";

	}

	@Override
	public String getDescription(){

		return "Шанс получить редкого кикпога 10 попыток";

	}

	@Override
	public void execute( SlashCommandInteractionEvent event ){

		Map<GachaItem, Integer> results = new HashMap<>();

		for( int i = 0; i < 10; i++ ){

			GachaItem item = Gacha.roll();

			if( ! results.containsKey( item ) )
				results.put( item, 1 );
			else
				results.put( item, results.get( item ) + 1 );

		}

		List<GachaItem> legendary = results.keySet().stream()
				.filter( i -> i.getRarity() == Rarity.LEGENDARY ).toList();
		List<GachaItem> rare = results.keySet().stream()
				.filter( i -> i.getRarity() == Rarity.RARE ).toList();
		List<GachaItem> common = results.keySet().stream()
				.filter( i -> i.getRarity() == Rarity.COMMON ).toList();

		List<MessageEmbed> embeds = new ArrayList<>();
		for( GachaItem item : legendary )
			embeds.add( getBuilder( item, results.get( item ) ).build() );
		for( GachaItem item : rare )
			embeds.add( getBuilder( item, results.get( item ) ).build() );
		for( GachaItem item : common )
			embeds.add( getBuilder( item, results.get( item ) ).build() );
			event.replyEmbeds( embeds ).queue();

	}

	private EmbedBuilder getBuilder( GachaItem item, int count ){

		Color color = switch( item.getRarity() ){

		case LEGENDARY -> Color.yellow;
		case RARE -> Color.magenta;
		case COMMON -> Color.cyan;

		default ->
			throw new IllegalArgumentException( "Unexpected value: " + item.getRarity() );

		};
		EmbedBuilder embed = new EmbedBuilder()
				.setTitle( "🎲 Твой результат: " + item.getName() + (count > 1 ? " x" + count : "") )
				.setImage( item.getImageUrl() )
				.setColor( color );
		return embed;

	}

}
