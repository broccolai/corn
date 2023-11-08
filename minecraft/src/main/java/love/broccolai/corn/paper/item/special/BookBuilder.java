package love.broccolai.corn.paper.item.special;

import love.broccolai.corn.paper.item.AbstractItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;


/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link BookMeta}.
 */
@SuppressWarnings("unused")
@NullMarked
public final class BookBuilder extends AbstractItemBuilder<BookBuilder, BookMeta> {

    private BookBuilder(final ItemStack itemStack, final BookMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code BookBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code BookBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static BookBuilder of(final ItemStack itemStack) throws IllegalArgumentException {
        return new BookBuilder(itemStack, castMeta(itemStack.getItemMeta(), BookMeta.class));
    }

    /**
     * Creates a {@code BookBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code BookBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static BookBuilder ofType(final Material material) throws IllegalArgumentException {
        return BookBuilder.of(getItem(material));
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
    public BookBuilder title(final @Nullable Component title) {
        this.itemMeta.title(title);
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
    public BookBuilder author(final @Nullable Component author) {
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
    public BookBuilder generation(final BookMeta.@Nullable Generation generation) {
        this.itemMeta.setGeneration(generation);
        return this;
    }

    /**
     * Gets the pages.
     *
     * @return the pages
     */
    public List<Component> pages() {
        return this.itemMeta.pages();
    }

    /**
     * Sets the pages. Pass {@code null} to reset.
     *
     * @param pages the pages
     * @return the builder
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public BookBuilder pages(final @Nullable List<Component> pages) {
        if (pages == null) {
            this.itemMeta.pages(List.of());
            return this;
        }

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
    public Component getPage(final int index) {
        return this.itemMeta.page(index);
    }

    /**
     * Sets the page at that index.
     *
     * @param index the index (1-indexed)
     * @param page  the page
     * @return the builder
     */
    public BookBuilder setPage(final int index, final Component page) {
        this.itemMeta.page(index, page);
        return this;
    }

    /**
     * Adds a page.
     *
     * @param page the page to add
     * @return the builder
     */
    public BookBuilder addPage(final Component... page) {
        this.itemMeta.addPages(page);
        return this;
    }

    /**
     * Removes a page.
     *
     * @param index the index of the page to remove (1-indexed)
     * @return the builder
     */
    public BookBuilder removePage(final int... index) {
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
