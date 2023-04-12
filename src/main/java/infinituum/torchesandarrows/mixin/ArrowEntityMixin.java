package infinituum.torchesandarrows.mixin;

import infinituum.torchesandarrows.utils.ArrowLightSource;
import infinituum.torchesandarrows.utils.ArrowWallLightSource;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
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
    private ArrowLightSource arrowLightSource = null;

    protected ArrowEntityMixin(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "initFromStack", at = @At("TAIL"))
    public void initFromStack(ItemStack stack, CallbackInfo ci) {
        if (stack.getItem() instanceof ArrowLightSource itemLightSource) {
            ((ArrowEntityAccessor) this).setPotion(Potions.EMPTY);
            ((ArrowEntityAccessor) this).getEffects().clear();

            this.dataTracker.set(ArrowEntityAccessor.getColor(), -1);
            this.arrowLightSource = itemLightSource;
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        if (!this.world.isClient() && arrowLightSource != null && this.inGround && this.inGroundTime != 0 && !hasPlacedLightingSource) {
            BlockState blockState = this.world.getBlockState(this.getBlockPos());

            if (isPlaceable(blockState)) {
                if (isSolid(this.getBlockPos().down())) {
                    placeLightSource();

                    hasPlacedLightingSource = true;

                    return;
                }

                if(arrowLightSource instanceof ArrowWallLightSource wallLightSource) {
                    for (Direction direction : Direction.values()) {
                        if (direction == Direction.DOWN) continue;

                        if (wallLightSource.isValidDirection(direction) && isSolid(this.getBlockPos().offset(direction))) {
                            placeWallLightSource(direction);

                            hasPlacedLightingSource = true;

                            return;
                        }
                    }
                }
            }

            dropLightSourceItem();
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        if(arrowLightSource != null) {
            nbt.putBoolean("HasPlacedLightSource", hasPlacedLightingSource);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if(nbt.contains("HasPlacedLightSource")) {
            this.hasPlacedLightingSource = nbt.getBoolean("HasPlacedLightSource");
        }
    }

    private void dropLightSourceItem() {
        BlockPos pos = this.getBlockPos();
        ItemEntity itemEntity = new ItemEntity(this.world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(this.arrowLightSource.getLightSourceItem()));

        this.world.spawnEntity(itemEntity);

        hasPlacedLightingSource = true;
    }

    private boolean isPlaceable(BlockState state) {
        if(this.arrowLightSource != null) {
            if(arrowLightSource instanceof ArrowWallLightSource wallLightSource) {
                return !state.isOf(wallLightSource.getLightSourceBlock()) && !state.isOf(wallLightSource.getWallLightSourceBlock());
            } else {
                return !state.isOf(arrowLightSource.getLightSourceBlock());
            }
        }

        return false;
    }

    private void placeLightSource() {
        if (this.arrowLightSource != null) {
            this.world.setBlockState(this.getBlockPos(), arrowLightSource.getLightSourceBlock().getDefaultState());
        }
    }

    private void placeWallLightSource(Direction direction) {
        if (this.arrowLightSource != null && this.arrowLightSource instanceof ArrowWallLightSource wallLightSource) {
            BlockState wallBlock = wallLightSource.getWallLightSourceBlock().getDefaultState();
            this.world.setBlockState(this.getBlockPos(), wallBlock.with(Properties.HORIZONTAL_FACING, direction.getOpposite()));
        }
    }

    private boolean isSolid(BlockPos position) {
        return this.world.getBlockState(position).isSolidBlock(world, position);
    }
}