package ladysnake.snowmercy.common.entity;

import ladysnake.snowmercy.common.SnowMercy;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum WeaponizedGolemType {
    SAWMAN("sawman"), SNUGGLES("mister_snuggles"), ROCKETS("aftermarket_snowman"), MORTARS("ice_mortar"), CHILL_SNUGGLES("mister_chill_snuggles");

    public static final WeaponizedGolemType DEFAULT = SNUGGLES;

    public static final TrackedDataHandler<WeaponizedGolemType> TRACKED_DATA_HANDLER = new TrackedDataHandler<>() {
        public void write(PacketByteBuf packetByteBuf, WeaponizedGolemType type) {
            packetByteBuf.writeEnumConstant(type);
        }

        public WeaponizedGolemType read(PacketByteBuf packetByteBuf) {
            return packetByteBuf.readEnumConstant(WeaponizedGolemType.class);
        }

        public WeaponizedGolemType copy(WeaponizedGolemType type) {
            return type;
        }
    };

    private static final Map<Identifier, WeaponizedGolemType> types = Arrays.stream(values()).collect(Collectors.toMap(WeaponizedGolemType::getId, Function.identity()));

    public static WeaponizedGolemType byId(Identifier id) {
        return types.getOrDefault(id, DEFAULT);
    }

    private final Identifier id;
    private final Identifier textureLocation;

    WeaponizedGolemType(String id) {
        this.id = SnowMercy.id(id);
        this.textureLocation = SnowMercy.id("textures/" + id + ".png");
    }

    public Identifier getId() {
        return this.id;
    }

    public Identifier getTexture() {
        return textureLocation;
    }

    public EntityType<? extends WeaponizedSnowGolemEntity> getEntityType() {
        @SuppressWarnings("unchecked") EntityType<? extends WeaponizedSnowGolemEntity> t = (EntityType<? extends WeaponizedSnowGolemEntity>) Registry.ENTITY_TYPE.get(this.id);
        return t;
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
}
