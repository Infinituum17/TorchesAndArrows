package infinituum.torchesandarrows.mixin;

import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.potion.Potion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(ArrowEntity.class)
public interface ArrowEntityAccessor {
    @Accessor("potion")
    void setPotion(Potion potion);

    @Accessor
    Set<StatusEffectInstance> getEffects();

    @Accessor("COLOR")
    static TrackedData<Integer> getColor() {
        throw new AssertionError();
    }
}
