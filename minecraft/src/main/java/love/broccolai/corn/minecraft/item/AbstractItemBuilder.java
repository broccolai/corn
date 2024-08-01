package love.broccolai.corn.minecraft.item;

import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * Modifies {@link ItemStack}s.
 *
 * @param <B> the builder type
 * @param <M> the {@link ItemMeta} type
 */
@NullMarked
@SuppressWarnings({"unchecked", "unused"})
public abstract class AbstractItemBuilder<B extends AbstractItemBuilder<B, M>, M extends ItemMeta> {

    private static final int FAKE_ENCHANT_LEVEL = 102; // f in ASCII.

    private static final Component DISABLE_ITALICS = Component.empty().decoration(TextDecoration.ITALIC, false);

    /**
     * The {@code ItemStack} to modify during building.
     *
     * <p>This will be cloned and returned upon {@link #build()}.</p>
     */
    protected ItemStack itemStack;
    /**
     * The {@code ItemMeta} to modify during building.
     *
     * <p>This will be applied to the {@link #itemStack} upon {@link #build()}.</p>
     */
    protected final M itemMeta;

    /**
     * Constructs AbstractItemBuilder with an {@code ItemStack} and its {@code ItemMeta}.
     *
     * @param itemStack the {@code ItemStack}
     * @param itemMeta  the {@code ItemMeta}
     */
    protected AbstractItemBuilder(final ItemStack itemStack, final M itemMeta) {
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
    protected static ItemStack itemOfMaterial(final Material material) throws IllegalArgumentException {
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
    public Material material() {
        return this.itemStack.getType();
    }

    /**
     * Sets the {@code Material}.
     *
     * @param material the {@code Material}
     * @return the builder
     */
    public B material(final Material material) {
        final boolean fakeEnch = this.fakeEnchant();
        if (fakeEnch) {
            this.fakeEnchant(false);
        }
        this.itemStack = this.itemStack.withType(material);
        if (fakeEnch) {
            this.fakeEnchant(true);
        }
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
    public B amount(final int amount) {
        this.itemStack.setAmount(amount);
        return (B) this;
    }

    /**
     * Gets the display name.
     *
     * @return the display name
     */
    public @Nullable Component name() {
        return this.itemMeta.displayName();
    }

    /**
     * Sets the display name. Pass {@code null} to reset.
     *
     * <p>The component passed in is appended to an empty component decorated with
     * italicization set to false. This effectively bypasses the default,
     * italicized text formatting, resulting in the text being only the component
     * that is passed in.</p>
     *
     * @param name the display name
     * @return the builder
     */
    public B name(final @Nullable Component name) {
        if (name == null) {
            this.itemMeta.displayName(null);
            return (B) this;
        }

        // sidestep default formatting by creating a dummy component and appending the component to that
        this.itemMeta.displayName(DISABLE_ITALICS.append(name));

        return (B) this;
    }

    /**
     * Gets whether hide_tooltip is set. An item with this set will not show any tooltip whatsoever.
     *
     * @return whether hide_tooltip is set
     */
    public boolean hideTooltip() {
        return this.itemMeta.isHideTooltip();
    }

    /**
     * Gets whether hide_tooltip is set. An item with this set will not show any tooltip whatsoever.
     *
     * @param hideTooltip whether hide_tooltip is set
     * @return the builder
     */
    public B hideTooltip(final boolean hideTooltip) {
        this.itemMeta.setHideTooltip(hideTooltip);
        return (B) this;
    }

    /**
     * Gets the lore.
     *
     * @return the lore
     */
    public @Nullable List<Component> lore() {
        if (!this.itemMeta.hasLore()) {
            return null;
        }
        return this.itemMeta.lore();
    }

    /**
     * Sets the lore. Pass {@code null} to reset.
     *
     * <p>Each component passed in is appended to an empty component decorated with
     * italicization set to false. This effectively bypasses the default,
     * italicized text formatting, resulting in the text being only the component
     * that is passed in.</p>
     *
     * @param lines the lines of the lore
     * @return the builder
     */
    public B lore(final @Nullable List<Component> lines) {
        if (lines == null) {
            this.itemMeta.lore(null);
            return (B) this;
        }

        // sidestep default formatting by creating a dummy component and appending the component to that
        final List<Component> toAdd = new ArrayList<>(lines);
        toAdd.replaceAll(DISABLE_ITALICS::append);

        this.itemMeta.lore(toAdd);
        return (B) this;
    }

    /**
     * A utility method that converts the provided {@code lines} into a
     * {@code List} using {@link List#of(Object[])}, and calls
     * {@link #lore(List)} using the new {@code List} as the argument.
     *
     * @param lines the lines of the lore
     * @return the builder
     */
    public B loreList(final Component... lines) {
        return this.lore(List.of(lines));
    }

    /**
     * Directly modifies the lore with a {@link Consumer}.
     * If the item has no lore, an empty {@code List} will
     * be supplied to the {@code Consumer} instead.
     *
     * @param consumer the {@code Consumer} to modify the lore with
     * @return the builder
     */
    public B loreModifier(final Consumer<List<Component>> consumer) {
        final List<Component> lore = Optional
            .ofNullable(this.itemMeta.lore())
            .orElse(new ArrayList<>());

        consumer.accept(lore);

        this.itemMeta.lore(lore);
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
    public <T, Z> @Nullable Z data(
        final NamespacedKey key,
        final PersistentDataType<T, Z> type
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
    public <T, Z> B data(
        final NamespacedKey key,
        final PersistentDataType<T, Z> type,
        final Z object
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
    public B removeData(
        final NamespacedKey key
    ) {
        this.itemMeta.getPersistentDataContainer().remove(key);
        return (B) this;
    }

    /**
     * Gets the {@code ItemFlag}s.
     *
     * @return the {@code ItemFlag}s
     */
    public Set<ItemFlag> flags() {
        return this.itemMeta.getItemFlags();
    }

    /**
     * Sets the {@code ItemFlag}s. Pass {@code null} to reset.
     *
     * @param flags the {@code ItemFlag}s
     * @return the builder
     */
    public B flags(final @Nullable List<ItemFlag> flags) {
        this.clearFlags();
        if (flags != null) {
            this.itemMeta.addItemFlags(flags.toArray(new ItemFlag[0]));
        }
        return (B) this;
    }

    private void clearFlags() {
        this.itemMeta.removeItemFlags(this.itemMeta.getItemFlags().toArray(new ItemFlag[0]));
    }

    /**
     * Add an {@code ItemFlag}.
     *
     * @param flag the {@code ItemFlag} to add
     * @return the builder
     */
    public B addFlag(final ItemFlag... flag) {
        this.itemMeta.addItemFlags(flag);
        return (B) this;
    }

    /**
     * Remove an {@code ItemFlag}.
     *
     * @param flag the {@code ItemFlag} to remove
     * @return the builder
     */
    public B removeFlag(final ItemFlag... flag) {
        this.itemMeta.removeItemFlags(flag);
        return (B) this;
    }

    /**
     * Gets the {@code Enchantment}s.
     *
     * @return the {@code Enchantment}s
     */
    public Map<Enchantment, Integer> enchants() {
        return new HashMap<>(this.itemStack.getEnchantments());
    }

    /**
     * Sets the {@code Enchantment}s. Pass {@code null} to reset.
     *
     * @param enchants the {@code Enchantment}s
     * @return the builder
     */
    public B enchants(final @Nullable Map<Enchantment, Integer> enchants) {
        this.clearEnchants();
        if (enchants != null) {
            this.itemStack.addEnchantments(enchants);
        }
        return (B) this;
    }

    private void clearEnchants() {
        for (final Enchantment enchantment : this.itemMeta.getEnchants().keySet()) {
            this.itemMeta.removeEnchant(enchantment);
        }
    }

    /**
     * Adds an {@code Enchantment}.
     *
     * @param enchantment the {@code Enchantment} to add
     * @param level       the level of the {@code Enchantment}
     * @return the builder
     */
    public B addEnchant(final Enchantment enchantment, final int level) {
        this.itemMeta.addEnchant(enchantment, level, true);
        return (B) this;
    }

    /**
     * Removes an {@code Enchantment}.
     *
     * @param enchantment the {@code Enchantment} to remove
     * @return the builder
     */
    public B removeEnchant(final Enchantment... enchantment) {
        for (final Enchantment item : enchantment) {
            this.itemMeta.removeEnchant(item);
        }
        return (B) this;
    }

    /**
     * Sets whether the {@code ItemStack} has a fake enchantment glint.
     *
     * <p>This works by enchanting the {@code ItemStack} with an incompatible
     * enchantment and adding the {@link ItemFlag#HIDE_ENCHANTS} flag.</p>
     *
     * @param fakeEnchant whether the {@code ItemStack} has a fake enchant glint
     * @return the builder
     */
    public B fakeEnchant(final boolean fakeEnchant) {
        if (fakeEnchant && !this.fakeEnchant()) {
            this.addEnchant(this.incompatibleEnchantment(), FAKE_ENCHANT_LEVEL);
            this.addFlag(ItemFlag.HIDE_ENCHANTS);
        } else if (!fakeEnchant && this.fakeEnchant()) {
            this.removeEnchant(this.incompatibleEnchantment());
            this.removeFlag(ItemFlag.HIDE_ENCHANTS);
        }
        return (B) this;
    }

    /**
     * Gets whether the {@code ItemStack} has a fake enchantment glint.
     *
     * @return whether the {@code ItemStack} has a fake enchantment glint
     */
    public boolean fakeEnchant() {
        return this.enchants().containsKey(this.incompatibleEnchantment())
            && this.enchants().get(this.incompatibleEnchantment()) == FAKE_ENCHANT_LEVEL
            && this.flags().contains(ItemFlag.HIDE_ENCHANTS);
    }

    /**
     * Checks whether the given enchantment conflicts with any enchantments
     * in this item.
     *
     * @param enchant the enchantment to test
     * @return whether the enchantment conflicts
     */
    public boolean hasConflictingEnchant(final Enchantment enchant) {
        return this.itemMeta.hasConflictingEnchant(enchant);
    }

    private Enchantment incompatibleEnchantment() {
        if (!Enchantment.LURE.canEnchantItem(this.itemStack)) {
            return Enchantment.RIPTIDE; // exclusive to tridents.
        } else {
            return Enchantment.LURE; // exclusive to fishing rods.
        }
    }

    /**
     * Sets whether the {@code ItemStack} is unbreakable.
     *
     * @param unbreakable whether the {@code ItemStack} is unbreakable
     * @return the builder
     */
    public B unbreakable(final boolean unbreakable) {
        this.itemMeta.setUnbreakable(unbreakable);
        return (B) this;
    }

    /**
     * Gets whether the {@code ItemStack} is unbreakable.
     *
     * @return whether the {@code ItemStack} is unbreakable
     */
    public boolean unbreakable() {
        return this.itemMeta.isUnbreakable();
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
    public B customModelData(final @Nullable Integer customModelData) {
        this.itemMeta.setCustomModelData(customModelData);
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
    public B attributeModifiers(final @Nullable Multimap<Attribute, AttributeModifier> attributeModifiers) {
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
    public B addAttributeModifier(
        final Attribute attribute,
        final AttributeModifier attributeModifier
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
    public B removeAttributeModifier(
        final Attribute attribute,
        final AttributeModifier attributeModifier
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
    public B removeAttributeModifier(final Attribute... attribute) {
        for (final Attribute item : attribute) {
            this.itemMeta.removeAttributeModifier(item);
        }
        return (B) this;
    }

    /**
     * Builds the {@code ItemStack} from the set properties.
     *
     * @return the built {@code ItemStack}
     */
    public ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack.clone();
    }

}
