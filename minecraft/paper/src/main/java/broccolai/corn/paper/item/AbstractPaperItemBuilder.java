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

    private static final Component DISABLE_ITALICS = Component.empty().decoration(TextDecoration.ITALIC, false);

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
     * Sets the display name. Pass {@code null} to reset.
     * <p>
     * The component passed in is appended to an empty component decorated with
     * italicization set to false. This effectively bypasses the default,
     * italicized text formatting, resulting in the text being only the component
     * that is passed in.
     *
     * @param name the display name
     * @return the builder
     */
    public @NonNull B name(final @Nullable Component name) {
        if (name == null) {
            this.itemMeta.displayName(null);
            return (B) this;
        }

        // sidestep default formatting by creating a dummy component and appending the component to that
        this.itemMeta.displayName(DISABLE_ITALICS.append(name));

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
     * Sets the lore. Pass {@code null} to reset.
     * <p>
     * Each component passed in is appended to an empty component decorated with
     * italicization set to false. This effectively bypasses the default,
     * italicized text formatting, resulting in the text being only the component
     * that is passed in.
     *
     * @param lines the lines of the lore
     * @return the builder
     */
    public @NonNull B lore(final @Nullable List<Component> lines) {
        if (lines == null) {
            this.itemMeta.lore(null);
            return (B) this;
        }

        // sidestep default formatting by creating a dummy component and appending the component to that
        final @NonNull List<Component> toAdd = new ArrayList<>(lines);
        toAdd.replaceAll(DISABLE_ITALICS::append);

        this.itemMeta.lore(toAdd);
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
    public @NonNull B loreList(final @NonNull Component... lines) {
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
    public @NonNull B loreModifier(final @NonNull Consumer<@NonNull List<Component>> consumer) {
        final @NonNull List<Component> lore = Optional
                .ofNullable(this.itemMeta.lore())
                .orElse(new ArrayList<>());

        consumer.accept(lore);

        this.itemMeta.lore(lore);
        return (B) this;
    }

}
