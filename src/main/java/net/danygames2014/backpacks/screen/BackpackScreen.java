package net.danygames2014.backpacks.screen;

import net.danygames2014.backpacks.item.BackpackItem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class BackpackScreen extends HandledScreen {
    public static final int SLOT_SIZE = 18;
    public static final int CORNER_SIZE = 7;
    public static final int EDGE_SIZE = 7;
    public int rows;
    public int columns;
    public int playerInventoryHorizontalOffset;

    public BackpackScreen(PlayerEntity player, ItemStack backpackStack) {
        super(new BackpackScreenHandler(player.inventory, backpackStack));
        this.rows = ((BackpackItem) backpackStack.getItem()).rows;
        this.columns = ((BackpackItem) backpackStack.getItem()).columns;
        this.backgroundHeight = 118 + (rows * SLOT_SIZE); // 118 = 17(Top Edge) + 4(Non-Overlapped Bottom Edge) + 97(Inventory Texture Height)
        this.backgroundWidth = Math.max(176, 14 + (columns * SLOT_SIZE)); // 176 = Inventory Texture Width
        this.playerInventoryHorizontalOffset = (this.backgroundWidth - 176) / 2;
    }

    @Override
    protected void drawForeground() {
        this.textRenderer.draw("Backpack", 8, 6, 4210752);
        this.textRenderer.draw("Inventory", 8 + playerInventoryHorizontalOffset, (SLOT_SIZE * rows) + 24, 4210752);
    }

    @Override
    protected void drawBackground(float tickDelta) {
        int containerTexturesId = this.minecraft.textureManager.getTextureId("/assets/nyabags/stationapi/textures/gui/modular_container.png");
        int playerInventoryTextureId = this.minecraft.textureManager.getTextureId("/assets/nyabags/stationapi/textures/gui/player_inventory.png");
        int slotsTextureId = this.minecraft.textureManager.getTextureId("/assets/nyabags/stationapi/textures/gui/slots.png");

        int x = (this.width - this.backgroundWidth) / 2;
        int y = (this.height - this.backgroundHeight) / 2;

        GL11.glColor4f(1F, 1F, 1F, 1.0F);

        // Backpack Frame
        this.minecraft.textureManager.bindTexture(containerTexturesId);
        this.drawTexture(x, y, 57, 211, CORNER_SIZE, CORNER_SIZE); // Top Left Corner - Small Corner
        this.drawTexture(x + CORNER_SIZE, y, 18, 230, SLOT_SIZE * columns, 17); // Top Edge
        this.drawTexture(x + CORNER_SIZE + (SLOT_SIZE * columns), y, 66, 211, CORNER_SIZE, CORNER_SIZE); // Top Right Corner
        this.drawTexture(x, y + CORNER_SIZE, 0, 0, EDGE_SIZE, 10 + (SLOT_SIZE * rows)); // Left Edge
        this.drawTexture(x + CORNER_SIZE + (SLOT_SIZE * columns), y + CORNER_SIZE, 9, 0, EDGE_SIZE, 10 + (SLOT_SIZE * rows)); // Right Edge
        this.drawTexture(x, y + (SLOT_SIZE * rows) + 17, 57, 220, EDGE_SIZE, EDGE_SIZE); // Bottom Left Corner
        this.drawTexture(x + EDGE_SIZE, y + (SLOT_SIZE * rows) + 17, 18, 249, SLOT_SIZE * columns, CORNER_SIZE); // Bottom Edge
        this.drawTexture(x + EDGE_SIZE + (SLOT_SIZE * columns), y + (SLOT_SIZE * rows) + 17, 66, 220, CORNER_SIZE, CORNER_SIZE); // Bottom Right Corner

        // Backpack Slots
        this.minecraft.textureManager.bindTexture(slotsTextureId);
        this.drawTexture(x + EDGE_SIZE, y + CORNER_SIZE + 10, 0, 0, SLOT_SIZE * columns, SLOT_SIZE * rows);

        // Player Inventory
        this.minecraft.textureManager.bindTexture(playerInventoryTextureId);
        this.drawTexture(x + this.playerInventoryHorizontalOffset, y + (SLOT_SIZE * rows) + 21, 0, 2, this.backgroundWidth, 95); // 21 = 17(Top Edge) + ( 7(Bottom Edge) - 3(Player Inventory Overlap) )
    }
}
