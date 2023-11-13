package love.broccolai.corn.minecraft.item.special;

import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.MusicInstrument;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MusicInstrumentMeta;
import org.jspecify.annotations.NullMarked;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link MusicInstrumentMeta}.
 */
@NullMarked
public final class MusicInstrumentBuilder extends AbstractItemBuilder<MusicInstrumentBuilder, MusicInstrumentMeta> {

    private MusicInstrumentBuilder(final ItemStack itemStack, final MusicInstrumentMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@code MusicInstrumentBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code MusicInstrumentBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static MusicInstrumentBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
        return new MusicInstrumentBuilder(itemStack, castMeta(itemStack.getItemMeta(), MusicInstrumentMeta.class));
    }

    /**
     * Creates an {@code MusicInstrumentBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code MusicInstrumentBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static MusicInstrumentBuilder ofType(final Material material) throws IllegalArgumentException {
        return MusicInstrumentBuilder.of(itemOfMaterial(material));
    }

    /**
     * Gets the goat horn's instrument.
     *
     * @return the goat horn's instrument
     */
    public MusicInstrument instrument() {
        return this.itemMeta.getInstrument();
    }

    /**
     * Sets the goat horn's instrument.
     *
     * @param instrument the goat horn's instrument
     * @return the builder
     */
    public MusicInstrumentBuilder instrument(final MusicInstrument instrument) {
        this.itemMeta.setInstrument(instrument);
        return this;
    }

}
