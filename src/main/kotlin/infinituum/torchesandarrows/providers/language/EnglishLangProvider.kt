package infinituum.torchesandarrows.providers.language

import infinituum.torchesandarrows.registration.ItemRegistration.SOUL_TORCH_ARROW
import infinituum.torchesandarrows.registration.ItemRegistration.TORCH_ARROW
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider

class EnglishLangProvider(dataOutput: FabricDataOutput?) : FabricLanguageProvider(dataOutput) {
    override fun generateTranslations(translationBuilder: TranslationBuilder?) {
        translationBuilder?.add(TORCH_ARROW, "Torch Arrow")
        translationBuilder?.add(SOUL_TORCH_ARROW, "Soul Torch Arrow")
    }
}