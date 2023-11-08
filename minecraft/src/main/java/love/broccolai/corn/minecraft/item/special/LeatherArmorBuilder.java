package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link LeatherArmorMeta}.
 */
@NullMarked
public final class LeatherArmorBuilder extends AbstractItemBuilder<LeatherArmorBuilder, LeatherArmorMeta> {

    private LeatherArmorBuilder(final ItemStack itemStack, final LeatherArmorMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code LeatherArmorBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code LeatherArmorBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static LeatherArmorBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
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
    public static LeatherArmorBuilder ofType(final Material material) throws IllegalArgumentException {
        return LeatherArmorBuilder.of(getItem(material));
    }

    /**
     * Gets the color.
     *
     * @return the color
     */
    public Color color() {
        return this.itemMeta.getColor();
    }

    /**
     * Sets the color. Pass {@code null} to reset.
     *
     * @param color the color
     * @return the builder
     */
    public LeatherArmorBuilder color(final @Nullable Color color) {
        this.itemMeta.setColor(color);
        return this;
    }

}
