package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link CrossbowMeta}.
 */
@SuppressWarnings("unused")
public final class CrossbowBuilder extends AbstractPaperItemBuilder<CrossbowBuilder, CrossbowMeta> {

    private CrossbowBuilder(final @NonNull ItemStack itemStack, final @NonNull CrossbowMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code CrossbowBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code CrossbowBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull CrossbowBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new CrossbowBuilder(itemStack, AridUtil.castMeta(itemStack.getItemMeta(), CrossbowMeta.class));
    }

    /**
     * Creates a {@code CrossbowBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code CrossbowBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull CrossbowBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return CrossbowBuilder.of(AridUtil.getItem(material));
    }

}
