package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link Damageable}.
 */
@NullMarked
public final class DamageableBuilder extends AbstractItemBuilder<DamageableBuilder, Damageable> {

    private DamageableBuilder(final ItemStack itemStack, final Damageable itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code DamageableBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code DamageableBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static DamageableBuilder damageableBuilder(final ItemStack itemStack) throws IllegalArgumentException {
        return new DamageableBuilder(itemStack, castMeta(itemStack.getItemMeta(), Damageable.class));
    }

    /**
     * Creates a {@code DamageableBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code DamageableBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static DamageableBuilder damageableBuilder(final Material material) throws IllegalArgumentException {
        return damageableBuilder(itemOfMaterial(material));
    }

    /**
     * Gets the damage.
     *
     * @return the damage
     */
    public @Nullable Integer damage() {
        if (!this.itemMeta.hasDamageValue()) {
            return null;
        }
        return this.itemMeta.getDamage();
    }

    /**
     * Sets the damage. Pass {@code null} to reset.
     *
     * @param damage the damage
     * @return the builder
     */
    public DamageableBuilder damage(final @Nullable Integer damage) {
        if (damage == null) {
            this.itemMeta.resetDamage();
            return this;
        }
        this.itemMeta.setDamage(damage);
        return this;
    }

    /**
     * Gets the maximum amount of damage.
     *
     * @return the maximum amount of damage
     */
    public @Nullable Integer maxDamage() {
        if (!this.itemMeta.hasMaxDamage()) {
            return null;
        }
        return this.itemMeta.getMaxDamage();
    }

    /**
     * Sets the maximum amount of damage.
     *
     * @param maxDamage the maximum amount of damage
     * @return the builder
     */
    public DamageableBuilder maxDamage(final @Nullable Integer maxDamage) {
        this.itemMeta.setMaxDamage(maxDamage);
        return this;
    }

}
