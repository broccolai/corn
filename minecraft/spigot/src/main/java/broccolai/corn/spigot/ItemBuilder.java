package broccolai.corn.spigot;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings({"unchecked", "unused"})
public class ItemBuilder extends AbstractItemBuilder<ItemBuilder, ItemMeta> {

    private ItemBuilder(final @NonNull ItemStack itemStack, final @Nullable ItemMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Create an ItemBuilder.
     *
     * @param itemStack the ItemStack to base builder off of
     * @return instance of ItemBuilder
     */
    public static ItemBuilder spigot(final @NonNull ItemStack itemStack) {
        return new ItemBuilder(itemStack, itemStack.getItemMeta());
    }

    /**
     * Create an ItemBuilder.
     *
     * @param material the material to base builder off of
     * @return instance of ItemBuilder
     */
    public static ItemBuilder spigot(final @NonNull Material material) {
        return ItemBuilder.spigot(new ItemStack(material));
    }

}
