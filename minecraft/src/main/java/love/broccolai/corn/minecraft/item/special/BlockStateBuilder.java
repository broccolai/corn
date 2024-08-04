package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link BlockStateMeta}.
 */
@NullMarked
public final class BlockStateBuilder extends AbstractItemBuilder<BlockStateBuilder, BlockStateMeta> {

    private BlockStateBuilder(final ItemStack itemStack, final BlockStateMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code BlockStateBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code BlockStateBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static BlockStateBuilder blockStateBuilder(final ItemStack itemStack) throws IllegalArgumentException {
        return new BlockStateBuilder(itemStack, castMeta(itemStack.getItemMeta(), BlockStateMeta.class));
    }

    /**
     * Creates a {@code BlockStateBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code BlockStateBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static BlockStateBuilder blockStateBuilder(final Material material) throws IllegalArgumentException {
        return blockStateBuilder(itemOfMaterial(material));
    }

    /**
     * Gets a copy of the block state. Creates a new one if it doesn't currently exist.
     *
     * @return the block state
     */
    public BlockState blockState() {
        return this.itemMeta.getBlockState();
    }

    /**
     * Sets the block state. Pass {@code null} to reset.
     *
     * @param blockState the block state
     * @return the builder
     */
    public BlockStateBuilder blockState(final @Nullable BlockState blockState) {
        if (blockState == null) {
            this.itemMeta.clearBlockState();
            return this;
        }
        this.itemMeta.setBlockState(blockState);
        return this;
    }

    /**
     * Gets whether a block state is currently attached.
     *
     * @return whether a block state is currently attached
     */
    public boolean hasBlockState() {
        return this.itemMeta.hasBlockState();
    }

}
