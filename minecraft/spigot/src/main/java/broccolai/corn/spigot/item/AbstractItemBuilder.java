package broccolai.corn.spigot.item;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unchecked", "unused"})
public abstract class AbstractItemBuilder<T extends AbstractItemBuilder<T, M>, M extends ItemMeta> {

    protected final @NonNull ItemStack itemStack;
    protected final @NonNull M itemMeta;

    protected final @NonNull Map<Enchantment, Integer> enchantmentMap;

    protected AbstractItemBuilder(final @NonNull ItemStack itemStack, final @NonNull M itemMeta) {
        this.itemStack = itemStack.clone();
        this.itemMeta = itemMeta;
        this.enchantmentMap = new HashMap<>(this.itemMeta.getEnchants());
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
     * Add data to the items PersistentDataContainer
     *
     * @param key    the NamespacedKey to use
     * @param type   the data type to use
     * @param object data to set
     * @param <T0>   the primary object type of data
     * @param <Z>    the retrieve object type of data
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
        this.enchantmentMap.put(enchantment, level);
        return (T) this;
    }

    /**
     * Build ItemStack from set properties.
     *
     * @return the built ItemStack
     */
    public @NonNull ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        this.itemStack.addUnsafeEnchantments(this.enchantmentMap);

        return this.itemStack.clone();
    }

}
