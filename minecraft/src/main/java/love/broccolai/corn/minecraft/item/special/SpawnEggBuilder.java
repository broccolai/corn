package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SpawnEggMeta;
import org.jspecify.annotations.NullMarked;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link SpawnEggMeta}.
 */
@NullMarked
public final class SpawnEggBuilder extends AbstractItemBuilder<SpawnEggBuilder, SpawnEggMeta> {

    private SpawnEggBuilder(final ItemStack itemStack, final SpawnEggMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@code SpawnEggBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code SpawnEggBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static SpawnEggBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
        return new SpawnEggBuilder(itemStack, castMeta(itemStack.getItemMeta(), SpawnEggMeta.class));
    }

    /**
     * Creates an {@code SpawnEggBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code SpawnEggBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static SpawnEggBuilder ofType(final Material material) throws IllegalArgumentException {
        return SpawnEggBuilder.of(getItem(material));
    }

    /**
     * Gets the custom spawned {@code EntityType}.
     *
     * @return the custom spawned {@code EntityType}
     */
    public EntityType customSpawnedType() {
        return this.itemMeta.getCustomSpawnedType();
    }

    /**
     * Sets the custom spawned {@code EntityType}.
     *
     * @param customSpawnedType the custom spawned {@code EntityType}
     * @return the builder
     */
    public SpawnEggBuilder customSpawnedType(final EntityType customSpawnedType) {
        this.itemMeta.setCustomSpawnedType(customSpawnedType);
        return this;
    }

}
