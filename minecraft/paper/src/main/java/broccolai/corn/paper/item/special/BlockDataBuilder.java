package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link BlockDataMeta}.
 */
@SuppressWarnings("unused")
public final class BlockDataBuilder extends AbstractPaperItemBuilder<BlockDataBuilder, BlockDataMeta> {

    private BlockDataBuilder(final @NonNull ItemStack itemStack, final @NonNull BlockDataMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code BlockDataBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code BlockDataBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull BlockDataBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        final ItemMeta meta = itemStack.getItemMeta();

        if (meta instanceof final BlockDataMeta newMeta) {
            return new BlockDataBuilder(itemStack, newMeta);
        } else {
            throw new IllegalArgumentException("The ItemStack's ItemMeta must be of type "
                    + BlockDataMeta.class.getSimpleName()
                    + " but received ItemMeta of type "
                    + meta.getClass().getSimpleName());
        }
    }

    /**
     * Creates a {@code BlockDataBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code BlockDataBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull BlockDataBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        if (!material.isItem()) {
            throw new IllegalArgumentException("The Material must be an obtainable item.");
        }

        return BlockDataBuilder.of(new ItemStack(material));
    }

}
