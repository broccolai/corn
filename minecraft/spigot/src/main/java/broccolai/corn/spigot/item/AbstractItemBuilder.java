package broccolai.corn.spigot.item;

import com.google.common.collect.Multimap;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Modifies {@link ItemStack}s using methods that both Paper and Spigot share.
 *
 * @param <B> the builder type
 * @param <M> the {@link ItemMeta} type
 */
@SuppressWarnings({"unchecked", "unused"})
public abstract class AbstractItemBuilder<B extends AbstractItemBuilder<B, M>, M extends ItemMeta> {

    /**
     * The {@code ItemStack} to modify during building. This will be cloned and
     * returned upon {@link #build()}.
     */
    protected final @NonNull ItemStack itemStack;
    /**
     * The {@code ItemMeta} to modify during building. This will be applied to
     * the {@link #itemStack} upon {@link #build()}.
     */
    protected final @NonNull M itemMeta;

    /**
     * @param itemStack the {@code ItemStack}
     * @param itemMeta  the {@code ItemMeta}
     */
    protected AbstractItemBuilder(final @NonNull ItemStack itemStack, final @NonNull M itemMeta) {
        this.itemStack = itemStack.clone();
        this.itemMeta = itemMeta;
    }

    /**
     * Attempts to cast {@code meta} to {@code expectedType},
     * and returns the result if successful.
     *
     * @param meta         the meta
     * @param expectedType the class of the expected type
     * @param <T>          the expected type
     * @return {@code meta} casted to {@code expectedType}
     * @throws IllegalArgumentException if {@code} meta is not the type of {@code expectedType}
     */
    protected static <T extends ItemMeta> T castMeta(final ItemMeta meta, final Class<T> expectedType)
            throws IllegalArgumentException {
        try {
            return expectedType.cast(meta);
        } catch (final ClassCastException e) {
            throw new IllegalArgumentException("The ItemMeta must be of type "
                    + expectedType.getSimpleName()
                    + " but received ItemMeta of type "
                    + meta.getClass().getSimpleName());
        }
    }

    /**
     * Returns an {@code ItemStack} of {@code material} if it is an item,
     * else throws an exception.
     *
     * @param material the material
     * @return an {@code ItemStack} of type {@code material}
     * @throws IllegalArgumentException if {@code material} is not an item
     */
    protected static @NonNull ItemStack getItem(final @NonNull Material material) throws IllegalArgumentException {
        if (!material.isItem()) {
            throw new IllegalArgumentException("The Material must be an obtainable item.");
        }
        return new ItemStack(material);
    }

    /**
     * Gets the {@code Material}.
     *
     * @return the {@code Material}
     */
    public @NonNull Material material() {
        return this.itemStack.getType();
    }

    /**
     * Sets the {@code Material}.
     *
     * @param material the {@code Material}
     * @return the builder
     */
    public @NonNull B material(final @NonNull Material material) {
        this.itemStack.setType(material);
        return (B) this;
    }

    /**
     * Gets the quantity.
     *
     * @return the quantity
     */
    public int amount() {
        return this.itemStack.getAmount();
    }

    /**
     * Sets the quantity.
     *
     * @param amount the quantity
     * @return the builder
     */
    public @NonNull B amount(final int amount) {
        this.itemStack.setAmount(amount);
        return (B) this;
    }

    /**
     * Gets data from the {@code ItemStack}'s {@link org.bukkit.persistence.PersistentDataContainer}.
     *
     * @param key  the {@code NamespacedKey} to use
     * @param type the {@code PersistentDataType to use}
     * @param <T>  the primary object type of the data
     * @param <Z>  the retrieve object type of the data
     * @return the data
     */
    public <T, Z> @Nullable Z getData(
            final @NonNull NamespacedKey key,
            final @NonNull PersistentDataType<T, Z> type
    ) {
        return this.itemMeta.getPersistentDataContainer().get(key, type);
    }

    /**
     * Adds data to the {@code ItemStack}'s {@link org.bukkit.persistence.PersistentDataContainer}.
     *
     * @param key    the {@code NamespacedKey} to use
     * @param type   the {@code PersistentDataType} to use
     * @param object the data to set
     * @param <T>    the primary object type of the data
     * @param <Z>    the retrieve object type of the data
     * @return the builder
     */
    public <T, Z> @NonNull B setData(
            final @NonNull NamespacedKey key,
            final @NonNull PersistentDataType<T, Z> type,
            final @NonNull Z object
    ) {
        this.itemMeta.getPersistentDataContainer().set(key, type, object);
        return (B) this;
    }

    /**
     * Removes data from the {@code ItemStack}'s {@link org.bukkit.persistence.PersistentDataContainer}.
     *
     * @param key the {@code NamespacedKey} to use
     * @return the builder
     */
    public @NonNull B removeData(
            final @NonNull NamespacedKey key
    ) {
        this.itemMeta.getPersistentDataContainer().remove(key);
        return (B) this;
    }

    /**
     * Gets the {@code ItemFlag}s.
     *
     * @return the {@code ItemFlag}s
     */
    public @NonNull Set<ItemFlag> flags() {
        return this.itemMeta.getItemFlags();
    }

