package broccolai.corn.spigot.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings({"unchecked", "unused"})
public abstract class AbstractSpigotItemBuilder<T extends AbstractSpigotItemBuilder<T, M>, M extends ItemMeta>
        extends AbstractItemBuilder<T, M> {

    protected AbstractSpigotItemBuilder(final @NonNull ItemStack itemStack, final @NonNull M itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Set the name of the ItemStack.
     *
     * @param name the ItemStack's display name
     * @return the builder
     */
    public @NonNull T name(final @Nullable String name) {
        this.itemMeta.setDisplayName(name);
        return (T) this;
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param lines the lines to set the ItemStacks lore to
     * @return the builder
     */
    public @NonNull T lore(final @Nullable List<String> lines) {
        this.itemMeta.setLore(lines);
        return (T) this;
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param consumer the lines to set the ItemStacks lore to
     * @return the builder
     */
    public @NonNull T lore(final @NonNull Consumer<List<String>> consumer) {
        List<String> lore = this.itemMeta.hasLore() ? this.itemMeta.getLore() : new ArrayList<>();
        consumer.accept(lore);

        this.itemMeta.setLore(lore);
        return (T) this;
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param lines the lines to set the ItemStack's lore to
     * @return the builder
     */
    public @NonNull T lore(final @NonNull String... lines) {
        this.itemMeta.setLore(List.of(lines));
        return (T) this;
    }

}
