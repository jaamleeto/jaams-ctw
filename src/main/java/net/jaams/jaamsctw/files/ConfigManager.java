package net.jaams.jaamsctw.files;

import org.bukkit.configuration.file.FileConfiguration;

import net.jaams.jaamsctw.JaamsCtwMod;

import java.io.File;

public class ConfigManager {
	private final JaamsCtwMod plugin;
	private FileConfiguration config;

	public ConfigManager(JaamsCtwMod plugin) {
		this.plugin = plugin;
		this.config = plugin.getConfig();
		plugin.saveDefaultConfig();
		this.load(false);
	}

	public void load() {
		this.load(true);
	}

	public void persists() {
		this.plugin.saveConfig();
	}

	private void load(boolean reload) {
		if (reload) {
			this.plugin.reloadConfig();
		}
		this.validateSignText(this.getSignFirstLine(), "signs.first-line-text", "ctw");
		this.validateSignText(this.getSignFirstLineReplacement(), "signs.first-line-text-replacement", "&1LIBELULA&4CTW");
		this.validateSignText(this.getTextForInvalidRooms(), "signs.on-invalid-room-replacement", "&4INVALID ROOM");
		this.validateSignText(this.getTextForInvalidMaps(), "signs.on-invalid-map-replacement", "&4INVALID MAP");
		File defaultMapFile = new File(this.plugin.getDataFolder(), "defaultmap.yml");
		if (!defaultMapFile.exists()) {
			this.plugin.saveResource("defaultmap.yml", false);
		}
	}

	private void validateSignText(String text, String key, String defaultValue) {
		if (text.length() < 1 || text.length() > 16) {
			this.plugin.getLogger().warning("Config value \"".concat(key).concat("\" is incorrect."));
			this.config.set(key, (Object) defaultValue);
			this.plugin.getLogger().info("Config value \"".concat(key).concat("\" has been changed to \"").concat(defaultValue).concat("\"."));
		}
	}

	public String getSignFirstLine() {
		return this.config.getString("signs.first-line-text");
	}

	public String getSignFirstLineReplacement() {
		return this.config.getString("signs.first-line-text-replacement");
	}

	public String getTextForInvalidRooms() {
		return this.config.getString("signs.on-invalid-room-replacement");
	}

	public String getTextForInvalidMaps() {
		return this.config.getString("signs.on-invalid-map-replacement");
	}

	public String getTextForDisabledMaps() {
		return this.config.getString("signs.on-disabled-map");
	}

	public boolean implementSpawnCmd() {
		return this.config.getBoolean("implement-spawn-cmd", false);
	}

	public boolean isVoidInstaKill() {
		return this.plugin.getConfig().getBoolean("instakill-on-void", false);
	}

	public boolean isFallDamage() {
		return this.plugin.getConfig().getBoolean("disable-fall-damage", false);
	}
}