    /**
     * Add an {@code ItemFlag}.
     *
     * @param flag the {@code ItemFlag} to add
     * @return the builder
     */
    public @NonNull B addFlag(final @NonNull ItemFlag... flag) {
        this.itemMeta.addItemFlags(flag);
        return (B) this;
    }

    /**
     * Remove an {@code ItemFlag}.
     *
     * @param flag the {@code ItemFlag} to remove
     * @return the builder
     */
    public @NonNull B removeFlag(final @NonNull ItemFlag... flag) {
        this.itemMeta.removeItemFlags(flag);
        return (B) this;
    }

    /**
     * Gets the {@code Enchantment}s.
     *
     * @return the {@code Enchantment}s
     */
    public @NonNull Map<Enchantment, Integer> enchants() {
        return new HashMap<>(this.itemStack.getEnchantments());
    }

    /**
     * Adds an {@code Enchantment}.
     *
     * @param enchantment the {@code Enchantment} to add
     * @param level       the level of the {@code Enchantment}
     * @return the builder
     */
    public @NonNull B addEnchant(final @NonNull Enchantment enchantment, final int level) {
        this.itemMeta.addEnchant(enchantment, level, true);
        return (B) this;
    }

    /**
     * Removes an {@code Enchantment}.
     *
     * @param enchantment the {@code Enchantment} to remove
     * @return the builder
     */
    public @NonNull B removeEnchant(final @NonNull Enchantment... enchantment) {
        for (final Enchantment item : enchantment) {
            this.itemMeta.removeEnchant(item);
        }
        return (B) this;
    }

    /**
     * Sets whether the {@code ItemStack} is unbreakable.
     *
     * @param unbreakable whether the {@code ItemStack} is unbreakable
     * @return the builder
     */
    public @NonNull B unbreakable(final boolean unbreakable) {
        itemMeta.setUnbreakable(unbreakable);
        return (B) this;
    }

    /**
     * Get the max stack size.
     *
     * @return the max stack size
     */
    public int maxStackSize() {
        return this.itemStack.getMaxStackSize();
    }

    /**
     * Gets the custom model data.
     *
     * @return the custom model data
     */
    public @Nullable Integer customModelData() {
        // we use the wrapper with null signifying absent for api consistency
        if (!this.itemMeta.hasCustomModelData()) {
            return null;
        }
        return this.itemMeta.getCustomModelData();
    }

    /**
     * Sets the custom model data. Pass {@code null} to reset.
     *
     * @param customModelData the custom model data
     * @return the builder
     */
    public @NonNull B customModelData(final @Nullable Integer customModelData) {
        this.itemMeta.setCustomModelData(customModelData);
        return (B) this;
    }

    /**
     * Gets the localized name.
     *
     * @return the localized name
     */
    public @NonNull String localizedName() {
        return this.itemMeta.getLocalizedName();
    }

    /**
     * Sets the localized name. Pass {@code null} to reset.
     *
     * @param localizedName the localized name
     * @return the builder
     */
    public @NonNull B localizedName(final @Nullable String localizedName) {
        this.itemMeta.setLocalizedName(localizedName);
        return (B) this;
    }

    /**
     * Gets the {@code AttributeModifier}s.
     *
     * @return the {@code AttributeModifier}s
     */
    public @Nullable Multimap<Attribute, AttributeModifier> attributeModifiers() {
        return this.itemMeta.getAttributeModifiers();
    }

    /**
     * Sets the {@code AttributeModifier}s. Pass {@code null} to reset.
     *
     * @param attributeModifiers the {@code AttributeModifier}s
     * @return the builder
     */
    public @NonNull B attributeModifiers(final @Nullable Multimap<Attribute, AttributeModifier> attributeModifiers) {
        this.itemMeta.setAttributeModifiers(attributeModifiers);
        return (B) this;
    }

    /**
     * Adds an {@code AttributeModifier}.
     *
     * @param attribute         the attribute to modify
     * @param attributeModifier the {@code AttributeModifier} to add
     * @return the builder
     */
    public @NonNull B addAttributeModifier(
            final @NonNull Attribute attribute,
            final @NonNull AttributeModifier attributeModifier
    ) {
        this.itemMeta.addAttributeModifier(attribute, attributeModifier);
        return (B) this;
    }

    /**
     * Removes an {@code AttributeModifier}.
     *
     * @param attribute         the attribute to modify
     * @param attributeModifier the {@code AttributeModifier} to remove
     * @return the builder
     */
    public @NonNull B removeAttributeModifier(
            final @NonNull Attribute attribute,
            final @NonNull AttributeModifier attributeModifier
    ) {
        this.itemMeta.removeAttributeModifier(attribute, attributeModifier);
        return (B) this;
    }

    /**
     * Removes all {@code AttributeModifier}s for the given {@code attribute}.
     *
     * @param attribute the {@code Attribute}
     * @return the builder
     */
    public @NonNull B removeAttributeModifier(final @NonNull Attribute... attribute) {
        for (final @NonNull Attribute item : attribute) {
            this.itemMeta.removeAttributeModifier(item);
        }
        return (B) this;
    }

    /**
     * Builds the {@code ItemStack} from the set properties.
     *
     * @return the built {@code ItemStack}
     */
    public @NonNull ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack.clone();
    }

}
