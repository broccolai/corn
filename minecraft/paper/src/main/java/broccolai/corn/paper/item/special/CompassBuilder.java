package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link CompassMeta}.
 */
@SuppressWarnings("unused")
public final class CompassBuilder extends AbstractPaperItemBuilder<CompassBuilder, CompassMeta> {

    private CompassBuilder(final @NonNull ItemStack itemStack, final @NonNull CompassMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code CompassBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code CompassBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull CompassBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new CompassBuilder(itemStack, AridUtil.castMeta(itemStack.getItemMeta(), CompassMeta.class));
    }

    /**
     * Creates a {@code CompassBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code CompassBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull CompassBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return CompassBuilder.of(AridUtil.getItem(material));
    }

}
