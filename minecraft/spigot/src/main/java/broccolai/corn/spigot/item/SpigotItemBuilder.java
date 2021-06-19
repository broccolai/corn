package broccolai.corn.spigot.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

@SuppressWarnings({"unused"})
public class SpigotItemBuilder extends AbstractSpigotItemBuilder<SpigotItemBuilder, ItemMeta> {

    private SpigotItemBuilder(final @NonNull ItemStack itemStack, final @Nullable ItemMeta itemMeta) {
        super(itemStack, itemMeta != null
                ? itemMeta
                : Objects.requireNonNull(
                        Bukkit.getItemFactory().getItemMeta(itemStack.getType())
                ));
    }

    /**
     * Create an ItemBuilder.
     *
     * @param itemStack the ItemStack to base builder off of
     * @return instance of ItemBuilder
     */
    public static SpigotItemBuilder spigot(final @NonNull ItemStack itemStack) {
        return new SpigotItemBuilder(itemStack, itemStack.getItemMeta());
    }

    /**
     * Create an ItemBuilder.
     *
     * @param material the material to base builder off of
     * @return instance of ItemBuilder
     */
    public static SpigotItemBuilder spigot(final @NonNull Material material) {
        return SpigotItemBuilder.spigot(new ItemStack(material));
    }

}
