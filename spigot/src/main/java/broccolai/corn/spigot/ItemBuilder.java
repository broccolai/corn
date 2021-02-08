package broccolai.corn.spigot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@SuppressWarnings({"unchecked", "unused"})
public class ItemBuilder<T extends ItemBuilder<T>> {

    protected final @NonNull ItemStack itemStack;
    protected final @NonNull ItemMeta itemMeta;

    protected ItemBuilder(final @NonNull ItemStack itemStack, final @Nullable ItemMeta itemMeta) {
        this.itemStack = itemStack.clone();
        this.itemMeta = itemMeta != null
                ? itemMeta
                : Objects.requireNonNull(
                        Bukkit.getItemFactory().getItemMeta(itemStack.getType())
                );
    }

    /**
     * Create a ItemBuilder.
     *
     * @param itemStack the ItemStack to base builder off of
     * @return instance of ItemBuilder
     */
    public static ItemBuilder<?> spigot(final @NonNull ItemStack itemStack) {
        return new ItemBuilder<>(itemStack, itemStack.getItemMeta());
    }

    /**
     * Create an ItemBuilder.
     *
     * @param material the material to base builder off of
     * @return instance of ItemBuilder
     */
    public static ItemBuilder<?> spigot(final @NonNull Material material) {
        return ItemBuilder.spigot(new ItemStack(material));
    }

    /**
     * Set the name of the ItemStack.
     *
     * @param name the ItemStack's display name
     * @return the builder
     */
    public @NonNull T name(final @Nullable String name) {
        this.itemMeta.setDisplayName(name);
        return (T) this;
    }

    /**
     * Set the quantity of the ItemStack.
     *
     * @param amount the ItemStacks quantity
     * @return the builder
     */
    public @NonNull T amount(final int amount) {
        this.itemStack.setAmount(amount);
        return (T) this;
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param lines the lines to set the ItemStacks lore to
     * @return the builder
     */
    public @NonNull T lore(final @Nullable List<String> lines) {
        this.itemMeta.setLore(lines);
        return (T) this;
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param consumer the lines to set the ItemStacks lore to
     * @return the builder
     */
    public @NonNull T lore(final @NonNull Consumer<List<String>> consumer) {
        List<String> lore = this.itemMeta.hasLore() ? this.itemMeta.getLore() : new ArrayList<>();
        consumer.accept(lore);

        this.itemMeta.setLore(lore);
        return (T) this;
    }

    /**
     * Add data to the items PersistentDataContainer
     *
     * @param key the NamespacedKey to use
     * @param type the data type to use
     * @param object data to set
     * @param <T0> the primary object type of data
     * @param <Z> the retrieve object type of data
     * @return the builder
     */
    public <T0, Z> @NonNull T data(
            final @NonNull NamespacedKey key,
            final @NonNull PersistentDataType<T0, Z> type,
            final @NonNull Z object
    ) {
        this.itemMeta.getPersistentDataContainer().set(key, type, object);
        return (T) this;
    }

    /**
     * Remove a persistent data container key from the item
     *
     * @param key the NamespacedKey to use
     * @return the builder
     */
    public @NonNull T removeData(
            final @NonNull NamespacedKey key
    ) {
        this.itemMeta.getPersistentDataContainer().remove(key);
        return (T) this;
    }

    /**
     * Add flags to an ItemStack.
     *
     * @param flags the ItemFlag to add the the ItemStack
     * @return the builder
     */
    public @NonNull T flags(final @NonNull ItemFlag... flags) {
        this.itemMeta.addItemFlags(flags);
        return (T) this;
    }

    /**
     * Delete flags from an ItemStack.
     *
     * @param flags the ItemFlag to add the the ItemStack
     * @return the builder
     */
    public @NonNull T deleteFlags(final @NonNull ItemFlag... flags) {
        this.itemMeta.removeItemFlags(flags);
        return (T) this;
    }

    /**
     * Adds an Enchantment to an ItemStack.
     *
     * @param enchantment the Enchantment to add to the ItemStack
     * @param level       the Level of the Enchantment
     * @return the builder
     */
    public @NonNull T enchant(final @NonNull Enchantment enchantment, final int level) {
        this.itemMeta.addEnchant(enchantment, level, true);
        return (T) this;
    }

    /**
     * Build ItemStack from set properties.
     *
     * @return the built ItemStack
     */
    public @NonNull ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);

        return this.itemStack.clone();
    }

}
