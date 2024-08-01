package love.broccolai.corn.minecraft.item.special;

import java.util.Map;
import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.jspecify.annotations.NullMarked;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link EnchantmentStorageMeta}.
 */
@NullMarked
public final class EnchantmentStorageBuilder extends AbstractItemBuilder<EnchantmentStorageBuilder, EnchantmentStorageMeta> {

    private EnchantmentStorageBuilder(final ItemStack itemStack, final EnchantmentStorageMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@code EnchantmentStorageBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code EnchantmentStorageBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static EnchantmentStorageBuilder enchantmentStorageBuilder(final ItemStack itemStack) throws IllegalArgumentException {
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
    public static EnchantmentStorageBuilder enchantmentStorageBuilder(final Material material) throws IllegalArgumentException {
        return enchantmentStorageBuilder(itemOfMaterial(material));
    }

    /**
     * Creates an {@code EnchantmentStorageBuilder} of type {@link Material#ENCHANTED_BOOK}. A convenience method.
     *
     * @return instance of {@code EnchantmentStorageBuilder}
     */
    public static EnchantmentStorageBuilder enchantmentStorageBuilder() {
        return enchantmentStorageBuilder(Material.ENCHANTED_BOOK);
    }

    /**
     * Gets the stored enchantments.
     *
     * @return the stored enchantments
     */
    public Map<Enchantment, Integer> storedEnchants() {
        return this.itemMeta.getStoredEnchants();
    }

    /**
     * Sets the stored enchantments.
     *
     * @param storedEnchants the stored enchantments
     * @return the builder
     */
    public EnchantmentStorageBuilder storedEnchants(final Map<Enchantment, Integer> storedEnchants) {
        for (final Enchantment item : this.itemMeta.getStoredEnchants().keySet()) {
            this.itemMeta.removeStoredEnchant(item);
        }
        for (final Map.Entry<Enchantment, Integer> entry : storedEnchants.entrySet()) {
            this.addStoredEnchant(entry.getKey(), entry.getValue());
        }
        return this;
    }

    /**
     * Adds a stored enchantment.
     *
     * @param enchant the enchantment to add
     * @param level   the level of the enchantment
     * @return the builder
     */
    public EnchantmentStorageBuilder addStoredEnchant(final Enchantment enchant, final int level) {
        this.itemMeta.addStoredEnchant(enchant, level, true);
        return this;
    }

    /**
     * Removes a stored enchantment.
     *
     * @param enchant the enchantment to remove
     * @return the builder
     */
    public EnchantmentStorageBuilder removeStoredEnchant(final Enchantment... enchant) {
        for (final Enchantment item : enchant) {
            this.itemMeta.removeStoredEnchant(item);
        }
        return this;
    }

    /**
     * Gets whether the given enchantment conflicts with any stored enchantments
     * in this item.
     *
     * @param enchantment the enchantment to test
     * @return whether the enchantment conflicts
     */
    public boolean hasConflictingStoredEnchant(final Enchantment enchantment) {
        return this.itemMeta.hasConflictingStoredEnchant(enchantment);
    }

}
