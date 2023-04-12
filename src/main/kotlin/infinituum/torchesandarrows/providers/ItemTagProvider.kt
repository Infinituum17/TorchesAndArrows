package infinituum.torchesandarrows.providers

import infinituum.torchesandarrows.registration.ItemRegistration.SOUL_TORCH_ARROW
import infinituum.torchesandarrows.registration.ItemRegistration.TORCH_ARROW
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup?) {
        getOrCreateTagBuilder(ItemTags.ARROWS)
            .add(TORCH_ARROW)
            .add(SOUL_TORCH_ARROW)
    }
}