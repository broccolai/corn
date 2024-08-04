package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link FireworkEffectMeta}.
 */
@NullMarked
public final class FireworkEffectBuilder extends AbstractItemBuilder<FireworkEffectBuilder, FireworkEffectMeta> {

    private FireworkEffectBuilder(final ItemStack itemStack, final FireworkEffectMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code FireworkEffectBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code FireworkEffectBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static FireworkEffectBuilder fireworkEffectBuilder(final ItemStack itemStack) throws IllegalArgumentException {
        return new FireworkEffectBuilder(itemStack, castMeta(itemStack.getItemMeta(), FireworkEffectMeta.class));
    }

    /**
     * Creates a {@code FireworkEffectBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code FireworkEffectBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static FireworkEffectBuilder fireworkEffectBuilder(final Material material) throws IllegalArgumentException {
        return fireworkEffectBuilder(itemOfMaterial(material));
    }

    /**
     * Creates a {@code FireworkEffectBuilder} of type {@link Material#FIREWORK_STAR}. A convenience method.
     *
     * @return instance of {@code FireworkEffectBuilder}
     */
    public static FireworkEffectBuilder fireworkEffectBuilder() {
        return fireworkEffectBuilder(Material.FIREWORK_STAR);
    }

    /**
     * Gets the firework effect.
     *
     * @return the firework effect
     */
    public @Nullable FireworkEffect effect() {
        return this.itemMeta.getEffect();
    }

    /**
     * Sets the firework effect. Pass {@code null} to reset.
     *
     * @param effect the firework effect
     * @return the builder
     */
    public FireworkEffectBuilder effect(final @Nullable FireworkEffect effect) {
        this.itemMeta.setEffect(effect);
        return this;
    }

}
