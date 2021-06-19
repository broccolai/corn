package broccolai.corn.paper;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@SuppressWarnings({"unchecked", "unused"})
public class PaperItemBuilder extends AbstractPaperItemBuilder<PaperItemBuilder, ItemMeta> {

    protected PaperItemBuilder(final @NonNull ItemStack itemStack, final @Nullable ItemMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Create a PaperItemBuilder.
     *
     * @param itemStack the ItemStack to base builder off of
     * @return instance of PaperItemBuilder
     */
    public static PaperItemBuilder paper(final @NonNull ItemStack itemStack) {
        return new PaperItemBuilder(itemStack, itemStack.getItemMeta());
    }

    /**
     * Create a PaperItemBuilder.
     *
     * @param material the material to base builder off of
     * @return instance of PaperItemBuilder
     */
    public static PaperItemBuilder paper(final @NonNull Material material) {
        return PaperItemBuilder.paper(new ItemStack(material));
    }

}
