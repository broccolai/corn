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
     * Set the quantity of the {@code ItemStack}
     *
     * @param amount the {@code ItemStack}'s quantity
     * @return the builder
     */
    public @NonNull T amount(final int amount) {
        this.itemStack.setAmount(amount);
        return (T) this;
    }

    /**
     * Add data to the {@code ItemStack}'s {@link org.bukkit.persistence.PersistentDataContainer}.
     *
     * @param key    the {@code NamespacedKey} to use
     * @param type   the {@code PersistentDataType} to use
     * @param object the data to set
     * @param <T0>   the primary object type of the data
     * @param <Z>    the retrieve object type of the data
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
     * Get data from the {@code ItemStack}'s {@link org.bukkit.persistence.PersistentDataContainer}.
     *
     * @param key  the {@code NamespacedKey} to use
     * @param type the {@code PersistentDatType to use}
     * @param <T0> the primary object type of the data
     * @param <Z>  the retrieve object type of the data
     * @return the data
     */
    public <T0, Z> @NonNull Z getData(
            final @NonNull NamespacedKey key,
            final @NonNull PersistentDataType<T0, Z> type
    ) {
        return this.itemMeta.getPersistentDataContainer().get(key, type);
    }

    /**
     * Remove data from the {@code ItemStack}'s {@link org.bukkit.persistence.PersistentDataContainer}.
     *
     * @param key the {@code NamespacedKey} to use
     * @return the builder
     */
    public @NonNull T removeData(
            final @NonNull NamespacedKey key
    ) {
        this.itemMeta.getPersistentDataContainer().remove(key);
        return (T) this;
    }

    /**
     * Add flags to the {@code ItemStack}.
     *
     * @param flags the {@code ItemFlag} to add
     * @return the builder
     */
    public @NonNull T flags(final @NonNull ItemFlag... flags) {
        this.itemMeta.addItemFlags(flags);
        return (T) this;
    }

    /**
     * Remove flags from the {@code ItemStack}.
     *
     * @param flags the {@code ItemFlag} to add
     * @return the builder
     */
    public @NonNull T removeFlags(final @NonNull ItemFlag... flags) {
        this.itemMeta.removeItemFlags(flags);
        return (T) this;
    }

    /**
     * Add an {@code Enchantment} to the {@code ItemStack}.
     *
     * @param enchantment the {@code Enchantment} to add
     * @param level       the level of the {@code Enchantment}
     * @return the builder
     */
    public @NonNull T enchant(final @NonNull Enchantment enchantment, final int level) {
        this.enchantmentMap.put(enchantment, level);
        return (T) this;
    }

    /**
     * Remove an {@code Enchantment} from the {@code ItemStack}.
     *
     * @param enchantment the {@code Enchantment} to remove
     * @return the builder
     */
    public @NonNull T removeEnchant(final @NonNull Enchantment enchantment) {
        this.enchantmentMap.remove(enchantment);
        return (T) this;
    }

    /**
     * Build the {@code ItemStack} from the set properties.
     *
     * @return the built {@code ItemStack}
     */
    public @NonNull ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        this.itemStack.addUnsafeEnchantments(this.enchantmentMap);

        return this.itemStack.clone();
    }

}
