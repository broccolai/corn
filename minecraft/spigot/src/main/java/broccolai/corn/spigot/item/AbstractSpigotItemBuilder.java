package broccolai.corn.spigot.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Modifies {@link ItemStack}s using Spigot-specific methods.
 *
 * @param <B> the builder type
 * @param <M> the {@link ItemMeta} type
 */
@SuppressWarnings({"unchecked", "unused"})
public abstract class AbstractSpigotItemBuilder<B extends AbstractSpigotItemBuilder<B, M>, M extends ItemMeta>
        extends AbstractItemBuilder<B, M> {

    protected AbstractSpigotItemBuilder(final @NonNull ItemStack itemStack, final @NonNull M itemMeta) {
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
     * Sets the display name.
     *
     * @param name the display name
     * @return the builder
     */
    public @NonNull B name(final @Nullable String name) {
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
     * Sets the lore.
     *
     * @param lines the lines of the lore
     * @return the builder
     */
    public @NonNull B lore(final @Nullable List<String> lines) {
        this.itemMeta.setLore(lines);
        return (B) this;
    }

    /**
     * Sets the lore.
     *
     * @param lines the lines of the lore
     * @return the builder
     */
    public @NonNull B lore(final @NonNull String... lines) {
        this.itemMeta.setLore(List.of(lines));
        return (B) this;
    }

    /**
     * Sets the lore.
     *
     * @param consumer the lines of the lore
     * @return the builder
     */
    public @NonNull B lore(final @NonNull Consumer<List<String>> consumer) {
        final List<String> lore = this.itemMeta.hasLore()
                ? this.itemMeta.getLore()
                : new ArrayList<>();
        consumer.accept(lore);

        this.itemMeta.setLore(lore);
        return (B) this;
    }

}
