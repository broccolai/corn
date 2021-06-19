package broccolai.corn.spigot.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings({"unchecked", "unused"})
public abstract class AbstractSpigotItemBuilder<B extends AbstractSpigotItemBuilder<B, M>, M extends ItemMeta>
        extends AbstractItemBuilder<B, M> {

    protected AbstractSpigotItemBuilder(final @NonNull ItemStack itemStack, final @NonNull M itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Set the name of the ItemStack.
     *
     * @param name the ItemStack's display name
     * @return the builder
     */
    public @NonNull B name(final @Nullable String name) {
        this.itemMeta.setDisplayName(name);
        return (B) this;
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param lines the lines to set the ItemStacks lore to
     * @return the builder
     */
    public @NonNull B lore(final @Nullable List<String> lines) {
        this.itemMeta.setLore(lines);
        return (B) this;
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param consumer the lines to set the ItemStacks lore to
     * @return the builder
     */
    public @NonNull B lore(final @NonNull Consumer<List<String>> consumer) {
        List<String> lore = this.itemMeta.hasLore() ? this.itemMeta.getLore() : new ArrayList<>();
        consumer.accept(lore);

        this.itemMeta.setLore(lore);
        return (B) this;
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param lines the lines to set the ItemStack's lore to
     * @return the builder
     */
    public @NonNull B lore(final @NonNull String... lines) {
        this.itemMeta.setLore(List.of(lines));
        return (B) this;
    }

}
