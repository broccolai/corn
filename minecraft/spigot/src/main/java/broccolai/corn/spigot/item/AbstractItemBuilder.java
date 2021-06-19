package broccolai.corn.spigot.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Modifies {@link ItemStack}s using methods that both Paper and Spigot share.
 *
 * @param <B> the builder type
 * @param <M> the {@link ItemMeta} type
 */
@SuppressWarnings({"unchecked", "unused"})
public abstract class AbstractItemBuilder<B extends AbstractItemBuilder<B, M>, M extends ItemMeta> {

    protected final @NonNull ItemStack itemStack;
    protected final @NonNull M itemMeta;

    protected final @NonNull Map<Enchantment, Integer> enchantmentMap;

    protected AbstractItemBuilder(final @NonNull ItemStack itemStack, final @NonNull M itemMeta) {
        this.itemStack = itemStack.clone();
        this.itemMeta = itemMeta;
        this.enchantmentMap = new HashMap<>(this.itemMeta.getEnchants());
    }

    /**
     * Gets the {@link Material} of the {@code ItemStack}.
     *
     * @return the {@code Material}
     */
    public Material material() {
        return this.itemStack.getType();
    }

    /**
     * Sets the {@link Material} of the {@code ItemStack}.
     *
     * @param material the {@code Material}
     * @return the builder
     */
    public @NonNull B material(final Material material) {
        this.itemStack.setType(material);
        return (B) this;
    }

    /**
     * Gets the quantity of the {@code ItemStack}.
     *
     * @return the quantity
     */
    public int amount() {
        return this.itemStack.getAmount();
    }

    /**
     * Sets the quantity of the {@code ItemStack}.
     *
     * @param amount the quantity
     * @return the builder
     */
    public @NonNull B amount(final int amount) {
        this.itemStack.setAmount(amount);
        return (B) this;
    }

    /**
     * Gets data from the {@code ItemStack}'s {@link org.bukkit.persistence.PersistentDataContainer}.
     *
     * @param key  the {@code NamespacedKey} to use
     * @param type the {@code PersistentDatType to use}
     * @param <T>  the primary object type of the data
     * @param <Z>  the retrieve object type of the data
     * @return the data
     */
    public <T, Z> @Nullable Z getData(
            final @NonNull NamespacedKey key,
            final @NonNull PersistentDataType<T, Z> type
    ) {
        return this.itemMeta.getPersistentDataContainer().get(key, type);
    }

    /**
     * Adds data to the {@code ItemStack}'s {@link org.bukkit.persistence.PersistentDataContainer}.
     *
     * @param key    the {@code NamespacedKey} to use
     * @param type   the {@code PersistentDataType} to use
     * @param object the data to set
     * @param <T>    the primary object type of the data
     * @param <Z>    the retrieve object type of the data
     * @return the builder
     */
    public <T, Z> @NonNull B setData(
            final @NonNull NamespacedKey key,
            final @NonNull PersistentDataType<T, Z> type,
            final @NonNull Z object
    ) {
        this.itemMeta.getPersistentDataContainer().set(key, type, object);
        return (B) this;
    }

    /**
     * Removes data from the {@code ItemStack}'s {@link org.bukkit.persistence.PersistentDataContainer}.
     *
     * @param key the {@code NamespacedKey} to use
     * @return the builder
     */
    public @NonNull B removeData(
            final @NonNull NamespacedKey key
    ) {
        this.itemMeta.getPersistentDataContainer().remove(key);
        return (B) this;
    }

    /**
     * Gets the {@code ItemFlag}s of the {@code ItemStack}.
     *
     * @return the {@code ItemFlag}s
     */
    public @NonNull Set<ItemFlag> getFlags() {
        return this.itemMeta.getItemFlags();
    }

    /**
     * Adds {@code ItemFlag}s to the {@code ItemStack}.
     *
     * @param flags the {@code ItemFlag}s to add
     * @return the builder
     */
    public @NonNull B addFlags(final @NonNull ItemFlag... flags) {
        this.itemMeta.addItemFlags(flags);
        return (B) this;
    }

    /**
     * Removes {@code ItemFlag}s from the {@code ItemStack}.
     *
     * @param flags the {@code ItemFlag}s to remove
     * @return the builder
     */
    public @NonNull B removeFlags(final @NonNull ItemFlag... flags) {
        this.itemMeta.removeItemFlags(flags);
        return (B) this;
    }

    /**
     * Gets the {@code Enchantment}s of the {@code ItemStack}.
     *
     * @return the {@code Enchantment}s
     */
    public @NonNull Map<Enchantment, Integer> getEnchants() {
        return new HashMap<>(this.enchantmentMap);
    }

    /**
     * Adds an {@code Enchantment} to the {@code ItemStack}.
     *
     * @param enchantment the {@code Enchantment} to add
     * @param level       the level of the {@code Enchantment}
     * @return the builder
     */
    public @NonNull B addEnchant(final @NonNull Enchantment enchantment, final int level) {
        this.enchantmentMap.put(enchantment, level);
        return (B) this;
    }

    /**
     * Removes an {@code Enchantment} from the {@code ItemStack}.
     *
     * @param enchantment the {@code Enchantment} to remove
     * @return the builder
     */
    public @NonNull B removeEnchant(final @NonNull Enchantment enchantment) {
        this.enchantmentMap.remove(enchantment);
        return (B) this;
    }

    /**
     * Builds the {@code ItemStack} from the set properties.
     *
     * @return the built {@code ItemStack}
     */
    public @NonNull ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        this.itemStack.addUnsafeEnchantments(this.enchantmentMap);

        return this.itemStack.clone();
    }

}
