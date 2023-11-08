package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.jspecify.annotations.NullMarked;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link BlockStateMeta}.
 */
@SuppressWarnings("unused")
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
    public static BlockStateBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
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
    public static BlockStateBuilder ofType(final Material material) throws IllegalArgumentException {
        return BlockStateBuilder.of(getItem(material));
    }

    /**
     * Gets a copy of {@code BlockState}. Creates a new one if it doesn't currently exist.
     *
     * @return the {@code BlockState}
     */
    public BlockState blockState() {
        return this.itemMeta.getBlockState();
    }

    /**
     * Sets the {@code BlockState}.
     *
     * @param blockState the {@code BlockState}
     * @return the builder
     */
    public BlockStateBuilder blockState(final BlockState blockState) {
        this.itemMeta.setBlockState(blockState);
        return this;
    }

    /**
     * Gets whether a {@code BlockState} is currently attached.
     *
     * @return whether a {@code BlockState} is currently attached
     */
    public boolean hasBlockState() {
        return this.itemMeta.hasBlockState();
    }

}