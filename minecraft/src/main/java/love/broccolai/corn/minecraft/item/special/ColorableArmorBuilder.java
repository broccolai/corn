package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ColorableArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link ColorableArmorMeta}.
 *
 * <p>Composite of {@link ArmorBuilder} and {@link LeatherArmorBuilder}.</p>
 */
@NullMarked
public final class ColorableArmorBuilder extends AbstractItemBuilder<ColorableArmorBuilder, ColorableArmorMeta> {

    private ColorableArmorBuilder(final ItemStack itemStack, final ColorableArmorMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@code ColorableArmorBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code ColorableArmorBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static ColorableArmorBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
        return new ColorableArmorBuilder(itemStack, castMeta(itemStack.getItemMeta(), ColorableArmorMeta.class));
    }

    /**
     * Creates an {@code ColorableArmorBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code ColorableArmorBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static ColorableArmorBuilder ofType(final Material material) throws IllegalArgumentException {
        return ColorableArmorBuilder.of(itemOfMaterial(material));
    }

    /**
     * Gets the trim.
     *
     * @return the trim
     */
    public ArmorTrim trim() {
        return this.itemMeta.getTrim();
    }

    /**
     * Sets the trim. Pass {@code null} to reset.
     *
     * @param trim the trim
     * @return the builder
     */
    public ColorableArmorBuilder trim(final @Nullable ArmorTrim trim) {
        this.itemMeta.setTrim(trim);
        return this;
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
    public ColorableArmorBuilder color(final @Nullable Color color) {
        this.itemMeta.setColor(color);
        return this;
    }

}
