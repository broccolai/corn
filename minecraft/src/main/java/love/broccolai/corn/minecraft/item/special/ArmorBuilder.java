package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link ArmorMeta}.
 */
@NullMarked
public final class ArmorBuilder extends AbstractItemBuilder<ArmorBuilder, ArmorMeta> {

    private ArmorBuilder(final ItemStack itemStack, final ArmorMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@code ArmorBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code ArmorBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static ArmorBuilder armorBuilder(final ItemStack itemStack) throws IllegalArgumentException {
        return new ArmorBuilder(itemStack, castMeta(itemStack.getItemMeta(), ArmorMeta.class));
    }

    /**
     * Creates an {@code ArmorBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code ArmorBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static ArmorBuilder armorBuilder(final Material material) throws IllegalArgumentException {
        return armorBuilder(itemOfMaterial(material));
    }

    /**
     * Gets the trim.
     *
     * @return the trim
     */
    public @Nullable ArmorTrim trim() {
        return this.itemMeta.getTrim();
    }

    /**
     * Sets the trim. Pass {@code null} to reset.
     *
     * @param trim the trim
     * @return the builder
     */
    public ArmorBuilder trim(final @Nullable ArmorTrim trim) {
        this.itemMeta.setTrim(trim);
        return this;
    }

}
