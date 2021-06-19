package broccolai.corn.paper.item;

import broccolai.corn.spigot.item.AbstractItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@SuppressWarnings({"unchecked", "unused"})
public abstract class AbstractPaperItemBuilder<B extends AbstractPaperItemBuilder<B, M>, M extends ItemMeta>
        extends AbstractItemBuilder<B, M> {

    protected AbstractPaperItemBuilder(final @NonNull ItemStack itemStack, final @NonNull M itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Set the name of the ItemStack.
     *
     * @param component the ItemStack's display name
     * @return the builder
     */
    public @NonNull B name(final @NonNull Component component) {
        this.itemMeta.displayName(component);
        return (B) this;
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param loreComponents the lines to set the ItemStacks lore to
     * @return the builder
     */
    public @NonNull B lore(final @NonNull List<Component> loreComponents) {
        this.itemMeta.lore(loreComponents);
        return (B) this;
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param consumer the lines to set the ItemStacks lore to
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

    /**
     * Set the lore of the ItemStack.
     *
     * @param loreComponents the lines to set the ItemStacks lore to
     * @return the builder
     */
    public @NonNull B lore(final @NonNull Component... loreComponents) {
        this.itemMeta.lore(List.of(loreComponents));
        return (B) this;
    }

}
