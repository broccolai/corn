package love.broccolai.corn.minecraft.item.special;

import java.util.List;
import love.broccolai.corn.minecraft.item.AbstractItemBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.KnowledgeBookMeta;
import org.jspecify.annotations.NullMarked;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link KnowledgeBookMeta}.
 */
@NullMarked
public final class KnowledgeBookBuilder extends AbstractItemBuilder<KnowledgeBookBuilder, KnowledgeBookMeta> {

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
    public static KnowledgeBookBuilder knowledgeBookBuilder(final ItemStack itemStack) throws IllegalArgumentException {
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
    public static KnowledgeBookBuilder knowledgeBookBuilder(final Material material) throws IllegalArgumentException {
        return knowledgeBookBuilder(itemOfMaterial(material));
    }

    /**
     * Creates a {@code KnowledgeBookBuilder} of type {@link Material#KNOWLEDGE_BOOK}. A convenience method.
     *
     * @return instance of {@code KnowledgeBookBuilder}
     */
    public static KnowledgeBookBuilder knowledgeBookBuilder() {
        return knowledgeBookBuilder(Material.KNOWLEDGE_BOOK);
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
