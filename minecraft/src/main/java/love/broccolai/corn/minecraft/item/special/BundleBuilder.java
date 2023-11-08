package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link BundleMeta}.
 */
@NullMarked
public final class BundleBuilder extends AbstractItemBuilder<BundleBuilder, BundleMeta> {

    private BundleBuilder(final ItemStack itemStack, final BundleMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@code BundleBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code BundleBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static BundleBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
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
    public static BundleBuilder ofType(final Material material) throws IllegalArgumentException {
        return BundleBuilder.of(getItem(material));
    }

    /**
     * Creates a {@code BundleBuilder} of type {@link Material#BUNDLE}. A convenience method.
     *
     * @return instance of {@code BundleBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static BundleBuilder ofBundle() throws IllegalArgumentException {
        return ofType(Material.BUNDLE);
    }

    /**
     * Gets the items.
     *
     * @return the items
     */
    public List<ItemStack> items() {
        return this.itemMeta.getItems();
    }

    /**
     * Sets the items. Pass {@code null} to reset.
     *
     * @param items the items
     * @return the builder
     */
    public BundleBuilder items(final @Nullable List<ItemStack> items) {
        this.itemMeta.setItems(items);
        return this;
    }

    /**
     * Adds a item.
     *
     * @param item the item to add
     * @return the builder
     */
    public BundleBuilder addItem(final ItemStack... item) {
        for (final ItemStack i : item) {
            this.itemMeta.addItem(i);
        }
        return this;
    }

}
