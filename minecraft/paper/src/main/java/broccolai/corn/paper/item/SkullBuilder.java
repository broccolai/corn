package broccolai.corn.paper.item;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link SkullMeta}.
 */
@SuppressWarnings("unused")
public class SkullBuilder extends AbstractPaperItemBuilder<SkullBuilder, SkullMeta> {

    private SkullBuilder(final @NonNull ItemStack item, final @NonNull SkullMeta meta) {
        super(item, meta);
    }

    /**
     * Creates a {@code SkullBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code SkullBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull SkullBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        final ItemMeta meta = itemStack.getItemMeta();

        if (meta instanceof final SkullMeta newMeta) {
            return new SkullBuilder(itemStack, newMeta);
        } else {
            throw new IllegalArgumentException("The ItemStack's ItemMeta must be of type "
                    + SkullMeta.class.getSimpleName()
                    + " but received ItemMeta of type "
                    + meta.getClass().getSimpleName());
        }
    }

    /**
     * Creates a {@code SkullBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code SkullBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item
     */
    public static @NonNull SkullBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        if (!material.isItem()) {
            throw new IllegalArgumentException("The Material must be an obtainable item.");
        }

        return SkullBuilder.of(new ItemStack(material));
    }

    /**
     * Gets the textures.
     *
     * @return the textures
     */
    public @NonNull List<ProfileProperty> textures() {
        PlayerProfile playerProfile = Objects.requireNonNull(this.itemMeta.getPlayerProfile());

        List<ProfileProperty> textures = new ArrayList<>();
        for (ProfileProperty property : playerProfile.getProperties()) {
            if (property.getName().equals("textures")) {
                textures.add(property);
            }
        }

        return textures;
    }

    /**
     * Sets the textures.
     *
     * @param data the textures
     * @return the builder
     */
    public @NonNull SkullBuilder textures(final @NonNull String data) {
        PlayerProfile playerProfile = Objects.requireNonNull(this.itemMeta.getPlayerProfile());
        playerProfile.setProperty(new ProfileProperty("textures", data));
        return this;
    }

}
