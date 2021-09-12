package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link MapMeta}.
 */
@SuppressWarnings("unused")
public final class MapBuilder extends AbstractPaperItemBuilder<MapBuilder, MapMeta> {

    private MapBuilder(final @NonNull ItemStack itemStack, final @NonNull MapMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code MapBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code MapBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull MapBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
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
    public static @NonNull MapBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return MapBuilder.of(getItem(material));
    }

    /**
     * Gets the {@code Color}. Pass {@code null} to clear.
     *
     * @return the {@code Color}
     */
    public @Nullable Color color() {
        return this.itemMeta.getColor();
    }

    /**
     * Sets the {@code Color}. Pass {@code null} to reset.
     *
     * @param color the {@code Color}
     * @return the builder
     */
    public @NonNull MapBuilder color(final @Nullable Color color) {
        this.itemMeta.setColor(color);
        return this;
    }

    /**
     * Gets the location name.
     *
     * @return the location name
     */
    public @Nullable String locationName() {
        return this.itemMeta.getLocationName();
    }

    /**
     * Sets the location name. Pass {@code null} to reset.
     *
     * @param locationName the location name
     * @return the builder
     */
    public @NonNull MapBuilder locationName(final @Nullable String locationName) {
        this.itemMeta.setLocationName(locationName);
        return this;
    }

    /**
     * Gets the {@code MapView}.
     *
     * @return the {@code MapView}
     */
    public @Nullable MapView mapView() {
        return this.itemMeta.getMapView();
    }

    /**
     * Sets the {@code MapView}. Pass {@code null} to reset.
     *
     * @param mapView the {@code MapView}
     * @return the builder
     */
    public @NonNull MapBuilder mapView(final @Nullable MapView mapView) {
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
    public @NonNull MapBuilder scaling(final boolean scaling) {
        this.itemMeta.setScaling(scaling);
        return this;
    }

}
