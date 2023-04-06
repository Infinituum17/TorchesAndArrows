package infinituum.torchesandarrows

import infinituum.torchesandarrows.registration.ItemRegistration
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object ExampleMod : ModInitializer {
	const val MODID = "torchesandarrows"
	private val logger = LoggerFactory.getLogger(MODID)

	override fun onInitialize() {
		logger.info("Initializing...")

		ItemRegistration.init()
	}
}