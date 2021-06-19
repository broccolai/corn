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

    private PaperItemBuilder(final @NonNull ItemStack itemStack, final @Nullable ItemMeta itemMeta) {
        super(itemStack, itemMeta != null
                ? itemMeta
                : Objects.requireNonNull(
                        Bukkit.getItemFactory().getItemMeta(itemStack.getType())
                ));
    }

    /**
     * Create a {@code PaperItemBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code PaperItemBuilder}
     */
    public static @NonNull PaperItemBuilder of(final @NonNull ItemStack itemStack) {
        return new PaperItemBuilder(itemStack, itemStack.getItemMeta());
    }

    /**
     * Create a {@code PaperItemBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code PaperItemBuilder}
     */
    public static @NonNull PaperItemBuilder ofType(final @NonNull Material material) {
        return PaperItemBuilder.of(new ItemStack(material));
    }

}
