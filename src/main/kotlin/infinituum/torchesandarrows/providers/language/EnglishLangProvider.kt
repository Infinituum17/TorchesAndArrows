package infinituum.torchesandarrows.providers.language

import infinituum.torchesandarrows.registration.ItemRegistration.TORCH_ARROW
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider

class EnglishLangProvider(dataOutput: FabricDataOutput?) : FabricLanguageProvider(dataOutput) {
    override fun generateTranslations(translationBuilder: TranslationBuilder?) {
        translationBuilder?.add(TORCH_ARROW, "Torch Arrow")
    }
}