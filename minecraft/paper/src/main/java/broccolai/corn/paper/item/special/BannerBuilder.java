package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link BannerMeta}.
 */
@SuppressWarnings("unused")
public final class BannerBuilder extends AbstractPaperItemBuilder<BannerBuilder, BannerMeta> {

    private BannerBuilder(final @NonNull ItemStack itemStack, final @NonNull BannerMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code BannerBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code BannerBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull BannerBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        final ItemMeta meta = itemStack.getItemMeta();

        if (meta instanceof final BannerMeta newMeta) {
            return new BannerBuilder(itemStack, newMeta);
        } else {
            throw new IllegalArgumentException("The ItemStack's ItemMeta must be of type "
                    + BannerMeta.class.getSimpleName()
                    + " but received ItemMeta of type "
                    + meta.getClass().getSimpleName());
        }
    }

    /**
     * Creates a {@code SkullBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code SkullBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull BannerBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        if (!material.isItem()) {
            throw new IllegalArgumentException("The Material must be an obtainable item.");
        }

        return BannerBuilder.of(new ItemStack(material));
    }

    /**
     * Gets the patterns.
     *
     * @return the patterns
     */
    public @NonNull List<Pattern> patterns() {
        return this.itemMeta.getPatterns();
    }

    /**
     * Sets the patterns.
     *
     * @param patterns the patterns
     * @return the builder
     */
    public @NonNull BannerBuilder patterns(final @NonNull List<Pattern> patterns) {
        this.itemMeta.setPatterns(patterns);
        return this;
    }

    /**
     * Gets a pattern.
     *
     * @param index the index
     * @return the pattern
     */
    public @NonNull BannerBuilder getPattern(final int index) {
        this.itemMeta.getPattern(index);
        return this;
    }

    /**
     * Sets a pattern.
     *
     * @param index   the index
     * @param pattern the pattern
     * @return the builder
     */
    public @NonNull BannerBuilder setPattern(final int index, final @NonNull Pattern pattern) {
        this.itemMeta.setPattern(index, pattern);
        return this;
    }

    /**
     * Adds a pattern.
     *
     * @param pattern the pattern
     * @return the builder
     */
    public @NonNull BannerBuilder addPattern(final @NonNull Pattern pattern) {
        this.itemMeta.addPattern(pattern);
        return this;
    }

    /**
     * Removes a pattern.
     *
     * @param index the index
     * @return the builder
     */
    public @NonNull BannerBuilder removePattern(final int index) {
        this.itemMeta.removePattern(index);
        return this;
    }

}
