package infinituum.torchesandarrows.providers.language

import infinituum.torchesandarrows.registration.ItemRegistration
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider

class ItalianLangProvider(dataOutput: FabricDataOutput?) : FabricLanguageProvider(dataOutput, "it_it") {
    override fun generateTranslations(translationBuilder: TranslationBuilder?) {
        translationBuilder?.add(ItemRegistration.TORCH_ARROW, "Freccia con torcia")
        translationBuilder?.add(ItemRegistration.SOUL_TORCH_ARROW, "Freccia con torcia delle anime")
    }
}