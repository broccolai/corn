package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link LeatherArmorMeta}.
 */
@SuppressWarnings("unused")
public final class LeatherArmorBuilder extends AbstractPaperItemBuilder<LeatherArmorBuilder, LeatherArmorMeta> {

    private LeatherArmorBuilder(final @NonNull ItemStack itemStack, final @NonNull LeatherArmorMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code LeatherArmorBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code LeatherArmorBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull LeatherArmorBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new LeatherArmorBuilder(itemStack, castMeta(itemStack.getItemMeta(), LeatherArmorMeta.class));
    }

    /**
     * Creates a {@code LeatherArmorBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code LeatherArmorBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull LeatherArmorBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return LeatherArmorBuilder.of(getItem(material));
    }

    /**
     * Gets the color.
     *
     * @return the color
     */
    public @NonNull Color color() {
        return this.itemMeta.getColor();
    }

    /**
     * Sets the color. Pass {@code null} to reset.
     *
     * @param color the color
     * @return the builder
     */
    public @NonNull LeatherArmorBuilder color(final @Nullable Color color) {
        this.itemMeta.setColor(color);
        return this;
    }

}
