package love.broccolai.corn.paper.item.special;

import love.broccolai.corn.paper.item.AbstractPaperItemBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.jspecify.annotations.NullMarked;

import java.util.List;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link KnowledgeBookMeta}.
 */
@SuppressWarnings("unused")
@NullMarked
public final class KnowledgeBookBuilder extends AbstractPaperItemBuilder<KnowledgeBookBuilder, KnowledgeBookMeta> {

    private KnowledgeBookBuilder(final ItemStack itemStack, final KnowledgeBookMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code KnowledgeBookBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code KnowledgeBookBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static KnowledgeBookBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
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
    public static KnowledgeBookBuilder ofType(final Material material) throws IllegalArgumentException {
        return KnowledgeBookBuilder.of(getItem(material));
    }

    /**
     * Creates a {@code KnowledgeBookBuilder} of type {@link Material#KNOWLEDGE_BOOK}. A convenience method.
     *
     * @return instance of {@code KnowledgeBookBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static KnowledgeBookBuilder ofKnowledgeBook() throws IllegalArgumentException {
        return ofType(Material.KNOWLEDGE_BOOK);
    }

    /**
     * Gets the recipes.
     *
     * @return the recipes
     */
    public List<NamespacedKey> recipes() {
        return this.itemMeta.getRecipes();
    }

    /**
     * Sets the recipes.
     *
     * @param recipes the recipes
     * @return the builder
     */
    public KnowledgeBookBuilder recipes(final List<NamespacedKey> recipes) {
        this.itemMeta.setRecipes(recipes);
        return this;
    }

    /**
     * Adds a recipe.
     *
     * @param recipe the recipe to add
     * @return the builder
     */
    public KnowledgeBookBuilder addRecipe(final NamespacedKey... recipe) {
        this.itemMeta.addRecipe(recipe);
        return this;
    }

}
