package broccolai.corn.spigot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class ItemBuilder {
    private final @NonNull ItemStack item;
    private final @Nullable ItemMeta meta;

    /**
     * Construct ItemBuilder with an ItemStack.
     *
     * @param item ItemStack to base the the new item from
     */
    public ItemBuilder(final @NonNull ItemStack item) {
        this.item = item.clone();
        this.meta = this.item.getItemMeta();
    }

    /**
     * Construct ItemBuilder with a Material.
     *
     * @param material creates an ItemStack and the builder from a material
     */
    public ItemBuilder(final @NonNull Material material) {
        this(new ItemStack(material));
    }

    /**
     * Build ItemStack from set properties.
     *
     * @return the built ItemStack
     */
    public @NonNull ItemStack build() {
        if (this.meta != null) {
            this.item.setItemMeta(this.meta);
        }

        return this.item;
    }

    /**
     * Set the quantity of the ItemStack.
     *
     * @param amount the ItemStacks quantity
     * @return the builder
     */
    public @NonNull ItemBuilder amount(final @NonNull Integer amount) {
        this.item.setAmount(amount);
        return this;
    }

    /**
     * Set the name of the ItemStack.
     *
     * @param name the ItemStack's display name
     * @return the builder
     */
    public @NonNull ItemBuilder name(final @Nullable String name) {
        if (this.meta != null) {
            this.meta.setDisplayName(name);
        }

        return this;
    }

    /**
     * Set the Lore of the ItemStack.
     *
     * @param lines the lines to set the ItemStacks lore to
     * @return the builder
     */
    public @NonNull ItemBuilder lore(final @NonNull List<String> lines) {
        if (this.meta != null) {
            this.meta.setLore(lines);
        }

        return this;
    }

    /**
     * Add a line of lore to the ItemStack.
     *
     * @param lines the lines to add to the ItemStacks lore
     * @return the builder
     */
    public @NonNull ItemBuilder loreAdd(final @NonNull String... lines) {
        if (this.meta != null) {
            List<String> lore = this.meta.getLore();

            if (lore == null) {
                lore = new ArrayList<>();
            }

            lore.addAll(Arrays.asList(lines));
            this.meta.setLore(lore);
        }

        return this;
    }

    /**
     * Remove all lines of an ItemStacks lore.
     *
     * @return the builder
     */
    public @NonNull ItemBuilder loreClear() {
        if (this.meta != null) {
            this.meta.setLore(new ArrayList<>());
        }

        return this;
    }

    /**
     * Remove last line of lore from an ItemStack.
     *
     * @return the builder
     */
    public @NonNull ItemBuilder loreRemoveLast() {
        if (this.meta != null) {
            final List<String> lore = this.meta.getLore();

            if (lore != null && !lore.isEmpty()) {
                lore.remove(lore.size() - 1);
            }

            this.meta.setLore(lore);
        }

        return this;
    }

    /**
     * Add a flag to an ItemStack.
     *
     * @param flag the ItemFlag to add the the ItemStack
     * @return the builder
     */
    public @NonNull ItemBuilder flag(final @NonNull ItemFlag flag) {
        if (this.meta != null) {
            this.meta.addItemFlags(flag);
        }

        return this;
    }

    /**
     * Adds an Enchantment to an ItemStack.
     *
     * @param enchantment the Enchantment to add to the ItemStack
     * @param level       the Level of the Enchantment
     * @return the builder
     */
    public @NonNull ItemBuilder enchant(final @NonNull Enchantment enchantment, final int level) {
        if (this.meta != null) {
            this.meta.addEnchant(enchantment, level, true);
        }

        return this;
    }
}
