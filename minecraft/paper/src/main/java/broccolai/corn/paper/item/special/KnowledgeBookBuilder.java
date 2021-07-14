package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link KnowledgeBookMeta}.
 */
@SuppressWarnings("unused")
public final class KnowledgeBookBuilder extends AbstractPaperItemBuilder<KnowledgeBookBuilder, KnowledgeBookMeta> {

    private KnowledgeBookBuilder(final @NonNull ItemStack itemStack, final @NonNull KnowledgeBookMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code KnowledgeBookBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code KnowledgeBookBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull KnowledgeBookBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new KnowledgeBookBuilder(itemStack, AridUtil.castMeta(itemStack.getItemMeta(), KnowledgeBookMeta.class));
    }

    /**
     * Creates a {@code KnowledgeBookBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code KnowledgeBookBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull KnowledgeBookBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return KnowledgeBookBuilder.of(AridUtil.getItem(material));
    }

}
