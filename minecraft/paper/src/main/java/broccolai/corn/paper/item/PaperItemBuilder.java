package broccolai.corn.paper.item;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

@SuppressWarnings({"unused"})
public class PaperItemBuilder extends AbstractPaperItemBuilder<PaperItemBuilder, ItemMeta> {

    protected PaperItemBuilder(final @NonNull ItemStack itemStack, final @Nullable ItemMeta itemMeta) {
        super(itemStack, itemMeta != null
                ? itemMeta
                : Objects.requireNonNull(
                        Bukkit.getItemFactory().getItemMeta(itemStack.getType())
                ));
    }

    /**
     * Create a PaperItemBuilder.
     *
     * @param itemStack the ItemStack to base builder off of
     * @return instance of PaperItemBuilder
     */
    public static PaperItemBuilder of(final @NonNull ItemStack itemStack) {
        return new PaperItemBuilder(itemStack, itemStack.getItemMeta());
    }

    /**
     * Create a PaperItemBuilder.
     *
     * @param material the material to base builder off of
     * @return instance of PaperItemBuilder
     */
    public static PaperItemBuilder ofType(final @NonNull Material material) {
        return PaperItemBuilder.of(new ItemStack(material));
    }

}
