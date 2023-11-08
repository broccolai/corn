package love.broccolai.corn.paper.item;


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
public final class PaperItemBuilder extends AbstractPaperItemBuilder<PaperItemBuilder, ItemMeta> {

    private PaperItemBuilder(final ItemStack itemStack, final @Nullable ItemMeta itemMeta) {
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
    public static PaperItemBuilder of(final ItemStack itemStack) {
        return new PaperItemBuilder(itemStack, itemStack.getItemMeta());
    }

    /**
     * Creates a {@code PaperItemBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code PaperItemBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item
     */
    public static PaperItemBuilder ofType(final Material material) throws IllegalArgumentException {
        return PaperItemBuilder.of(getItem(material));
    }

}
