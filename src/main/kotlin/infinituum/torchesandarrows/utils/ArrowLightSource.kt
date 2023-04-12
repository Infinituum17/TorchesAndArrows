package infinituum.torchesandarrows.utils

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.util.math.Direction

interface ArrowLightSource {
    fun getLightSourceItem(): Item
    fun getLightSourceBlock(): Block
    fun isValidDirection(direction: Direction): Boolean {
        return direction == Direction.DOWN
    }
}