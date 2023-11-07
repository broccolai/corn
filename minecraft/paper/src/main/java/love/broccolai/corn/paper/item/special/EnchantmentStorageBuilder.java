package love.broccolai.corn.paper.item.special;

import love.broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Map;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link EnchantmentStorageMeta}.
 */
@SuppressWarnings("unused")
public final class EnchantmentStorageBuilder extends AbstractPaperItemBuilder<EnchantmentStorageBuilder, EnchantmentStorageMeta> {

    private EnchantmentStorageBuilder(final @NonNull ItemStack itemStack, final @NonNull EnchantmentStorageMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@code EnchantmentStorageBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code EnchantmentStorageBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull EnchantmentStorageBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new EnchantmentStorageBuilder(itemStack, castMeta(itemStack.getItemMeta(), EnchantmentStorageMeta.class));
    }

    /**
     * Creates an {@code EnchantmentStorageBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code EnchantmentStorageBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull EnchantmentStorageBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return EnchantmentStorageBuilder.of(getItem(material));
    }

    /**
     * Gets the stored enchants.
     *
     * @return the stored enchants
     */
    public @NonNull Map<@NonNull Enchantment, @NonNull Integer> storedEnchants() {
        return this.itemMeta.getStoredEnchants();
    }

    /**
     * Sets the stored enchants.
     *
     * @param storedEnchants the stored enchants
     * @return the builder
     */
    public @NonNull EnchantmentStorageBuilder storedEnchants(final @NonNull Map<@NonNull Enchantment, @NonNull Integer> storedEnchants) {
        for (final @NonNull Enchantment item : this.itemMeta.getStoredEnchants().keySet()) {
            this.itemMeta.removeStoredEnchant(item);
        }
        for (final Map.@NonNull Entry<@NonNull Enchantment, @NonNull Integer> entry : storedEnchants.entrySet()) {
            this.addStoredEnchant(entry.getKey(), entry.getValue());
        }
        return this;
    }

    /**
     * Adds a stored enchant.
     *
     * @param enchant the {@code Enchantment} to add
     * @param level   the level of the {@code Enchantment}
     * @return the builder
     */
    public @NonNull EnchantmentStorageBuilder addStoredEnchant(final @NonNull Enchantment enchant, final int level) {
        this.itemMeta.addStoredEnchant(enchant, level, true);
        return this;
    }

    /**
     * Removes a stored enchant.
     *
     * @param enchant the {@code Enchantment} to remove
     * @return the builder
     */
    public @NonNull EnchantmentStorageBuilder removeStoredEnchant(final @NonNull Enchantment... enchant) {
        for (final @NonNull Enchantment item : enchant) {
            this.itemMeta.removeStoredEnchant(item);
        }
        return this;
    }

}
