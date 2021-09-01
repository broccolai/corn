package broccolai.corn.paper.item;

import broccolai.corn.spigot.item.AridUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link ItemMeta}.
 */
@SuppressWarnings({"unused"})
public final class PaperItemBuilder extends AbstractPaperItemBuilder<PaperItemBuilder, ItemMeta> {

    private PaperItemBuilder(final @NonNull ItemStack itemStack, final @Nullable ItemMeta itemMeta) {
        super(itemStack, itemMeta != null
                ? itemMeta
                : Objects.requireNonNull(
                        Bukkit.getItemFactory().getItemMeta(itemStack.getType())
                ));
    }

    /**
     * Creates a {@code PaperItemBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code PaperItemBuilder}
     */
    public static @NonNull PaperItemBuilder of(final @NonNull ItemStack itemStack) {
        return new PaperItemBuilder(itemStack, itemStack.getItemMeta());
    }

    /**
     * Creates a {@code PaperItemBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code PaperItemBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item
     */
    public static @NonNull PaperItemBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return PaperItemBuilder.of(AridUtil.getItem(material));
    }

}
