package me.xemu.xemchatprotection.builder;

import me.xemu.xemchatprotection.utils.MessageUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

	private Material material;
	private ItemStack stack;
	private ItemMeta meta;

	public ItemBuilder(Material material) {
		this.material = material;
		this.stack = new ItemStack(material);
		this.meta = stack.getItemMeta();
	}

	public ItemBuilder(Material material, int amount) {
		this.material = material;
		this.stack = new ItemStack(material);
		this.meta = stack.getItemMeta();
	}

	public ItemBuilder setDisplayName(String name) {
		meta.setDisplayName(MessageUtils.color(name));
		return this;
	}

	public ItemBuilder setLore(String... lore) {
		List<String> coloredLore = new ArrayList<>();
		for (String part : lore) {
			coloredLore.add(MessageUtils.color(part));
		}
		meta.setLore(coloredLore);
		return this;
	}

	public ItemBuilder setSingleLore(String lore) {
		meta.setLore(Arrays.asList(MessageUtils.color(lore)));
		return this;
	}

	public ItemBuilder addEnchantment(Enchantment enchantment, int level, boolean force) {
		meta.addEnchant(enchantment, level, force);
		return this;
	}

	public ItemStack build() {
		stack.setItemMeta(meta);
		return stack;
	}

}