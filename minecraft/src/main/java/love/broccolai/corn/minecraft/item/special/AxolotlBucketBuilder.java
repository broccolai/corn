package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Axolotl;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.AxolotlBucketMeta;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link AxolotlBucketMeta}.
 */
@NullMarked
public final class AxolotlBucketBuilder extends AbstractItemBuilder<AxolotlBucketBuilder, AxolotlBucketMeta> {

    private AxolotlBucketBuilder(final ItemStack itemStack, final AxolotlBucketMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@code AxolotlBucketBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code AxolotlBucketBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static AxolotlBucketBuilder axolotlBucketBuilder(final ItemStack itemStack) throws IllegalArgumentException {
        return new AxolotlBucketBuilder(itemStack, castMeta(itemStack.getItemMeta(), AxolotlBucketMeta.class));
    }

    /**
     * Creates an {@code AxolotlBucketBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code AxolotlBucketBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static AxolotlBucketBuilder axolotlBucketBuilder(final Material material) throws IllegalArgumentException {
        return axolotlBucketBuilder(itemOfMaterial(material));
    }

    /**
     * Creates an {@code AxolotlBucketBuilder} of type {@link Material#AXOLOTL_BUCKET}. A convenience method.
     *
     * @return instance of {@code AxolotlBucketBuilder}
     */
    public static AxolotlBucketBuilder axolotlBucketBuilder() {
        return axolotlBucketBuilder(Material.AXOLOTL_BUCKET);
    }

    /**
     * Gets the variant.
     *
     * @return the variant
     */
    public Axolotl.@Nullable Variant variant() {
        if (!this.itemMeta.hasVariant()) {
            return null;
        }
        return this.itemMeta.getVariant();
    }

    /**
     * Sets the variant.
     *
     * @param variant the variant
     * @return the builder
     */
    public AxolotlBucketBuilder variant(final Axolotl.Variant variant) {
        this.itemMeta.setVariant(variant);
        return this;
    }

}
