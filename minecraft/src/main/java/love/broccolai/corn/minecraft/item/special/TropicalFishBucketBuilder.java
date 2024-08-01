package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.TropicalFish;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.TropicalFishBucketMeta;
import org.jspecify.annotations.NullMarked;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link TropicalFishBucketMeta}.
 */
@NullMarked
public final class TropicalFishBucketBuilder extends AbstractItemBuilder<TropicalFishBucketBuilder, TropicalFishBucketMeta> {

    private TropicalFishBucketBuilder(final ItemStack itemStack, final TropicalFishBucketMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code TropicalFishBucketBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code TropicalFishBucketBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static TropicalFishBucketBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
        return new TropicalFishBucketBuilder(itemStack, castMeta(itemStack.getItemMeta(), TropicalFishBucketMeta.class));
    }

    /**
     * Creates a {@code TropicalFishBucketBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code TropicalFishBucketBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static TropicalFishBucketBuilder ofType(final Material material) throws IllegalArgumentException {
        return TropicalFishBucketBuilder.of(itemOfMaterial(material));
    }

    /**
     * Creates a {@code TropicalFishBucketBuilder} of type {@link Material#TROPICAL_FISH_BUCKET}. A convenience method.
     *
     * @return instance of {@code TropicalFishBucketBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static TropicalFishBucketBuilder ofTropicalFishBucket() throws IllegalArgumentException {
        return ofType(Material.TROPICAL_FISH_BUCKET);
    }

    /**
     * Gets the pattern.
     *
     * @return the pattern
     */
    public TropicalFish.Pattern pattern() {
        return this.itemMeta.getPattern();
    }

    /**
     * Sets the pattern.
     *
     * @param pattern the pattern
     * @return the builder
     */
    public TropicalFishBucketBuilder pattern(final TropicalFish.Pattern pattern) {
        this.itemMeta.setPattern(pattern);
        return this;
    }

    /**
     * Gets the pattern color.
     *
     * @return the pattern color
     */
    public DyeColor patternColor() {
        return this.itemMeta.getPatternColor();
    }

    /**
     * Sets the pattern color.
     *
     * @param patternColor the pattern color
     * @return the builder
     */
    public TropicalFishBucketBuilder patternColor(final DyeColor patternColor) {
        this.itemMeta.setPatternColor(patternColor);
        return this;
    }

    /**
     * Gets the body color.
     *
     * @return the body color
     */
    public DyeColor bodyColor() {
        return this.itemMeta.getBodyColor();
    }

    /**
     * Sets the body color.
     *
     * @param bodyColor the body color
     * @return the builder
     */
    public TropicalFishBucketBuilder bodyColor(final DyeColor bodyColor) {
        this.itemMeta.setBodyColor(bodyColor);
        return this;
    }

    /**
     * Gets whether a variant tag exists.
     * If true, a specific fish will be spawned.
     *
     * @return whether a variant tag exists
     */
    public boolean variant() {
        return this.itemMeta.hasVariant();
    }

}
