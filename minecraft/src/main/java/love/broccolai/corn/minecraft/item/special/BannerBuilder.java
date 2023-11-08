package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.jspecify.annotations.NullMarked;

import java.util.List;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link BannerMeta}.
 */
@NullMarked
public final class BannerBuilder extends AbstractItemBuilder<BannerBuilder, BannerMeta> {

    private BannerBuilder(final ItemStack itemStack, final BannerMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code BannerBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code BannerBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static BannerBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
        return new BannerBuilder(itemStack, castMeta(itemStack.getItemMeta(), BannerMeta.class));
    }

    /**
     * Creates a {@code SkullBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code SkullBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static BannerBuilder ofType(final Material material) throws IllegalArgumentException {
        return BannerBuilder.of(getItem(material));
    }

    /**
     * Gets the patterns.
     *
     * @return the patterns
     */
    public List<Pattern> patterns() {
        return this.itemMeta.getPatterns();
    }

    /**
     * Sets the patterns.
     *
     * @param patterns the patterns
     * @return the builder
     */
    public BannerBuilder patterns(final List<Pattern> patterns) {
        this.itemMeta.setPatterns(patterns);
        return this;
    }

    /**
     * Gets a pattern.
     *
     * @param index the index (0-indexed)
     * @return the pattern
     */
    public Pattern getPattern(final int index) {
        return this.itemMeta.getPattern(index);
    }

    /**
     * Sets a pattern.
     *
     * @param index   the index
     * @param pattern the pattern (0-indexed)
     * @return the builder
     */
    public BannerBuilder setPattern(final int index, final Pattern pattern) {
        this.itemMeta.setPattern(index, pattern);
        return this;
    }

    /**
     * Adds a pattern.
     *
     * @param pattern the pattern
     * @return the builder
     */
    public BannerBuilder addPattern(final Pattern... pattern) {
        for (final Pattern item : pattern) {
            this.itemMeta.addPattern(item);
        }
        return this;
    }

    /**
     * Removes a pattern.
     *
     * @param index the index (0-indexed)
     * @return the builder
     */
    public BannerBuilder removePattern(final int... index) {
        for (final int item : index) {
            this.itemMeta.removePattern(item);
        }
        return this;
    }

}
