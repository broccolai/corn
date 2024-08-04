package love.broccolai.corn.minecraft.item.special;

import java.util.List;
import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link CrossbowMeta}.
 */
@NullMarked
public final class CrossbowBuilder extends AbstractItemBuilder<CrossbowBuilder, CrossbowMeta> {

    private CrossbowBuilder(final ItemStack itemStack, final CrossbowMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code CrossbowBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code CrossbowBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static CrossbowBuilder crossbowBuilder(final ItemStack itemStack) throws IllegalArgumentException {
        return new CrossbowBuilder(itemStack, castMeta(itemStack.getItemMeta(), CrossbowMeta.class));
    }

    /**
     * Creates a {@code CrossbowBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code CrossbowBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static CrossbowBuilder crossbowBuilder(final Material material) throws IllegalArgumentException {
        return crossbowBuilder(itemOfMaterial(material));
    }

    /**
     * Creates a {@code CrossbowBuilder} of type {@link Material#CROSSBOW}. A convenience method.
     *
     * @return instance of {@code CrossbowBuilder}
     */
    public static CrossbowBuilder crossbowBuilder() {
        return crossbowBuilder(Material.CROSSBOW);
    }

    /**
     * Gets the charged projectiles.
     *
     * @return the charged projectiles
     */
    public List<ItemStack> chargedProjectiles() {
        return this.itemMeta.getChargedProjectiles();
    }

    /**
     * Sets the charged projectiles. Pass {@code null} to reset.
     * The items must be either of type {@link Material#ARROW} or {@link Material#FIREWORK_ROCKET}.
     *
     * @param chargedProjectiles the charged projectiles
     * @return the builder
     */
    public CrossbowBuilder chargedProjectiles(final @Nullable List<ItemStack> chargedProjectiles) {
        this.itemMeta.setChargedProjectiles(chargedProjectiles);
        return this;
    }

    /**
     * Adds a charged projectile.
     * The item must be either of type {@link Material#ARROW} or {@link Material#FIREWORK_ROCKET}.
     *
     * @param chargedProjectile the charged projectile to add
     * @return the builder
     */
    public CrossbowBuilder addChargedProjectile(final ItemStack... chargedProjectile) {
        for (final ItemStack item : chargedProjectile) {
            this.itemMeta.addChargedProjectile(item);
        }
        return this;
    }

}
