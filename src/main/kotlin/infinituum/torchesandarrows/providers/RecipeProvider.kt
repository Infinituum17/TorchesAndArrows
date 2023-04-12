package infinituum.torchesandarrows.providers

import infinituum.torchesandarrows.registration.ItemRegistration.SOUL_TORCH_ARROW
import infinituum.torchesandarrows.registration.ItemRegistration.TORCH_ARROW
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancement.criterion.InventoryChangedCriterion
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.Items
import net.minecraft.recipe.book.RecipeCategory
import java.util.function.Consumer

class RecipeProvider(output: FabricDataOutput?) : FabricRecipeProvider(output) {
    override fun generate(exporter: Consumer<RecipeJsonProvider>?) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, TORCH_ARROW)
            .input(Items.ARROW)
            .input(Items.TORCH)
            .criterion("has_items", InventoryChangedCriterion.Conditions.items(Items.ARROW, Items.TORCH))
            .offerTo(exporter)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, SOUL_TORCH_ARROW)
            .input(Items.ARROW)
            .input(Items.SOUL_TORCH)
            .criterion("has_items", InventoryChangedCriterion.Conditions.items(Items.ARROW, Items.SOUL_TORCH))
            .offerTo(exporter)
    }
}