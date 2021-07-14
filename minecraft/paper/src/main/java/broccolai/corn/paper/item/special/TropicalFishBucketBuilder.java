package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.TropicalFishBucketMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link TropicalFishBucketMeta}.
 */
@SuppressWarnings("unused")
public final class TropicalFishBucketBuilder extends AbstractPaperItemBuilder<TropicalFishBucketBuilder, TropicalFishBucketMeta> {

    private TropicalFishBucketBuilder(final @NonNull ItemStack itemStack, final @NonNull TropicalFishBucketMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code TropicalFishBucketBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code TropicalFishBucketBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull TropicalFishBucketBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new TropicalFishBucketBuilder(itemStack, AridUtil.castMeta(itemStack.getItemMeta(), TropicalFishBucketMeta.class));
    }

    /**
     * Creates a {@code TropicalFishBucketBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code TropicalFishBucketBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull TropicalFishBucketBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return TropicalFishBucketBuilder.of(AridUtil.getItem(material));
    }

}
