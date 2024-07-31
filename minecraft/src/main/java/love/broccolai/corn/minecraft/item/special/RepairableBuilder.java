package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Repairable;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link Repairable}.
 */
@NullMarked
public final class RepairableBuilder extends AbstractItemBuilder<RepairableBuilder, Repairable> {

    private RepairableBuilder(final ItemStack itemStack, final Repairable itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code RepairableBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code RepairableBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static RepairableBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
        return new RepairableBuilder(itemStack, castMeta(itemStack.getItemMeta(), Repairable.class));
    }

    /**
     * Creates a {@code RepairableBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code RepairableBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static RepairableBuilder ofType(final Material material) throws IllegalArgumentException {
        return RepairableBuilder.of(itemOfMaterial(material));
    }

    /**
     * Gets the repair cost.
     *
     * @return the repair cost
     */
    public @Nullable Integer repairCost() {
        if (this.itemMeta.hasRepairCost()) {
            return null;
        }
        return this.itemMeta.getRepairCost();
    }

    /**
     * Sets the repair cost.
     *
     * @param repairCost the repair cost
     * @return the builder
     */
    public RepairableBuilder repairCost(final Integer repairCost) {
        this.itemMeta.setRepairCost(repairCost);
        return this;
    }

}
