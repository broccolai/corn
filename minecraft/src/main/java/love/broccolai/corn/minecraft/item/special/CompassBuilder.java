package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link CompassMeta}.
 */
@NullMarked
public final class CompassBuilder extends AbstractItemBuilder<CompassBuilder, CompassMeta> {

    private CompassBuilder(final ItemStack itemStack, final CompassMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code CompassBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code CompassBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static CompassBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
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
    public static CompassBuilder ofType(final Material material) throws IllegalArgumentException {
        return CompassBuilder.of(getItem(material));
    }

    /**
     * Creates a {@code CompassBuilder} of type {@link Material#COMPASS}. A convenience method.
     *
     * @return instance of {@code CompassBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static CompassBuilder ofCompass() throws IllegalArgumentException {
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
    public CompassBuilder lodestone(final @Nullable Location lodestone) {
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
    public CompassBuilder lodestoneTracked(final boolean lodestoneTracked) {
        this.itemMeta.setLodestoneTracked(lodestoneTracked);
        return this;
    }

}
