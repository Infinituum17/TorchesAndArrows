package infinituum.torchesandarrows.utils

import net.minecraft.block.Block
import net.minecraft.util.math.Direction

interface ArrowWallLightSource : ArrowLightSource {
    fun getWallLightSourceBlock(): Block
    override fun isValidDirection(direction: Direction): Boolean {
        return super.isValidDirection(direction) ||
                direction == Direction.EAST ||
                direction == Direction.WEST ||
                direction == Direction.NORTH ||
                direction == Direction.SOUTH
    }
}