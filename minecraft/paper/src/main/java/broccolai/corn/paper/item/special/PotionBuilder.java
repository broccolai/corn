package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link PotionMeta}.
 */
@SuppressWarnings("unused")
public final class PotionBuilder extends AbstractPaperItemBuilder<PotionBuilder, PotionMeta> {

    private PotionBuilder(final @NonNull ItemStack itemStack, final @NonNull PotionMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code PotionBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code PotionBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull PotionBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new PotionBuilder(itemStack, AridUtil.castMeta(itemStack.getItemMeta(), PotionMeta.class));
    }

    /**
     * Creates a {@code PotionBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code PotionBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull PotionBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return PotionBuilder.of(AridUtil.getItem(material));
    }

    /**
     * Gets the custom effects.
     *
     * @return the custom effects
     */
    public @NonNull List<@NonNull PotionEffect> customEffects() {
        return this.itemMeta.getCustomEffects();
    }

    /**
     * Adds a custom effect.
     *
     * @param customEffect the custom effect to add
     * @param overwrite    whether to overwrite {@code PotionEffect}s of the same type
     * @return the builder
     */
    public @NonNull PotionBuilder addCustomEffect(final @NonNull PotionEffect customEffect, final boolean overwrite) {
        this.itemMeta.addCustomEffect(customEffect, true);
        return this;
    }

    /**
     * Removes a custom effect type.
     *
     * @param customEffectType the custom effect type to remove
     * @return the builder
     */
    public @NonNull PotionBuilder removeCustomEffect(final @NonNull PotionEffectType... customEffectType) {
        for (final PotionEffectType item : customEffectType) {
            this.itemMeta.removeCustomEffect(item);
        }
        return this;
    }

    /**
     * Gets whether a custom effect type is present.
     *
     * @param customEffectType the custom effect type
     * @return the builder
     */
    public boolean hasCustomEffect(final @NonNull PotionEffectType customEffectType) {
        return this.itemMeta.hasCustomEffect(customEffectType);
    }

    /**
     * Clears all custom effects.
     *
     * @return the builder
     */
    public @NonNull PotionBuilder clearCustomEffects() {
        this.itemMeta.clearCustomEffects();
        return this;
    }

    /**
     * Gets the {@code Color}.
     *
     * @return the {@code Color}
     */
    public @Nullable Color color() {
        return this.itemMeta.getColor();
    }

    /**
     * Sets the {@code Color}.
     *
     * @param color the {@code Color}
     * @return the builder
     */
    public @NonNull PotionBuilder color(final @Nullable Color color) {
        this.itemMeta.setColor(color);
        return this;
    }

    /**
     * Gets the base {@code PotionData}.
     *
     * @return the base {@code PotionData}
     */
    public @NonNull PotionData basePotionData() {
        return this.itemMeta.getBasePotionData();
    }

    /**
     * Sets the base {@code PotionData}.
     *
     * @param basePotionData the base {@code PotionData}
     * @return the builder
     */
    public @NonNull PotionBuilder basePotionData(final @NonNull PotionData basePotionData) {
        this.itemMeta.setBasePotionData(basePotionData);
        return this;
    }

}
