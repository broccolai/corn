package broccolai.corn.spigot.item;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

@SuppressWarnings({"unchecked", "unused"})
public abstract class AbstractItemBuilder<T extends AbstractItemBuilder<T, M>, M extends ItemMeta> {

    protected final @NonNull ItemStack itemStack;
    protected final @NonNull M itemMeta;

    protected final @NonNull Map<Enchantment, Integer> enchantmentMap;

    protected AbstractItemBuilder(final @NonNull ItemStack itemStack, final @Nullable ItemMeta itemMeta) {
        this.itemStack = itemStack.clone();
        this.itemMeta = itemMeta != null
                ? itemMeta
                : Objects.requireNonNull(
                        Bukkit.getItemFactory().getItemMeta(itemStack.getType())
                );
        this.enchantmentMap = new HashMap<>(this.itemMeta.getEnchants());
    }

    /**
     * Build ItemStack from set properties.
     *
     * @return the built ItemStack
     */
    public @NonNull ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        this.itemStack.addUnsafeEnchantments(this.enchantmentMap);

        return this.itemStack.clone();
    }

}
