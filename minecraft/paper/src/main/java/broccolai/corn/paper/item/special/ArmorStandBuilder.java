package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import com.destroystokyo.paper.inventory.meta.ArmorStandMeta;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link ArmorStandMeta}.
 */
@SuppressWarnings("unused")
public final class ArmorStandBuilder extends AbstractPaperItemBuilder<ArmorStandBuilder, ArmorStandMeta> {

    private ArmorStandBuilder(final @NonNull ItemStack itemStack, final @NonNull ArmorStandMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates an {@code ArmorStandBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code ArmorStandBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull ArmorStandBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new ArmorStandBuilder(itemStack, AridUtil.castMeta(itemStack.getItemMeta(), ArmorStandMeta.class));
    }

    /**
     * Creates an {@code ArmorStandBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code ArmorStandBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull ArmorStandBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return ArmorStandBuilder.of(AridUtil.getItem(material));
    }
    
    /**
     * Creates a {@code ArmorStandBuilder} of type {@link Material#ARMOR_STAND}. A convenience method.
     *
     * @return instance of {@code ArmorStandBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull ArmorStandBuilder ofArmorStand() throws IllegalArgumentException {
        return ofType(Material.ARMOR_STAND);
    }

}
