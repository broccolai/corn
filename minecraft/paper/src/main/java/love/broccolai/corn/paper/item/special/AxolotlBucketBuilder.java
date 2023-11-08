package love.broccolai.corn.paper.item.special;

import love.broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Axolotl;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.AxolotlBucketMeta;
import org.jspecify.annotations.NullMarked;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link AxolotlBucketMeta}.
 */
@SuppressWarnings("unused")
@NullMarked
public final class AxolotlBucketBuilder extends AbstractPaperItemBuilder<AxolotlBucketBuilder, AxolotlBucketMeta> {

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
    public static AxolotlBucketBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
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
    public static AxolotlBucketBuilder ofType(final Material material) throws IllegalArgumentException {
        return AxolotlBucketBuilder.of(getItem(material));
    }

    /**
     * Creates a {@code AxolotlBucketBuilder} of type {@link Material#AXOLOTL_BUCKET}. A convenience method.
     *
     * @return instance of {@code AxolotlBucketBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static AxolotlBucketBuilder ofAxolotlBucket() throws IllegalArgumentException {
        return ofType(Material.AXOLOTL_BUCKET);
    }

    /**
     * Gets the variant.
     *
     * @return the variant
     */
    public Axolotl.Variant variant() {
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

    /**
     * Gets whether a variant tag exists.
     * If true, a specific axolotl will be spawned.
     *
     * @return whether a variant tag exists
     */
    public boolean hasVariant() {
        return this.itemMeta.hasVariant();
    }

}