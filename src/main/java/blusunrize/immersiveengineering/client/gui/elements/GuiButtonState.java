package blusunrize.immersiveengineering.client.gui.elements;

import blusunrize.immersiveengineering.api.Lib;
import blusunrize.immersiveengineering.client.ClientUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;

public class GuiButtonState extends GuiButtonIE
{
	public boolean state;
	protected final int offsetDir;
	public int[] textOffset = {0,0};
	public GuiButtonState(int buttonId, int x, int y, int w, int h, String name, boolean state, String texture, int u, int v, int offsetDir)
	{
		super(buttonId, x, y, w,h, name, texture, u,v);
		this.state = state;
		this.offsetDir = offsetDir;
		textOffset = new int[]{width+1,height/2-3};
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY)
	{
		if(this.visible)
		{
			ClientUtils.bindTexture(texture);
			FontRenderer fontrenderer = mc.fontRendererObj;
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);
			int u = texU + (!state?0: offsetDir==0?width: offsetDir==2?-width: 0);
			int v = texV + (!state?0: offsetDir==1?height: offsetDir==3?-height: 0);
			this.drawTexturedModalRect(xPosition,yPosition, u,v, width,height);
			this.mouseDragged(mc, mouseX, mouseY);
			if(displayString!=null && !displayString.isEmpty())
			{
				int txtCol = 0xE0E0E0;
				if(!this.enabled)
					txtCol = 0xA0A0A0;
				else if(this.hovered)
					txtCol = Lib.COLOUR_I_ImmersiveOrange;
				this.drawString(fontrenderer, displayString, xPosition+textOffset[0], yPosition+textOffset[1], txtCol);
			}
		}
	}

	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
	{
		boolean b = super.mousePressed(mc, mouseX, mouseY);
		if(b)
			this.state = !state;
		return b;
	}
}
