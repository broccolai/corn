package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import com.destroystokyo.paper.inventory.meta.ArmorStandMeta;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link ArmorStandMeta}.
 */
@SuppressWarnings("unused")
public final class ArmorStandBuilder extends AbstractPaperItemBuilder<ArmorStandBuilder, ArmorStandMeta> {

    private ArmorStandBuilder(final @NonNull ItemStack itemStack, final @NonNull ArmorStandMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code ArmorStandBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code ArmorStandBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull ArmorStandBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        final ItemMeta meta = itemStack.getItemMeta();

        if (meta instanceof final ArmorStandMeta newMeta) {
            return new ArmorStandBuilder(itemStack, newMeta);
        } else {
            throw new IllegalArgumentException("The ItemStack's ItemMeta must be of type "
                    + ArmorStandMeta.class.getSimpleName()
                    + " but received ItemMeta of type "
                    + meta.getClass().getSimpleName());
        }
    }

    /**
     * Creates a {@code ArmorStandBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code ArmorStandBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull ArmorStandBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        if (!material.isItem()) {
            throw new IllegalArgumentException("The Material must be an obtainable item.");
        }

        return ArmorStandBuilder.of(new ItemStack(material));
    }

}
