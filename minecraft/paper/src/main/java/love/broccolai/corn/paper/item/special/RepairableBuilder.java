package love.broccolai.corn.paper.item.special;

import love.broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Repairable;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link Repairable}.
 */
@SuppressWarnings("unused")
public final class RepairableBuilder extends AbstractPaperItemBuilder<RepairableBuilder, Repairable> {

    private RepairableBuilder(final @NonNull ItemStack itemStack, final @NonNull Repairable itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@code RepairableBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code RepairableBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull RepairableBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new RepairableBuilder(itemStack, castMeta(itemStack.getItemMeta(), Repairable.class));
    }

    /**
     * Creates an {@code RepairableBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code RepairableBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull RepairableBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return RepairableBuilder.of(getItem(material));
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
    public @NonNull RepairableBuilder repairCost(final @NonNull Integer repairCost) {
        this.itemMeta.setRepairCost(repairCost);
        return this;
    }

}
