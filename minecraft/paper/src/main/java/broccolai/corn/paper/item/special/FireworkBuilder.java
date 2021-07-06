package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link FireworkMeta}.
 */
@SuppressWarnings("unused")
public final class FireworkBuilder extends AbstractPaperItemBuilder<FireworkBuilder, FireworkMeta> {

    private FireworkBuilder(final @NonNull ItemStack itemStack, final @NonNull FireworkMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code FireworkBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code FireworkBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull FireworkBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        final ItemMeta meta = itemStack.getItemMeta();

        if (meta instanceof final FireworkMeta newMeta) {
            return new FireworkBuilder(itemStack, newMeta);
        } else {
            throw new IllegalArgumentException("The ItemStack's ItemMeta must be of type "
                    + FireworkMeta.class.getSimpleName()
                    + " but received ItemMeta of type "
                    + meta.getClass().getSimpleName());
        }
    }

    /**
     * Creates a {@code FireworkBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code FireworkBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item
     */
    public static @NonNull FireworkBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        if (!material.isItem()) {
            throw new IllegalArgumentException("The Material must be an obtainable item.");
        }

        return FireworkBuilder.of(new ItemStack(material));
    }

}
