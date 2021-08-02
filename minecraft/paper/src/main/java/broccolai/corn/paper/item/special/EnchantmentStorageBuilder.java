package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

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
        return new EnchantmentStorageBuilder(itemStack, AridUtil.castMeta(itemStack.getItemMeta(), EnchantmentStorageMeta.class));
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
        return EnchantmentStorageBuilder.of(AridUtil.getItem(material));
    }

}
