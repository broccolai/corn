package broccolai.corn.spigot.item;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unused"})
public abstract class AbstractItemBuilder<T extends AbstractItemBuilder<T, M>, M extends ItemMeta> {

    protected final @NonNull ItemStack itemStack;
    protected final @NonNull M itemMeta;

    protected final @NonNull Map<Enchantment, Integer> enchantmentMap;

    protected AbstractItemBuilder(final @NonNull ItemStack itemStack, final @NonNull M itemMeta) {
        this.itemStack = itemStack.clone();
        this.itemMeta = itemMeta;
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
