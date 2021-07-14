package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link BookMeta}.
 */
@SuppressWarnings("unused")
public final class BookBuilder extends AbstractPaperItemBuilder<BookBuilder, BookMeta> {

    private BookBuilder(final @NonNull ItemStack itemStack, final @NonNull BookMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code BookBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code BookBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull BookBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new BookBuilder(itemStack, AridUtil.castMeta(itemStack.getItemMeta(), BookMeta.class));
    }

    /**
     * Creates a {@code BookBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code BookBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull BookBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return BookBuilder.of(AridUtil.getItem(material));
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public @Nullable String title() {
        return this.itemMeta.getTitle();
    }

    /**
     * Sets the title.
     *
     * @param title the title
     * @return the builder
     */
    public @NonNull BookBuilder title(final @NonNull String title) {
        this.itemMeta.setTitle(title);
        return this;
    }

    /**
     * Gets the {@code Generation}.
     *
     * @return the generation
     */
    public BookMeta.@Nullable Generation generation() {
        return this.itemMeta.getGeneration();
    }

    /**
     * Sets the {@code Generation}.
     *
     * @param generation the generation
     * @return the builder
     */
    public @NonNull BookBuilder generation(final BookMeta.@NonNull Generation generation) {
        this.itemMeta.setGeneration(generation);
        return this;
    }

    /**
     * Gets the author.
     *
     * @return the author
     */
    public @Nullable String author() {
        return this.itemMeta.getAuthor();
    }

    /**
     * Sets the author.
     *
     * @param author the author
     * @return the builder
     */
    public @NonNull BookBuilder author(final @NonNull String author) {
        this.itemMeta.setAuthor(author);
        return this;
    }

    /**
     * Gets the page count.
     *
     * @return the page count
     */
    public int pageCount() {
        return this.itemMeta.getPageCount();
    }

}
