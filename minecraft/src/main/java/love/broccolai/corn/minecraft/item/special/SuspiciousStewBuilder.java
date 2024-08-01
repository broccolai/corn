package love.broccolai.corn.minecraft.item.special;

import io.papermc.paper.potion.SuspiciousEffectEntry;
import java.util.Collections;
import java.util.List;
import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SuspiciousStewMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link SuspiciousStewMeta}.
 */
@NullMarked
public final class SuspiciousStewBuilder extends AbstractItemBuilder<SuspiciousStewBuilder, SuspiciousStewMeta> {

    private SuspiciousStewBuilder(final ItemStack itemStack, final SuspiciousStewMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code SuspiciousStewBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code SuspiciousStewBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static SuspiciousStewBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
        return new SuspiciousStewBuilder(itemStack, castMeta(itemStack.getItemMeta(), SuspiciousStewMeta.class));
    }

    /**
     * Creates a {@code SuspiciousStewBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code SuspiciousStewBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static SuspiciousStewBuilder ofType(final Material material) throws IllegalArgumentException {
        return SuspiciousStewBuilder.of(itemOfMaterial(material));
    }

    /**
     * Creates a {@code SuspiciousStewBuilder} of type {@link Material#SUSPICIOUS_STEW}. A convenience method.
     *
     * @return instance of {@code SuspiciousStewBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static SuspiciousStewBuilder ofSuspiciousStew() throws IllegalArgumentException {
        return ofType(Material.SUSPICIOUS_STEW);
    }

    /**
     * Gets the custom effects.
     *
     * @return the custom effects
     */
    public List<PotionEffect> customEffects() {
        if (!this.itemMeta.hasCustomEffects()) {
            // we could return null, but the API generally returns empty lists
            // instead, so we'll do this for consistency.
            return Collections.emptyList();
        }
        return this.itemMeta.getCustomEffects();
    }

    /**
     * Sets the custom effects. Pass {@code null} to reset.
     *
     * @param customEffects custom effects
     * @return the builder
     */
    public SuspiciousStewBuilder customEffects(final @Nullable List<SuspiciousEffectEntry> customEffects) {
        this.itemMeta.clearCustomEffects();
        if (customEffects != null) {
            for (final SuspiciousEffectEntry item : customEffects) {
                this.itemMeta.addCustomEffect(item, true);
            }
        }
        return this;
    }

    /**
     * Adds a custom effect.
     *
     * @param customEffect the custom effect to add
     * @param overwrite    whether to overwrite {@code PotionEffect}s of the same type
     * @return the builder
     */
    public SuspiciousStewBuilder addCustomEffect(final SuspiciousEffectEntry customEffect, final boolean overwrite) {
        this.itemMeta.addCustomEffect(customEffect, overwrite);
        return this;
    }

    /**
     * Removes a custom effect type.
     *
     * @param customEffectType the custom effect type to remove
     * @return the builder
     */
    public SuspiciousStewBuilder removeCustomEffect(final PotionEffectType... customEffectType) {
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
    public boolean hasCustomEffect(final PotionEffectType customEffectType) {
        return this.itemMeta.hasCustomEffect(customEffectType);
    }

}
