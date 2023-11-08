package love.broccolai.corn.spigot.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link ItemMeta}.
 */
@SuppressWarnings({"unused"})
@NullMarked
public final class SpigotItemBuilder extends AbstractSpigotItemBuilder<SpigotItemBuilder, ItemMeta> {

    private SpigotItemBuilder(final ItemStack itemStack, final @Nullable ItemMeta itemMeta) {
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
    public static SpigotItemBuilder of(final ItemStack itemStack) {
        return new SpigotItemBuilder(itemStack, itemStack.getItemMeta());
    }

    /**
     * Creates a {@code SpigotItemBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code SpigotItemBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item
     */
    public static SpigotItemBuilder ofType(final Material material) throws IllegalArgumentException {
        return SpigotItemBuilder.of(getItem(material));
    }

}
