package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.common.value.qual.IntRange;

import java.util.List;


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
    public @Nullable Component title() {
        return this.itemMeta.title();
    }

    /**
     * Sets the title. Pass {@code null} to reset.
     *
     * @param title the title
     * @return the builder
     */
    @SuppressWarnings({"ResultOfMethodCallIgnored"})
    public @NonNull BookBuilder title(final @Nullable Component title) {
        this.itemMeta.title(null);
        return this;
    }

    /**
     * Gets the author.
     *
     * @return the author
     */
    public @Nullable Component author() {
        return this.itemMeta.author();
    }

    /**
     * Sets the author. Pass {@code null} to reset.
     *
     * @param author the author
     * @return the builder
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public @NonNull BookBuilder author(final @Nullable Component author) {
        this.itemMeta.author(author);
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
     * Sets the {@code Generation}. Pass {@code null} to reset.
     *
     * @param generation the generation
     * @return the builder
     */
    public @NonNull BookBuilder generation(final BookMeta.@Nullable Generation generation) {
        this.itemMeta.setGeneration(generation);
        return this;
    }

    /**
     * Gets the pages.
     *
     * @return the pages
     */
    public @NonNull List<@NonNull Component> pages() {
        return this.itemMeta.pages();
    }

    /**
     * Sets the pages. Pass {@code List.of()} to reset.
     *
     * @param pages the pages
     * @return the builder
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public @NonNull BookBuilder pages(final @NonNull List<@NonNull Component> pages) {
        // in Paper's implementation, this will actually mutate the internal BookMeta instance
        // you can see the discussion I had about this on the Paper discord
        // https://canary.discord.com/channels/289587909051416579/555462289851940864/872168673283043328
        // https://canary.discord.com/channels/289587909051416579/555462289851940864/872172549075783690
        // once the Javadocs are fixed on Paper's end, these comments may be removed
        this.itemMeta.pages(pages);
        return this;
    }

    /**
     * Gets the page at that index.
     *
     * @param index the index (1-indexed)
     * @return the page
     */
    public @NonNull Component getPage(final @IntRange(from = 1) int index) {
        return this.itemMeta.page(index);
    }

    /**
     * Sets the page at that index.
     *
     * @param index the index (1-indexed)
     * @param page  the page
     * @return the builder
     */
    public @NonNull BookBuilder setPage(final @IntRange(from = 1) int index, final @NonNull Component page) {
        this.itemMeta.page(index, page);
        return this;
    }

    /**
     * Adds a page.
     *
     * @param page the page to add
     * @return the builder
     */
    public @NonNull BookBuilder addPage(final @NonNull Component... page) {
        this.itemMeta.addPages(page);
        return this;
    }

    /**
     * Removes a page.
     *
     * @param index the index of the page to remove (1-indexed)
     * @return the builder
     */
    public @NonNull BookBuilder removePage(final @IntRange(from = 1) int... index) {
        for (final int i : index) {
            this.itemMeta.page(i, Component.empty());
        }
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
