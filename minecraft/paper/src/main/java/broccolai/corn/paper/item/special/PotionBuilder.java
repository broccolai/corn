package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.ItemMeta;
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
        final ItemMeta meta = itemStack.getItemMeta();

        if (meta instanceof final PotionMeta newMeta) {
            return new PotionBuilder(itemStack, newMeta);
        } else {
            throw new IllegalArgumentException("The ItemStack's ItemMeta must be of type "
                    + PotionMeta.class.getSimpleName()
                    + " but received ItemMeta of type "
                    + meta.getClass().getSimpleName());
        }
    }

    /**
     * Creates a {@code PotionBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code PotionBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item
     */
    public static @NonNull PotionBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        if (!material.isItem()) {
            throw new IllegalArgumentException("The Material must be an obtainable item.");
        }

        return PotionBuilder.of(new ItemStack(material));
    }

}
