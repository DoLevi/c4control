package de.untenrechts.c4control.block;

import net.minecraft.client.gui.inventory.GuiContainer;

public class BlockATMContainerGui extends GuiContainer {

    private static final int WIDTH = 180;
    private static final int HEIGHT = 152;

    public BlockATMContainerGui(BlockATMContainerTileEntity tileEntity, BlockATMContainer container) {
        super(container);
        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        //mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
