package love.broccolai.corn.paper.item.special;

import love.broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link CrossbowMeta}.
 */
@SuppressWarnings("unused")
public final class CrossbowBuilder extends AbstractPaperItemBuilder<CrossbowBuilder, CrossbowMeta> {

    private CrossbowBuilder(final @NonNull ItemStack itemStack, final @NonNull CrossbowMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code CrossbowBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code CrossbowBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull CrossbowBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
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
    public static @NonNull CrossbowBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return CrossbowBuilder.of(getItem(material));
    }

    /**
     * Creates a {@code CrossbowBuilder} of type {@link Material#CROSSBOW}. A convenience method.
     *
     * @return instance of {@code CrossbowBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull CrossbowBuilder ofCrossbow() throws IllegalArgumentException {
        return ofType(Material.CROSSBOW);
    }

    /**
     * Gets the charged projectiles.
     *
     * @return the charged projectiles
     */
    public @NonNull List<@NonNull ItemStack> chargedProjectiles() {
        return this.itemMeta.getChargedProjectiles();
    }

    /**
     * Sets the charged projectiles. Pass {@code null} to reset.
     * The items must be either of type {@link Material#ARROW} or {@link Material#FIREWORK_ROCKET}.
     *
     * @param chargedProjectiles the charged projectiles
     * @return the builder
     */
    public @NonNull CrossbowBuilder chargedProjectiles(final @Nullable List<@NonNull ItemStack> chargedProjectiles) {
        this.itemMeta.setChargedProjectiles(chargedProjectiles);
        return this;
    }

    /**
     * Adds a charged projectile.
     * Must be either of type {@link Material#ARROW} or {@link Material#FIREWORK_ROCKET}.
     *
     * @param chargedProjectile the charged projectile to add
     * @return the builder
     */
    public @NonNull CrossbowBuilder addChargedProjectile(final @NonNull ItemStack... chargedProjectile) {
        for (final ItemStack item : chargedProjectile) {
            this.itemMeta.addChargedProjectile(item);
        }
        return this;
    }

}
