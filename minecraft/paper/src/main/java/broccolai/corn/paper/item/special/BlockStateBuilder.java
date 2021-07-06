package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link CompassMeta}.
 */
@SuppressWarnings("unused")
public final class BlockStateBuilder extends AbstractPaperItemBuilder<BlockStateBuilder, CompassMeta> {

    private BlockStateBuilder(final @NonNull ItemStack itemStack, final @NonNull CompassMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code CompassBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code CompassBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull BlockStateBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        final ItemMeta meta = itemStack.getItemMeta();

        if (meta instanceof final CompassMeta newMeta) {
            return new BlockStateBuilder(itemStack, newMeta);
        } else {
            throw new IllegalArgumentException("The ItemStack's ItemMeta must be of type "
                    + CompassMeta.class.getSimpleName()
                    + " but received ItemMeta of type "
                    + meta.getClass().getSimpleName());
        }
    }

    /**
     * Creates a {@code CompassBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code CompassBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull BlockStateBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        if (!material.isItem()) {
            throw new IllegalArgumentException("The Material must be an obtainable item.");
        }

        return BlockStateBuilder.of(new ItemStack(material));
    }

}
