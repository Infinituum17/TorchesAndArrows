package infinituum.torchesandarrows

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object ExampleMod : ModInitializer {
	private val logger = LoggerFactory.getLogger("torchesandarrows")

	override fun onInitialize() {
		logger.info("Hello Fabric world!")
	}
}