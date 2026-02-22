package com.cyberclown.basic;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventRouter extends ListenerAdapter{

	@Override
	public void onSlashCommandInteraction( SlashCommandInteractionEvent event ){

		CommandRegistry.handle( event );

	}

}
