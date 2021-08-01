package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link FireworkEffectMeta}.
 */
@SuppressWarnings("unused")
public final class FireworkEffectBuilder extends AbstractPaperItemBuilder<FireworkEffectBuilder, FireworkEffectMeta> {

    private FireworkEffectBuilder(final @NonNull ItemStack itemStack, final @NonNull FireworkEffectMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code FireworkEffectBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code FireworkEffectBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull FireworkEffectBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new FireworkEffectBuilder(itemStack, AridUtil.castMeta(itemStack.getItemMeta(), FireworkEffectMeta.class));
    }

    /**
     * Creates a {@code FireworkEffectBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code FireworkEffectBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull FireworkEffectBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return FireworkEffectBuilder.of(AridUtil.getItem(material));
    }

    /**
     * Gets the {@code FireworkEffect}.
     *
     * @return the {@code FireworkEffect}
     */
    public @Nullable FireworkEffect fireworkEffect() {
        return this.itemMeta.getEffect();
    }

    /**
     * Sets the {@code FireworkEffect}.
     *
     * @param fireworkEffect the {@code FireworkEffect}
     * @return the builder
     */
    public @NonNull FireworkEffectBuilder fireworkEffect(final @Nullable FireworkEffect fireworkEffect) {
        this.itemMeta.setEffect(fireworkEffect);
        return this;
    }

}
