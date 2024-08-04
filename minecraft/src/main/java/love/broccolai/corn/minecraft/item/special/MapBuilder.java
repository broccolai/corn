package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link MapMeta}.
 */
@NullMarked
public final class MapBuilder extends AbstractItemBuilder<MapBuilder, MapMeta> {

    private MapBuilder(final ItemStack itemStack, final MapMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code MapBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code MapBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static MapBuilder mapBuilder(final ItemStack itemStack) throws IllegalArgumentException {
        return new MapBuilder(itemStack, castMeta(itemStack.getItemMeta(), MapMeta.class));
    }

    /**
     * Creates a {@code MapBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code MapBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static MapBuilder mapBuilder(final Material material) throws IllegalArgumentException {
        return mapBuilder(itemOfMaterial(material));
    }

    /**
     * Gets the color.
     *
     * @return the color
     */
    public @Nullable Color color() {
        return this.itemMeta.getColor();
    }

    /**
     * Sets the color. Pass {@code null} to reset.
     *
     * @param color the color
     * @return the builder
     */
    public MapBuilder color(final @Nullable Color color) {
        this.itemMeta.setColor(color);
        return this;
    }

    /**
     * Gets the map view.
     *
     * @return the map view
     */
    public @Nullable MapView mapView() {
        return this.itemMeta.getMapView();
    }

    /**
     * Sets the map view. Pass {@code null} to reset.
     *
     * @param mapView the map view
     * @return the builder
     */
    public MapBuilder mapView(final @Nullable MapView mapView) {
        this.itemMeta.setMapView(mapView);
        return this;
    }

    /**
     * Gets whether the map is scaling.
     *
     * @return whether the map is scaling
     */
    public boolean scaling() {
        return this.itemMeta.isScaling();
    }

    /**
     * Sets whether the map is scaling.
     *
     * @param scaling whether the map is scaling
     * @return the builder
     */
    public MapBuilder scaling(final boolean scaling) {
        this.itemMeta.setScaling(scaling);
        return this;
    }

}
