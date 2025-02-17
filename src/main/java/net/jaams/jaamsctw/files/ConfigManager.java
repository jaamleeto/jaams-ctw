package net.jaams.jaamsctw.files;

import net.jaams.jaamsctw.JaamsCtwMod;

import java.io.File;

public class ConfigManager {
	private final JaamsCtwMod plugin;

	public ConfigManager(JaamsCtwMod plugin) {
		this.plugin = plugin;
		plugin.saveDefaultConfig();
		this.load(false);
	}

	public void load() {
		this.load(true);
	}

	public void persists() {
		plugin.saveConfig();
	}

	private void load(boolean reload) {
		if (reload) {
			plugin.reloadConfig();
		}
		validateSignText(getSignFirstLine(), "signs.first-line-text", "ctw");
		validateSignText(getSignFirstLineReplacement(), "signs.first-line-text-replacement", "&1LIBELULA&4CTW");
		validateSignText(getTextForInvalidRooms(), "signs.on-invalid-room-replacement", "&4INVALID ROOM");
		validateSignText(getTextForInvalidMaps(), "signs.on-invalid-map-replacement", "&4INVALID MAP");
		File defaultMapFile = new File(plugin.getDataFolder(), "defaultmap.yml");
		if (!defaultMapFile.exists()) {
			plugin.saveResource("defaultmap.yml", false);
		}
	}

	private void validateSignText(String text, String key, String defaultValue) {
		if (text.length() < 1 || text.length() > 16) {
			plugin.getLogger().warning(String.format("Config value \"%s\" is incorrect.", key));
			plugin.getConfig().set(key, defaultValue);
			plugin.getLogger().info(String.format("Config value \"%s\" has been changed to \"%s\".", key, defaultValue));
			persists();
		}
	}

	public String getSignFirstLine() {
		return plugin.getConfig().getString("signs.first-line-text");
	}

	public String getSignFirstLineReplacement() {
		return plugin.getConfig().getString("signs.first-line-text-replacement");
	}

	public String getTextForInvalidRooms() {
		return plugin.getConfig().getString("signs.on-invalid-room-replacement");
	}

	public String getTextForInvalidMaps() {
		return plugin.getConfig().getString("signs.on-invalid-map-replacement");
	}

	public String getTextForDisabledMaps() {
		return plugin.getConfig().getString("signs.on-disabled-map");
	}

	public boolean implementSpawnCmd() {
		return plugin.getConfig().getBoolean("implement-spawn-cmd");
	}

	public boolean isVoidInstaKill() {
		return plugin.getConfig().getBoolean("instakill-on-void");
	}

	public boolean isFallDamage() {
		return plugin.getConfig().getBoolean("disable-fall-damage");
	}

	public boolean isWaterFlowAllowed() {
		return plugin.getConfig().getBoolean("allow-water-flow");
	}

	public boolean isLavaFlowAllowed() {
		return plugin.getConfig().getBoolean("allow-lava-flow");
	}

	public boolean isTntBlockDamageEnabled() {
		return plugin.getConfig().getBoolean("tnt-breaks-blocks");
	}

	public boolean isTntPlayerDamageEnabled() {
		return plugin.getConfig().getBoolean("tnt-causes-player-damage");
	}
}
