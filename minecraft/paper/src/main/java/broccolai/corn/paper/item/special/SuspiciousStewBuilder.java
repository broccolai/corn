package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SuspiciousStewMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link SuspiciousStewMeta}.
 */
@SuppressWarnings("unused")
public final class SuspiciousStewBuilder extends AbstractPaperItemBuilder<SuspiciousStewBuilder, SuspiciousStewMeta> {

    private SuspiciousStewBuilder(final @NonNull ItemStack itemStack, final @NonNull SuspiciousStewMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code SuspiciousStewBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code SuspiciousStewBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull SuspiciousStewBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new SuspiciousStewBuilder(itemStack, AridUtil.castMeta(itemStack.getItemMeta(), SuspiciousStewMeta.class));
    }

    /**
     * Creates a {@code SuspiciousStewBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code SuspiciousStewBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull SuspiciousStewBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return SuspiciousStewBuilder.of(AridUtil.getItem(material));
    }

}
