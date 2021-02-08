package broccolai.corn.paper;

import broccolai.corn.spigot.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

import java.util.function.Consumer;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings({"unchecked", "unused"})
public class PaperItemBuilder<T extends PaperItemBuilder<T>> extends ItemBuilder<T> {

    protected PaperItemBuilder(final @NonNull ItemStack itemStack, final @Nullable ItemMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Create a PaperItemBuilder.
     *
     * @param itemStack the ItemStack to base builder off of
     * @return instance of PaperItemBuilder
     */
    public static PaperItemBuilder<?> paper(final @NonNull ItemStack itemStack) {
        return new PaperItemBuilder<>(itemStack, itemStack.getItemMeta());
    }

    /**
     * Create a PaperItemBuilder.
     *
     * @param material the material to base builder off of
     * @return instance of PaperItemBuilder
     */
    public static PaperItemBuilder<?> paper(final @NonNull Material material) {
        return PaperItemBuilder.paper(new ItemStack(material));
    }

    /**
     * Set the name of the ItemStack.
     *
     * @param baseComponent the ItemStack's display name
     * @return the builder
     */
    public @NonNull T name(final @NonNull BaseComponent[] baseComponent) {
        this.itemMeta.setDisplayNameComponent(baseComponent);
        return (T) this;
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param loreComponents the lines to set the ItemStacks lore to
     * @return the builder
     */
    public @NonNull T loreBaseComponents(final @NonNull List<BaseComponent[]> loreComponents) {
        this.itemMeta.setLoreComponents(loreComponents);
        return (T) this;
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param consumer the lines to set the ItemStacks lore to
     * @return the builder
     */
    public @NonNull T loreBaseComponents(final @NonNull Consumer<List<BaseComponent[]>> consumer) {
        List<BaseComponent[]> lore = this.itemMeta.hasLore()
                ? this.itemMeta.getLoreComponents()
                : new ArrayList<>();
        consumer.accept(lore);

        this.itemMeta.setLoreComponents(lore);
        return (T) this;
    }

}
