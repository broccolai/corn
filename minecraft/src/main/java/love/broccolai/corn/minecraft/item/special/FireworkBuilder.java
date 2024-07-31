package love.broccolai.corn.minecraft.item.special;

import java.util.List;
import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jspecify.annotations.NullMarked;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link FireworkMeta}.
 */
@NullMarked
public final class FireworkBuilder extends AbstractItemBuilder<FireworkBuilder, FireworkMeta> {

    private FireworkBuilder(final ItemStack itemStack, final FireworkMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code FireworkBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code FireworkBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static FireworkBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
        return new FireworkBuilder(itemStack, castMeta(itemStack.getItemMeta(), FireworkMeta.class));
    }

    /**
     * Creates a {@code FireworkBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code FireworkBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static FireworkBuilder ofType(final Material material) throws IllegalArgumentException {
        return FireworkBuilder.of(itemOfMaterial(material));
    }

    /**
     * Creates a {@code FireworkBuilder} of type {@link Material#FIREWORK_ROCKET}. A convenience method.
     *
     * @return instance of {@code FireworkBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static FireworkBuilder ofFireworkRocket() throws IllegalArgumentException {
        return ofType(Material.FIREWORK_ROCKET);
    }

    /**
     * Gets the power.
     *
     * @return the power
     */
    public int power() {
        return this.itemMeta.getPower();
    }

    /**
     * Sets the power.
     *
     * @param power the power
     * @return the builder
     */
    public FireworkBuilder power(final int power) {
        this.itemMeta.setPower(power);
        return this;
    }

    /**
     * Gets the {@code FireworkEffect}s.
     *
     * @return the {@code FireworkEffect}s
     */
    public List<FireworkEffect> effects() {
        return this.itemMeta.getEffects();
    }

    /**
     * Sets the {@code FireworkEffect}s.
     *
     * @param effects the {@code FireworkEffect}s
     * @return the builder
     */
    public FireworkBuilder effects(final List<FireworkEffect> effects) {
        this.itemMeta.clearEffects();
        this.itemMeta.addEffects(effects);
        return this;
    }

    /**
     * Adds a {@code FireworkEffect}.
     *
     * @param effect the {@code FireworkEffect} to add
     * @return the builder
     */
    public FireworkBuilder addEffect(final FireworkEffect... effect) {
        this.itemMeta.addEffects(effect);
        return this;
    }

}
