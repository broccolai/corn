package love.broccolai.corn.paper.item.special;

import love.broccolai.corn.paper.item.AbstractItemBuilder;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link FireworkEffectMeta}.
 */
@SuppressWarnings("unused")
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
    public static FireworkEffectBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
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
    public static FireworkEffectBuilder ofType(final Material material) throws IllegalArgumentException {
        return FireworkEffectBuilder.of(getItem(material));
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
     * Sets the {@code FireworkEffect}. Pass {@code null} to reset.
     *
     * @param fireworkEffect the {@code FireworkEffect}
     * @return the builder
     */
    public FireworkEffectBuilder fireworkEffect(final @Nullable FireworkEffect fireworkEffect) {
        this.itemMeta.setEffect(fireworkEffect);
        return this;
    }

}
