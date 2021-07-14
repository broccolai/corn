package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link PotionMeta}.
 */
@SuppressWarnings("unused")
public final class PotionBuilder extends AbstractPaperItemBuilder<PotionBuilder, PotionMeta> {

    private PotionBuilder(final @NonNull ItemStack itemStack, final @NonNull PotionMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code PotionBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code PotionBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull PotionBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new PotionBuilder(itemStack, AridUtil.castMeta(itemStack.getItemMeta(), PotionMeta.class));
    }

    /**
     * Creates a {@code PotionBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code PotionBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull PotionBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return PotionBuilder.of(AridUtil.getItem(material));
    }

}
