package broccolai.corn.spigot.item;

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
public final class SpigotItemBuilder extends AbstractSpigotItemBuilder<SpigotItemBuilder, ItemMeta> {

    private SpigotItemBuilder(final @NonNull ItemStack itemStack, final @Nullable ItemMeta itemMeta) {
        super(itemStack, itemMeta != null
                ? itemMeta
                : Objects.requireNonNull(
                        Bukkit.getItemFactory().getItemMeta(itemStack.getType())
                ));
    }

    /**
     * Creates a {@code SpigotItemBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code SpigotItemBuilder}
     */
    public static @NonNull SpigotItemBuilder of(final @NonNull ItemStack itemStack) {
        return new SpigotItemBuilder(itemStack, itemStack.getItemMeta());
    }

    /**
     * Creates a {@code SpigotItemBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code SpigotItemBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item
     */
    public static @NonNull SpigotItemBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return SpigotItemBuilder.of(getItem(material));
    }

}
