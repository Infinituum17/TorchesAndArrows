package infinituum.torchesandarrows.providers

import infinituum.torchesandarrows.registration.ItemRegistration.SOUL_TORCH_ARROW
import infinituum.torchesandarrows.registration.ItemRegistration.TORCH_ARROW
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models

class ModelProvider(output: FabricDataOutput?) : FabricModelProvider(output) {
    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator?) {}

    override fun generateItemModels(itemModelGenerator: ItemModelGenerator?) {
        itemModelGenerator?.register(TORCH_ARROW, Models.GENERATED)
        itemModelGenerator?.register(SOUL_TORCH_ARROW, Models.GENERATED)
    }
}
