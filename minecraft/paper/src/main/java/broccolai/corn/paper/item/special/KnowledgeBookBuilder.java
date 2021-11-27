package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

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
        return new KnowledgeBookBuilder(itemStack, castMeta(itemStack.getItemMeta(), KnowledgeBookMeta.class));
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
        return KnowledgeBookBuilder.of(getItem(material));
    }

    /**
     * Creates a {@code KnowledgeBookBuilder} of type {@link Material#KNOWLEDGE_BOOK}. A convenience method.
     *
     * @return instance of {@code KnowledgeBookBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull KnowledgeBookBuilder ofKnowledgeBook() throws IllegalArgumentException {
        return ofType(Material.KNOWLEDGE_BOOK);
    }

    /**
     * Gets the recipes.
     *
     * @return the recipes
     */
    public @NonNull List<NamespacedKey> recipes() {
        return this.itemMeta.getRecipes();
    }

    /**
     * Sets the recipes.
     *
     * @param recipes the recipes
     * @return the builder
     */
    public @NonNull KnowledgeBookBuilder recipes(final @NonNull List<@NonNull NamespacedKey> recipes) {
        this.itemMeta.setRecipes(recipes);
        return this;
    }

    /**
     * Adds a recipe.
     *
     * @param recipe the recipe to add
     * @return the builder
     */
    public @NonNull KnowledgeBookBuilder addRecipe(final @NonNull NamespacedKey... recipe) {
        this.itemMeta.addRecipe(recipe);
        return this;
    }

}
