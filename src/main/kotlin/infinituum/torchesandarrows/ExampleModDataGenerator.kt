package infinituum.torchesandarrows

import infinituum.torchesandarrows.providers.ItemTagProvider
import infinituum.torchesandarrows.providers.ModelProvider
import infinituum.torchesandarrows.providers.RecipeProvider
import infinituum.torchesandarrows.providers.language.EnglishLangProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object ExampleModDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		val pack = fabricDataGenerator.createPack()

		pack.addProvider(::ModelProvider)
		pack.addProvider(::ItemTagProvider)
		pack.addProvider(::RecipeProvider)

		registerLanguages(pack)
	}

	private fun registerLanguages(pack: FabricDataGenerator.Pack) {
		pack.addProvider(::EnglishLangProvider)
	}
}