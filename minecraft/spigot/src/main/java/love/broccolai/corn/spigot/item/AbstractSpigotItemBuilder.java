package love.broccolai.corn.spigot.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Modifies {@link ItemStack}s using Spigot-specific methods.
 *
 * @param <B> the builder type
 * @param <M> the {@link ItemMeta} type
 */
@SuppressWarnings({"unchecked", "unused"})
@NullMarked
public abstract class AbstractSpigotItemBuilder<B extends AbstractSpigotItemBuilder<B, M>, M extends ItemMeta>
        extends AbstractItemBuilder<B, M> {

    protected AbstractSpigotItemBuilder(final ItemStack itemStack, final M itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Gets the display name.
     *
     * @return the display name
     */
    public @Nullable String name() {
        return this.itemMeta.getDisplayName();
    }

    /**
     * Sets the display name. Pass {@code null} to reset.
     *
     * @param name the display name
     * @return the builder
     */
    public B name(final @Nullable String name) {
        this.itemMeta.setDisplayName(name);
        return (B) this;
    }

    /**
     * Gets the lore.
     *
     * @return the lore
     */
    public @Nullable List<String> lore() {
        return this.itemMeta.getLore();
    }

    /**
     * Sets the lore. Pass {@code null} to reset.
     *
     * @param lines the lines of the lore
     * @return the builder
     */
    public B lore(final @Nullable List<String> lines) {
        this.itemMeta.setLore(lines);
        return (B) this;
    }

    /**
     * A utility method that converts the provided {@code lines} into a
     * {@code List} using {@link List#of(Object[])}, and calls
     * {@link #lore(List)} using the new {@code List} as the argument.
     *
     * @param lines the lines of the lore
     * @return the builder
     */
    public B loreList(final String... lines) {
        return this.lore(List.of(lines));
    }

    /**
     * Directly modifies the lore with a {@link Consumer}.
     * If the item has no lore, an empty {@code List} will
     * be supplied to the {@code Consumer} instead.
     *
     * @param consumer the {@code Consumer} to modify the lore with
     * @return the builder
     */
    public B loreModifier(final Consumer<List<String>> consumer) {
        final List<String> lore = Optional
                .ofNullable(this.itemMeta.getLore())
                .orElse(new ArrayList<>());

        consumer.accept(lore);

        this.itemMeta.setLore(lore);
        return (B) this;
    }

}
