package love.broccolai.corn.minecraft.item;

import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link ItemMeta}.
 */
@SuppressWarnings({"unused"})
@NullMarked
public final class ItemBuilder extends AbstractItemBuilder<ItemBuilder, ItemMeta> {

    private ItemBuilder(final ItemStack itemStack, final @Nullable ItemMeta itemMeta) {
        super(itemStack, itemMeta != null
            ? itemMeta
            : Objects.requireNonNull(Bukkit.getItemFactory().getItemMeta(itemStack.getType()))
        );
    }

    /**
     * Creates a {@code ItemBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code ItemBuilder}
     */
    public static ItemBuilder of(final ItemStack itemStack) {
        return new ItemBuilder(itemStack, itemStack.getItemMeta());
    }

    /**
     * Creates a {@code ItemBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code ItemBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item
     */
    public static ItemBuilder ofType(final Material material) throws IllegalArgumentException {
        return ItemBuilder.of(itemOfMaterial(material));
    }

}
