package broccolai.corn.paper.item;

import broccolai.corn.spigot.item.AbstractItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
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
     * Gets the display name of the {@code ItemStack}.
     *
     * @return the display name
     */
    public @Nullable Component name() {
        return this.itemMeta.displayName();
    }

    /**
     * Sets the display name of the {@code ItemStack}.
     *
     * @param name the display name
     * @return the builder
     */
    public @NonNull B name(final @Nullable Component name) {
        this.itemMeta.displayName(name);
        return (B) this;
    }

    /**
     * Gets the lore of the {@code ItemStack}.
     *
     * @return the lore
     */
    public @Nullable List<Component> lore() {
        return this.itemMeta.lore();
    }

    /**
     * Sets the lore of the {@code ItemStack}.
     *
     * @param lines the lines of the lore
     * @return the builder
     */
    public @NonNull B lore(final @Nullable List<Component> lines) {
        this.itemMeta.lore(lines);
        return (B) this;
    }

    /**
     * Sets the lore of the {@code ItemStack}.
     *
     * @param lines the lines of the lore
     * @return the builder
     */
    public @NonNull B lore(final @NonNull Component... lines) {
        this.itemMeta.lore(List.of(lines));
        return (B) this;
    }

    /**
     * Sets the lore of the {@code ItemStack}.
     *
     * @param consumer the lines of the lore
     * @return the builder
     */
    public @NonNull B lore(final @NonNull Consumer<List<Component>> consumer) {
        List<Component> lore = this.itemMeta.hasLore()
                ? this.itemMeta.lore()
                : new ArrayList<>();
        consumer.accept(lore);

        this.itemMeta.lore(lore);
        return (B) this;
    }

}
