package net.danygames2014.backpacks.screen;

import net.danygames2014.backpacks.item.BackpackItem;
import net.minecraft.client.gui.screen.container.ContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class BackpackScreen extends ContainerScreen {
    public int rows;

    public BackpackScreen(PlayerEntity player, ItemStack stack) {
        super(new BackpackScreenHandler(player.inventory, stack));
        player.method_490("Backpack Opened");
        this.rows = ((BackpackItem)stack.getItem()).rows;
        this.backgroundHeight = 243 + 96;
        this.backgroundWidth = 248;
    }

    @Override
    protected void drawForeground() {
        this.textRenderer.draw("Nyaaaa!", 8, 6, 4210752);
        this.textRenderer.draw("Inventory", 8, this.rows * 18 + 17 + 3, 4210752); // 17 is the "chin" + 3
    }

    @Override
    protected void drawBackground(float tickDelta) {
        int backpackTextureId = this.minecraft.textureManager.getTextureId("assets/backpacks/stationapi/textures/gui/large_backpack.png");
        int playerInvTextureId = this.minecraft.textureManager.getTextureId("gui/container.png");

        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        this.drawTextWithShadow(this.textRenderer, "Testing Text", this.width/2, this.height/2, 4210752);

        GL11.glColor4f(0.2F, 0.2F, 0.2F, 1.0F);

        this.minecraft.textureManager.bindTexture(backpackTextureId);
        this.drawTexture(x, y+(int)tickDelta, 0, 0, this.backgroundWidth, this.rows * 18 + 27);

        this.minecraft.textureManager.bindTexture(playerInvTextureId);
        this.drawTexture(x, y + this.rows * 18 + 17, 0, 126, this.backgroundWidth, 96);
    }
}
