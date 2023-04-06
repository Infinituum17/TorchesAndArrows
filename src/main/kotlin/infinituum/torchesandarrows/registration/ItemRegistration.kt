package infinituum.torchesandarrows.registration

import infinituum.torchesandarrows.ExampleMod.MODID
import infinituum.torchesandarrows.items.TorchArrow
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object ItemRegistration {
    @JvmField
    var TORCH_ARROW = TorchArrow(FabricItemSettings())

    fun init() {
        Registry.register(Registries.ITEM, Identifier(MODID, "torch_arrow"), TORCH_ARROW)
    }
}