package infinituum.torchesandarrows.registration

import infinituum.torchesandarrows.ExampleMod.MODID
import infinituum.torchesandarrows.items.SoulTorchArrow
import infinituum.torchesandarrows.items.TorchArrow
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.ItemGroups
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object ItemRegistration {
    @JvmField var TORCH_ARROW = TorchArrow(FabricItemSettings())
    @JvmField var SOUL_TORCH_ARROW = SoulTorchArrow(FabricItemSettings())

    fun init() {
        Registry.register(Registries.ITEM, Identifier(MODID, "torch_arrow"), TORCH_ARROW)
        Registry.register(Registries.ITEM, Identifier(MODID, "soul_torch_arrow"), SOUL_TORCH_ARROW)

        addToCreativeTab()
    }

    private fun addToCreativeTab() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register { content ->
            content.add(TORCH_ARROW)
            content.add(SOUL_TORCH_ARROW)
        }
    }
}