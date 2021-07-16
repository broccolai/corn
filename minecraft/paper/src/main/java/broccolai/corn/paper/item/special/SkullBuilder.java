package broccolai.corn.paper.item.special;

import broccolai.corn.paper.item.AbstractPaperItemBuilder;
import broccolai.corn.spigot.item.AridUtil;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Modifies {@link ItemStack}s that have an {@code ItemMeta} of {@link SkullMeta}.
 */
@SuppressWarnings("unused")
public final class SkullBuilder extends AbstractPaperItemBuilder<SkullBuilder, SkullMeta> {

    private SkullBuilder(final @NonNull ItemStack itemStack, final @NonNull SkullMeta itemMeta) {
        super(itemStack, itemMeta);
    }

    /**
     * Creates a {@code SkullBuilder}.
     *
     * @param itemStack the {@code ItemStack} to base the builder off of
     * @return instance of {@code SkullBuilder}
     * @throws IllegalArgumentException if the {@code itemStack}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull SkullBuilder of(final @NonNull ItemStack itemStack) throws IllegalArgumentException {
        return new SkullBuilder(itemStack, AridUtil.castMeta(itemStack.getItemMeta(), SkullMeta.class));
    }

    /**
     * Creates a {@code SkullBuilder}.
     *
     * @param material the {@code Material} to base the builder off of
     * @return instance of {@code SkullBuilder}
     * @throws IllegalArgumentException if the {@code material} is not an obtainable item,
     *                                  or if the {@code material}'s {@code ItemMeta} is not the correct type
     */
    public static @NonNull SkullBuilder ofType(final @NonNull Material material) throws IllegalArgumentException {
        return SkullBuilder.of(AridUtil.getItem(material));
    }

    /**
     * Gets the textures.
     *
     * @return the textures
     */
    public @NonNull List<ProfileProperty> textures() {
        final PlayerProfile playerProfile = Objects.requireNonNull(this.itemMeta.getPlayerProfile());

        final List<ProfileProperty> textures = new ArrayList<>();
        for (final ProfileProperty property : playerProfile.getProperties()) {
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
        final PlayerProfile playerProfile = Objects.requireNonNull(this.itemMeta.getPlayerProfile());
        playerProfile.setProperty(new ProfileProperty("textures", data));
        return this;
    }

}
