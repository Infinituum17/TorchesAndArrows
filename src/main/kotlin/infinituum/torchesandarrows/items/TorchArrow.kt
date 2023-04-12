package infinituum.torchesandarrows.items

import infinituum.torchesandarrows.utils.ArrowWallLightSource
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.ArrowItem
import net.minecraft.item.Item
import net.minecraft.item.Items

class TorchArrow(settings: Settings) : ArrowItem(settings), ArrowWallLightSource {
    override fun getLightSourceItem(): Item {
        return Items.TORCH
    }

    override fun getLightSourceBlock(): Block {
        return Blocks.TORCH
    }

    override fun getWallLightSourceBlock(): Block {
        return Blocks.WALL_TORCH
    }
}