package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link BundleMeta}.
 */
@SuppressWarnings("unused")
public final class BundleBuilder extends AbstractPaperItemBuilder<BundleBuilder, BundleMeta> {

    private BundleBuilder(final @NonNull ItemStack itemStack, final @NonNull BundleMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@code BundleBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code BundleBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull BundleBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new BundleBuilder(itemStack, AridUtil.castMeta(itemStack.getItemMeta(), BundleMeta.class));
    }

    /**
     * Creates an {@code BundleBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code BundleBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull BundleBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return BundleBuilder.of(AridUtil.getItem(material));
    }

}
