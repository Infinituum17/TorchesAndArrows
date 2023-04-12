package infinituum.torchesandarrows.items

import infinituum.torchesandarrows.utils.ArrowWallLightSource
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.ArrowItem
import net.minecraft.item.Item
import net.minecraft.item.Items

class SoulTorchArrow(settings: Settings) : ArrowItem(settings), ArrowWallLightSource {
    override fun getWallLightSourceBlock(): Block {
        return Blocks.SOUL_WALL_TORCH
    }

    override fun getLightSourceItem(): Item {
        return Items.SOUL_TORCH
    }

    override fun getLightSourceBlock(): Block {
        return Blocks.SOUL_TORCH
    }
}