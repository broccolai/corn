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

    /**
     * The {@code ItemStack} to modify during building. This will be cloned and
     * returned upon {@link #build()}.
     */
    protected final @NonNull ItemStack itemStack;
    /**
     * The {@code ItemMeta} to modify during building. This will be applied to
     * the {@link #itemStack} upon {@link #build()}.
     */
    protected final @NonNull M itemMeta;

    /**
     * @param itemStack the {@code ItemStack}
     * @param itemMeta  the {@code ItemMeta}
     */
    protected AbstractItemBuilder(final @NonNull ItemStack itemStack, final @NonNull M itemMeta) {
        this.itemStack = itemStack.clone();
        this.itemMeta = itemMeta;
    }

    /**
     * Gets the {@code Material}.
     *
     * @return the {@code Material}
     */
    public @NonNull Material material() {
        return this.itemStack.getType();
    }

    /**
     * Sets the {@code Material}.
     *
     * @param material the {@code Material}
     * @return the builder
     */
    public @NonNull B material(final @NonNull Material material) {
        this.itemStack.setType(material);
        return (B) this;
    }

    /**
     * Gets the quantity.
     *
     * @return the quantity
     */
    public int amount() {
        return this.itemStack.getAmount();
    }

    /**
     * Sets the quantity.
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
     * @param type the {@code PersistentDataType to use}
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
     * Gets the {@code ItemFlag}s.
     *
     * @return the {@code ItemFlag}s
     */
    public @NonNull Set<ItemFlag> flags() {
        return this.itemMeta.getItemFlags();
    }

    /**
     * Add an {@code ItemFlag}.
     *
     * @param flag the {@code ItemFlag} to add
     * @return the builder
     */
    public @NonNull B addFlag(final @NonNull ItemFlag... flag) {
        this.itemMeta.addItemFlags(flag);
        return (B) this;
    }

    /**
     * Remove an {@code ItemFlag}.
     *
     * @param flag the {@code ItemFlag} to remove
     * @return the builder
     */
    public @NonNull B removeFlag(final @NonNull ItemFlag... flag) {
        this.itemMeta.removeItemFlags(flag);
        return (B) this;
    }

    /**
     * Gets the {@code Enchantment}s.
     *
     * @return the {@code Enchantment}s
     */
    public @NonNull Map<Enchantment, Integer> enchants() {
        return new HashMap<>(this.itemStack.getEnchantments());
    }

    /**
     * Adds an {@code Enchantment}.
     *
     * @param enchantment the {@code Enchantment} to add
     * @param level       the level of the {@code Enchantment}
     * @return the builder
     */
    public @NonNull B addEnchant(final @NonNull Enchantment enchantment, final int level) {
        this.itemMeta.addEnchant(enchantment, level, true);
        return (B) this;
    }

    /**
     * Removes an {@code Enchantment}.
     *
     * @param enchantment the {@code Enchantment} to remove
     * @return the builder
     */
    public @NonNull B removeEnchant(final @NonNull Enchantment... enchantment) {
        for (final Enchantment item : enchantment) {
            this.itemMeta.removeEnchant(item);
        }
        return (B) this;
    }

    /**
     * Sets whether the {@code ItemStack} should be unbreakable.
     *
     * @param unbreakable whether the {@code ItemStack} should be unbreakable
     * @return the builder
     */
    public @NonNull B unbreakable(final boolean unbreakable) {
        itemMeta.setUnbreakable(unbreakable);
        return (B) this;
    }

    /**
     * Get the max stack size.
     *
     * @return the max stack size
     */
    public int maxStackSize() {
        return this.itemStack.getMaxStackSize();
    }

    /**
     * Builds the {@code ItemStack} from the set properties.
     *
     * @return the built {@code ItemStack}
     */
    public @NonNull ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack.clone();
    }

}
