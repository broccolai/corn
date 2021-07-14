package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link MapMeta}.
 */
@SuppressWarnings("unused")
public final class MapBuilder extends AbstractPaperItemBuilder<MapBuilder, MapMeta> {

    private MapBuilder(final @NonNull ItemStack itemStack, final @NonNull MapMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code MapBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code MapBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull MapBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new MapBuilder(itemStack, AridUtil.castMeta(itemStack.getItemMeta(), MapMeta.class));
    }

    /**
     * Creates a {@code MapBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code MapBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull MapBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return MapBuilder.of(AridUtil.getItem(material));
    }

}
