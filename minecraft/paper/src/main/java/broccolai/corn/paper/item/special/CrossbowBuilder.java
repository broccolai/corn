package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.ItemMeta;
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
        final ItemMeta meta = itemStack.getItemMeta();

        if (meta instanceof final CrossbowMeta newMeta) {
            return new CrossbowBuilder(itemStack, newMeta);
        } else {
            throw new IllegalArgumentException("The ItemStack's ItemMeta must be of type "
                    + CrossbowMeta.class.getSimpleName()
                    + " but received ItemMeta of type "
                    + meta.getClass().getSimpleName());
        }
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
        if (!material.isItem()) {
            throw new IllegalArgumentException("The Material must be an obtainable item.");
        }

        return CrossbowBuilder.of(new ItemStack(material));
    }

}
