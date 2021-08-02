package broccolai.corn.paper.item;

import broccolai.corn.spigot.item.AbstractItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Modifies {@link ItemStack}s using Paper-specific methods.
 *
 * @param <B> the builder type
 * @param <M> the {@link ItemMeta} type
 */
@SuppressWarnings({"unchecked", "unused"})
public abstract class AbstractPaperItemBuilder<B extends AbstractPaperItemBuilder<B, M>, M extends ItemMeta>
        extends AbstractItemBuilder<B, M> {

    protected AbstractPaperItemBuilder(final @NonNull ItemStack itemStack, final @NonNull M itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Gets the display name.
     *
     * @return the display name
     */
    public @Nullable Component name() {
        return this.itemMeta.displayName();
    }

    /**
     * Sets the display name.
     *
     * @param name the display name
     * @return the builder
     */
    public @NonNull B name(final @Nullable Component name) {
        // If the value is null, pass that right through to the underlying displayName method.
        if (name == null) {
            this.itemMeta.displayName(null);
            return (B) this;
        }
        // Bukkit likes italicizing everything for no good reason, so to alleviate this we have to
        // first create a dummy component, set italics for that to false, and then append the name.
        this.itemMeta.displayName(
                Component.text("")
                        .decoration(TextDecoration.ITALIC, false)
                        .append(name)
        );
        return (B) this;
    }

    /**
     * Gets the lore.
     *
     * @return the lore
     */
    public @Nullable List<Component> lore() {
        return this.itemMeta.lore();
    }

    /**
     * Sets the lore.
     *
     * @param lines the lines of the lore
     * @return the builder
     */
    public @NonNull B lore(final @Nullable List<Component> lines) {
        // If the value is null, pass that right through to the underlying lore method.
        if (lines == null) {
            this.itemMeta.lore(null);
            return (B) this;
        }
        // Bukkit likes italicizing everything for no good reason, so to alleviate this we have to
        // first create a dummy component, set italics for that to false, and then append the name.
        final @NonNull List<Component> toAdd = new ArrayList<>(lines);
        toAdd.replaceAll((line) -> Component.text("")
                .decoration(TextDecoration.ITALIC, false)
                .append(line));

        this.itemMeta.lore(toAdd);
        return (B) this;
    }

    /**
     * Sets the lore.
     *
     * @param lines the lines of the lore
     * @return the builder
     */
    public @NonNull B lore(final @Nullable Component... lines) {
        this.lore(List.of(lines));
        return (B) this;
    }

    /**
     * Sets the lore.
     *
     * @param consumer the lines of the lore
     * @return the builder
     */
    public @NonNull B lore(final @NonNull Consumer<List<Component>> consumer) {
        final @NonNull List<@NonNull Component> lore = Optional
                .ofNullable(this.itemMeta.lore())
                .orElse(new ArrayList<>());

        consumer.accept(lore);

        this.itemMeta.lore(lore);
        return (B) this;
    }

}
