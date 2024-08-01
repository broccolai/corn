package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.EntitySnapshot;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SpawnEggMeta;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link SpawnEggMeta}.
 */
@NullMarked
public final class SpawnEggBuilder extends AbstractItemBuilder<SpawnEggBuilder, SpawnEggMeta> {

    private SpawnEggBuilder(final ItemStack itemStack, final SpawnEggMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code SpawnEggBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code SpawnEggBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static SpawnEggBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
        return new SpawnEggBuilder(itemStack, castMeta(itemStack.getItemMeta(), SpawnEggMeta.class));
    }

    /**
     * Creates a {@code SpawnEggBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code SpawnEggBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static SpawnEggBuilder ofType(final Material material) throws IllegalArgumentException {
        return SpawnEggBuilder.of(itemOfMaterial(material));
    }

    /**
     * Gets the entity that will be spawned.
     *
     * @return the spawned entity
     */
    public @Nullable EntitySnapshot spawnedEntity() {
        return this.itemMeta.getSpawnedEntity();
    }

    /**
     * Sets the entity that will be spawned.
     *
     * @param spawnedEntity the spawned entity
     * @return the builder
     */
    public SpawnEggBuilder spawnedEntity(final EntitySnapshot spawnedEntity) {
        this.itemMeta.setSpawnedEntity(spawnedEntity);
        return this;
    }

    /**
     * Gets the custom spawned {@code EntityType}.
     *
     * @return the custom spawned {@code EntityType}
     */
    public @Nullable EntityType customSpawnedType() {
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
