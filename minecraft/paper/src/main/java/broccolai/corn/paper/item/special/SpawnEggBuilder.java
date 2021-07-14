package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SpawnEggMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link SpawnEggMeta}.
 */
@SuppressWarnings("unused")
public final class SpawnEggBuilder extends AbstractPaperItemBuilder<SpawnEggBuilder, SpawnEggMeta> {

    private SpawnEggBuilder(final @NonNull ItemStack itemStack, final @NonNull SpawnEggMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code SpawnEggBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code SpawnEggBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull SpawnEggBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new SpawnEggBuilder(itemStack, AridUtil.castMeta(itemStack.getItemMeta(), SpawnEggMeta.class));
    }

    /**
     * Creates a {@code SpawnEggBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code SpawnEggBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull SpawnEggBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return SpawnEggBuilder.of(AridUtil.getItem(material));
    }

}
