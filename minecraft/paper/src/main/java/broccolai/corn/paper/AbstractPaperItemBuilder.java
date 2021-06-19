package broccolai.corn.paper;

import broccolai.corn.spigot.AbstractItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings({"unchecked", "unused"})
public abstract class AbstractPaperItemBuilder<T extends AbstractPaperItemBuilder<T, M>, M extends ItemMeta>
        extends AbstractItemBuilder<T, M> {

    protected AbstractPaperItemBuilder(final @NonNull ItemStack itemStack, final @Nullable ItemMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Set the name of the ItemStack.
     *
     * @param component the ItemStack's display name
     * @return the builder
     */
    public @NonNull T name(final @NonNull Component component) {
        this.itemMeta.displayName(component);
        return (T) this;
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param loreComponents the lines to set the ItemStacks lore to
     * @return the builder
     */
    public @NonNull T loreComponents(final @NonNull List<Component> loreComponents) {
        this.itemMeta.lore(loreComponents);
        return (T) this;
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param consumer the lines to set the ItemStacks lore to
     * @return the builder
     */
    public @NonNull T loreComponents(final @NonNull Consumer<List<Component>> consumer) {
        List<Component> lore = this.itemMeta.hasLore()
                ? this.itemMeta.lore()
                : new ArrayList<>();
        consumer.accept(lore);

        this.itemMeta.lore(lore);
        return (T) this;
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param loreComponents the lines to set the ItemStacks lore to
     * @return the builder
     */
    public @NonNull T loreComponents(final @NonNull Component... loreComponents) {
        this.itemMeta.lore(List.of(loreComponents));
        return (T) this;
    }

}
