package infinituum.torchesandarrows.mixin;

import infinituum.torchesandarrows.registration.ItemRegistration;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potions;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArrowEntity.class)
public abstract class ArrowEntityMixin extends PersistentProjectileEntity {
    private boolean hasPlacedLightingSource = false;
    private Item lightingSource = null;

    protected ArrowEntityMixin(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initFromStack", at = @At("TAIL"))
    public void initFromStack(ItemStack stack, CallbackInfo ci) {
        if (stack.isOf(ItemRegistration.TORCH_ARROW)) {
            ((ArrowEntityAccessor) this).setPotion(Potions.EMPTY);
            ((ArrowEntityAccessor) this).getEffects().clear();

            this.dataTracker.set(ArrowEntityAccessor.getColor(), -1);
            this.lightingSource = Items.TORCH;
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        if (!this.world.isClient() && lightingSource != null && this.inGround && this.inGroundTime != 0) {
            BlockState blockState = this.world.getBlockState(this.getBlockPos());

            if (hasPlacedLightingSource) return;

            if (isPlaceable(blockState)) {
                if (isSolid(this.getBlockPos().down())) {
                    placeLightSource();

                    hasPlacedLightingSource = true;

                    return;
                } else {
                    for (Direction direction : Direction.values()) {
                        if (direction == Direction.DOWN || isInvalidDirection(direction)) continue;

                        if (isSolid(this.getBlockPos().offset(direction))) {
                            placeLightSourceWall(direction);

                            hasPlacedLightingSource = true;

                            return;
                        }
                    }
                }
            }

            BlockPos pos = this.getBlockPos();
            ItemEntity itemEntity = new ItemEntity(this.world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(this.lightingSource));

            this.world.spawnEntity(itemEntity);

            hasPlacedLightingSource = true;
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        if(lightingSource != null) {
            nbt.putBoolean("HasPlacedLightingSource", hasPlacedLightingSource);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if(nbt.contains("HasPlacedLightingSource")) {
            this.hasPlacedLightingSource = nbt.getBoolean("HasPlacedLightingSource");
        }
    }

    private boolean isInvalidDirection(Direction direction) {
        if(this.lightingSource == Items.TORCH) {
            return direction == Direction.UP;
        } else {
            return false;
        }
    }

    private boolean isPlaceable(BlockState state) {
        if(this.lightingSource == Items.TORCH) {
            return !state.isOf(Blocks.TORCH) && !state.isOf(Blocks.WALL_TORCH);
        } else {
            return false;
        }
    }

    private void placeLightSource() {
        if (this.lightingSource == Items.TORCH) {
            this.world.setBlockState(this.getBlockPos(), Blocks.TORCH.getDefaultState());
        }
    }

    private void placeLightSourceWall(Direction direction) {
        if (this.lightingSource == Items.TORCH) {
            BlockState wallTorch = Blocks.WALL_TORCH.getDefaultState();
            this.world.setBlockState(this.getBlockPos(), wallTorch.with(Properties.HORIZONTAL_FACING, direction.getOpposite()));
        }
    }

    private boolean isSolid(BlockPos position) {
        return this.world.getBlockState(position).isSolidBlock(world, position);
    }
}