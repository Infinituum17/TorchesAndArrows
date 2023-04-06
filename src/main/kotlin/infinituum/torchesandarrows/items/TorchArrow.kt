package infinituum.torchesandarrows.items

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ArrowItem
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class TorchArrow(settings: Settings) : ArrowItem(settings) {
    override fun createArrow(world: World?, stack: ItemStack?, shooter: LivingEntity?): PersistentProjectileEntity {
        return super.createArrow(world, stack, shooter)
    }
}