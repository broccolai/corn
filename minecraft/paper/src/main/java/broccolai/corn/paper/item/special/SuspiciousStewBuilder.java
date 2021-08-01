package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SuspiciousStewMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link SuspiciousStewMeta}.
 */
@SuppressWarnings("unused")
public final class SuspiciousStewBuilder extends AbstractPaperItemBuilder<SuspiciousStewBuilder, SuspiciousStewMeta> {

    private SuspiciousStewBuilder(final @NonNull ItemStack itemStack, final @NonNull SuspiciousStewMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code SuspiciousStewBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code SuspiciousStewBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull SuspiciousStewBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new SuspiciousStewBuilder(itemStack, AridUtil.castMeta(itemStack.getItemMeta(), SuspiciousStewMeta.class));
    }

    /**
     * Creates a {@code SuspiciousStewBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code SuspiciousStewBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull SuspiciousStewBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return SuspiciousStewBuilder.of(AridUtil.getItem(material));
    }

    /**
     * Gets the custom effects.
     *
     * @return the custom effects
     */
    public @NonNull List<@NonNull PotionEffect> getCustomEffects() {
        return this.itemMeta.getCustomEffects();
    }

    /**
     * Adds a custom effect.
     *
     * @param customEffect the custom effect to add
     * @param overwrite    whether to overwrite {@code PotionEffect}s of the same type
     * @return the builder
     */
    public @NonNull SuspiciousStewBuilder addCustomEffect(final @NonNull PotionEffect customEffect, final boolean overwrite) {
        this.itemMeta.addCustomEffect(customEffect, true);
        return this;
    }

    /**
     * Removes a custom effect type.
     *
     * @param customEffectType the custom effect type to remove
     * @return the builder
     */
    public @NonNull SuspiciousStewBuilder removeCustomEffect(final @NonNull PotionEffectType... customEffectType) {
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
    public @NonNull SuspiciousStewBuilder clearCustomEffects() {
        this.itemMeta.clearCustomEffects();
        return this;
    }

}
