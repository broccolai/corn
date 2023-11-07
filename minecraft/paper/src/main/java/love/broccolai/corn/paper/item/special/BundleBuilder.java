package love.broccolai.corn.paper.item.special;

import love.broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.List;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link BundleMeta}.
 */
@SuppressWarnings("unused")
public final class BundleBuilder extends AbstractPaperItemBuilder<BundleBuilder, BundleMeta> {

    private BundleBuilder(final @NonNull ItemStack itemStack, final @NonNull BundleMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@code BundleBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code BundleBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull BundleBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new BundleBuilder(itemStack, castMeta(itemStack.getItemMeta(), BundleMeta.class));
    }

    /**
     * Creates an {@code BundleBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code BundleBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull BundleBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return BundleBuilder.of(getItem(material));
    }

    /**
     * Creates a {@code BundleBuilder} of type {@link Material#BUNDLE}. A convenience method.
     *
     * @return instance of {@code BundleBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull BundleBuilder ofBundle() throws IllegalArgumentException {
        return ofType(Material.BUNDLE);
    }

    /**
     * Gets the items.
     *
     * @return the items
     */
    public @NonNull List<@NonNull ItemStack> items() {
        return this.itemMeta.getItems();
    }

    /**
     * Sets the items. Pass {@code null} to reset.
     *
     * @param items the items
     * @return the builder
     */
    public @NonNull BundleBuilder items(final @Nullable List<@NonNull ItemStack> items) {
        this.itemMeta.setItems(items);
        return this;
    }

    /**
     * Adds a item.
     *
     * @param item the item to add
     * @return the builder
     */
    public @NonNull BundleBuilder addItem(final @NonNull ItemStack... item) {
        for (final ItemStack i : item) {
            this.itemMeta.addItem(i);
        }
        return this;
    }

}
