package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link CompassMeta}.
 */
@SuppressWarnings("unused")
public final class CompassBuilder extends AbstractPaperItemBuilder<CompassBuilder, CompassMeta> {

    private CompassBuilder(final @NonNull ItemStack itemStack, final @NonNull CompassMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code CompassBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code CompassBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull CompassBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new CompassBuilder(itemStack, castMeta(itemStack.getItemMeta(), CompassMeta.class));
    }

    /**
     * Creates a {@code CompassBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code CompassBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull CompassBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return CompassBuilder.of(getItem(material));
    }

    /**
     * Creates a {@code CompassBuilder} of type {@link Material#COMPASS}. A convenience method.
     *
     * @return instance of {@code CompassBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull CompassBuilder ofCompass() throws IllegalArgumentException {
        return ofType(Material.COMPASS);
    }

    /**
     * Gets the {@code Location} of the lodestone that this compass is tracking.
     *
     * @return the the lodestone
     */
    public @Nullable Location lodestone() {
        return this.itemMeta.getLodestone();
    }

    /**
     * Sets the {@code Location} of the lodestone that this compass is tracking. Pass {@code null} to reset.
     *
     * @param lodestone the the lodestone
     * @return the builder
     */
    public @NonNull CompassBuilder lodestone(final @Nullable Location lodestone) {
        this.itemMeta.setLodestone(lodestone);
        return this;
    }

    /**
     * Gets whether the compass is tracking the lodestone.
     *
     * @return whether the compass is tracking the lodestone
     */
    public boolean lodestoneTracked() {
        return this.itemMeta.isLodestoneTracked();
    }

    /**
     * Sets whether the compass is tracking the lodestone.
     *
     * @param lodestoneTracked whether the compass is tracking the lodestone
     * @return the builder
     */
    public @NonNull CompassBuilder lodestoneTracked(final boolean lodestoneTracked) {
        this.itemMeta.setLodestoneTracked(lodestoneTracked);
        return this;
    }

}
