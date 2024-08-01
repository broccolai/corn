package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.OminousBottleMeta;
import org.checkerframework.common.value.qual.IntRange;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link OminousBottleMeta}.
 */
@SuppressWarnings("unused")
@NullMarked
public final class OminousBottleBuilder extends AbstractItemBuilder<OminousBottleBuilder, OminousBottleMeta> {

    private OminousBottleBuilder(final ItemStack itemStack, final OminousBottleMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@code OminousBottleBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code OminousBottleBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static OminousBottleBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
        return new OminousBottleBuilder(itemStack, castMeta(itemStack.getItemMeta(), OminousBottleMeta.class));
    }

    /**
     * Creates an {@code OminousBottleBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code OminousBottleBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static OminousBottleBuilder ofType(final Material material) throws IllegalArgumentException {
        return OminousBottleBuilder.of(itemOfMaterial(material));
    }

    /**
     * Creates a {@code OminousBottleBuilder} of type {@link Material#OMINOUS_BOTTLE}. A convenience method.
     *
     * @return instance of {@code OminousBottleBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static OminousBottleBuilder ofOminousBottle() throws IllegalArgumentException {
        return ofType(Material.OMINOUS_BOTTLE);
    }

    /**
     * Gets the amplifier for the bad omen effect.
     *
     * @return the amplifier
     */
    public @Nullable Integer amplifier() {
        if (!this.itemMeta.hasAmplifier()) {
            return null;
        }
        return this.itemMeta.getAmplifier();
    }

    /**
     * Sets the amplifier for the bad omen effect.
     *
     * @param amplifier the amplifier, between 0 and 4
     * @return the builder
     */
    public OminousBottleBuilder amplifier(final @IntRange(from = 0, to = 4) Integer amplifier) {
        this.itemMeta.setAmplifier(amplifier);
        return this;
    }

}
