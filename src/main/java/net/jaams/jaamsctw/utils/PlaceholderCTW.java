package net.jaams.jaamsctw.utils;

import org.bukkit.entity.Player;

import net.jaams.jaamsctw.JaamsCtwMod;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PlaceholderCTW extends PlaceholderExpansion {
	private final JaamsCtwMod plugin;

	public PlaceholderCTW(JaamsCtwMod plugin) {
		this.plugin = plugin;
	}

	public boolean persist() {
		return true;
	}

	public boolean canRegister() {
		return true;
	}

	public String getAuthor() {
		return "Jaam's CTW";
	}

	public String getIdentifier() {
		return "mctw";
	}

	public String getVersion() {
		return this.plugin.getDescription().getVersion();
	}

	public String onPlaceholderRequest(final Player player, final String identifier) {
		if (player == null) {
			return "";
		}
		if (identifier.equals("score")) {
			return new StringBuilder(String.valueOf(this.plugin.db.getScore(player.getName()))).toString();
		}
		if (identifier.equals("kills")) {
			return new StringBuilder(String.valueOf(this.plugin.db.getKill(player.getName()))).toString();
		}
		if (identifier.equals("wools_placed")) {
			return new StringBuilder(String.valueOf(this.plugin.db.getWoolCaptured(player.getName()))).toString();
		}
		return null;
	}
}
