package broccolai.corn.adventure;

import broccolai.corn.core.Lists;
import broccolai.corn.paper.PaperItemBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public class AdventureItemBuilder<T extends AdventureItemBuilder<T>> extends PaperItemBuilder<T> {

    private static final BungeeComponentSerializer BUNGEE = BungeeComponentSerializer.get();

    protected AdventureItemBuilder(
            final @NonNull ItemStack itemStack,
            final @Nullable ItemMeta itemMeta
    ) {
        super(itemStack, itemMeta);
    }

    /**
     * Create a AdventureItemBuilder.
     *
     * @param itemStack the ItemStack to base builder off of
     * @return instance of AdventureItemBuilder
     */
    public static AdventureItemBuilder<?> adventure(final @NonNull ItemStack itemStack) {
        return new AdventureItemBuilder<>(itemStack, itemStack.getItemMeta());
    }

    /**
     * Create a AdventureItemBuilder.
     *
     * @param material the material to base builder off of
     * @return instance of AdventureItemBuilder
     */
    public static AdventureItemBuilder<?> adventure(final @NonNull Material material) {
        return AdventureItemBuilder.adventure(new ItemStack(material));
    }

    /**
     * Set the name of the ItemStack.
     *
     * @param component the ItemStack's display name
     * @return the builder
     */
    public @NonNull T name(final @NonNull Component component) {
        return this.name(BUNGEE.serialize(component));
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param loreComponents the lines to set the ItemStacks lore to
     * @return the builder
     */
    public @NonNull T loreComponents(final @NonNull List<Component> loreComponents) {
        List<BaseComponent[]> loreBaseComponents = Lists.map(loreComponents, BUNGEE::serialize);
        return this.loreBaseComponents(loreBaseComponents);
    }

    /**
     * Set the lore of the ItemStack.
     *
     * @param consumer the lines to set the ItemStacks lore to
     * @return the builder
     */
    public @NonNull T loreComponents(final @NonNull Consumer<List<Component>> consumer) {
        List<BaseComponent[]> loreBaseComponents = this.itemMeta.hasLore()
                ? this.itemMeta.getLoreComponents()
                : new ArrayList<>();
        assert loreBaseComponents != null;

        List<Component> lore = Lists.map(loreBaseComponents, BUNGEE::deserialize);
        consumer.accept(lore);

        loreBaseComponents = Lists.map(lore, BUNGEE::serialize);
        return this.loreBaseComponents(loreBaseComponents);
    }
}
