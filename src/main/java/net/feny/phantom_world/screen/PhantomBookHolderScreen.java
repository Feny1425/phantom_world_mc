package net.feny.phantom_world.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.feny.phantom_world.PhantomWorld.MOD_ID;

public class PhantomBookHolderScreen extends HandledScreen<PhantomBookHolderScreenHandler> {
    //A path to the gui texture. In this example we use the texture from the dispenser
    private boolean CombineButton = false;
    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/gui/phantom_book_holder_gui.png");

    public PhantomBookHolderScreen(PhantomBookHolderScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        if (CombineButton || !handler.canCombine()) renderButton(matrices,1,x,y);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        double d = mouseX - (double)(i + 66);
        double e = mouseY - (double)(j + 60);
        CombineButton = (d >= 0.0) && (e >= 0.0) && (d <= 43) && (e <= 11);
        if (CombineButton) {
            this.client.interactionManager.clickButton(((PhantomBookHolderScreenHandler) this.handler).syncId, 0);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void renderButton(MatrixStack matrices,int i,int x, int y){
        switch (i){
            case 1 : PhantomBookHolderScreen.drawTexture(matrices,x+66,y+60,176,0,43,12);break;
            case 2 : break;
            case 3 : break;
        }

    }
}