package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link Damageable}.
 */
@SuppressWarnings("unused")
public final class DamageableBuilder extends AbstractPaperItemBuilder<DamageableBuilder, Damageable> {

    private DamageableBuilder(final @NonNull ItemStack itemStack, final @NonNull Damageable itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@code DamageableBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code DamageableBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull DamageableBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new DamageableBuilder(itemStack, castMeta(itemStack.getItemMeta(), Damageable.class));
    }

    /**
     * Creates an {@code DamageableBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code DamageableBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull DamageableBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return DamageableBuilder.of(getItem(material));
    }

    /**
     * Gets the damage.
     *
     * @return the damage
     */
    public @Nullable Integer damage() {
        if (!this.itemMeta.hasDamage()) {
            return null;
        }
        return this.itemMeta.getDamage();
    }

    /**
     * Sets the damage.
     *
     * @param damage the damage
     * @return the builder
     */
    public @NonNull DamageableBuilder damage(final @NonNull Integer damage) {
        this.itemMeta.setDamage(damage);
        return this;
    }

}
