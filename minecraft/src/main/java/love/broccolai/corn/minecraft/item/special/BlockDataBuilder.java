package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.jspecify.annotations.NullMarked;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link BlockDataMeta}.
 */
@NullMarked
public final class BlockDataBuilder extends AbstractItemBuilder<BlockDataBuilder, BlockDataMeta> {

    private BlockDataBuilder(final ItemStack itemStack, final BlockDataMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code BlockDataBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code BlockDataBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static BlockDataBuilder blockDataBuilder(final ItemStack itemStack) throws IllegalArgumentException {
        return new BlockDataBuilder(itemStack, castMeta(itemStack.getItemMeta(), BlockDataMeta.class));
    }

    /**
     * Creates a {@code BlockDataBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code BlockDataBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static BlockDataBuilder blockDataBuilder(final Material material) throws IllegalArgumentException {
        return blockDataBuilder(itemOfMaterial(material));
    }

    /**
     * Gets a copy of the block data. Creates a new one if it doesn't currently exist.
     *
     * @param material the material the data should be retrieved in the context of
     * @return the block data
     */
    public BlockData blockData(final Material material) {
        return this.itemMeta.getBlockData(material);
    }

    /**
     * Sets the block data.
     *
     * @param blockData the block data
     * @return the builder
     */
    public BlockDataBuilder blockData(final BlockData blockData) {
        this.itemMeta.setBlockData(blockData);
        return this;
    }

    /**
     * Gets whether a block data is currently attached.
     *
     * @return whether a block data is currently attached
     */
    public boolean hasBlockData() {
        return this.itemMeta.hasBlockData();
    }

}
