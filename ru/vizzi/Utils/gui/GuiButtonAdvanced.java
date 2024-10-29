package ru.vizzi.Utils.gui;

import java.awt.Color;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import ru.vizzi.Utils.CustomFont.FontType;
import ru.vizzi.Utils.gui.drawmodule.AnimationHelper;
import ru.vizzi.Utils.gui.drawmodule.GuiDrawUtils;
import ru.vizzi.Utils.gui.drawmodule.GuiUtils;

@Getter
@Setter
public class GuiButtonAdvanced extends GuiButtonNew {

    protected static final Minecraft mc = Minecraft.getMinecraft();

    protected ResourceLocation texture, textureHover, textureActive;

    public float xBase, yBase;
    public float widthBase, heightBase;
    protected float textBlending;
    protected float textScale;
    protected boolean hovered;
    protected boolean active;
    public int colorBackground = -1;
    public int colorHoverBackground =-1;
    public int colorActiveBackground =-1;
    public int colorTextActive = -1;
    public int colorText = -1;
    public int colorTextHover = -1;
  


    public GuiButtonAdvanced(int id, float x, float y, float width, float height, String text) {
        super(id, x, y, width, height, text);
        this.xBase = x;
        this.yBase = y;
        this.widthBase = width;
        this.heightBase = height;
        this.textScale = height / 36f;
        
    }

    public GuiButtonAdvanced(int id, float x, float y, float width, float height, String text, float textScale) {
        super(id, x, y, width, height, text);
        this.xBase = x;
        this.yBase = y;
        this.widthBase = width;
        this.heightBase = height;
        this.textScale = textScale;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        this.drawButton(mouseX, mouseY);
    }

    protected void updateAnimation(boolean hovered) {
        if (hovered) {
            textBlending += AnimationHelper.getAnimationSpeed() * 0.1f;
            if (textBlending > 1) textBlending = 1;
        } else {
            textBlending -= AnimationHelper.getAnimationSpeed() * 0.1f;
            if (textBlending < 0) textBlending = 0f;
        }
    }
    


    public void drawButton(int mouseX, int mouseY) {
        if (enabled && visible) {
            boolean hovered = isHovered(mouseX, mouseY);
            if(hovered && !this.hovered) {
             //   SoundUtils.playGuiSound(SoundType.BUTTON_HOVER);
            }
            this.hovered = hovered;
            updateAnimation(hovered);
            
      
            if(colorBackground != -1) {
            	
            	if(isHovered() && colorHoverBackground != -1 && !active) {
            		GuiUtils.drawRectS(xPosition, yPosition, width, height, colorHoverBackground, 1.0);
            	} else {
            		if(active && colorActiveBackground != -1) {
            			
            			GuiUtils.drawRectS(xPosition, yPosition, width, height, colorActiveBackground, 1.0);
            		} else {
            			
            			GuiUtils.drawRectS(xPosition, yPosition, width, height, colorBackground, 1.0);
            		}
            }
            }
            
            
            if(texture != null) {
            	if(isHovered() && textureHover != null && !active) {
            		GuiUtils.drawImageNew(textureHover, xPosition, this.yPosition, this.width, this.height, 1.0);
            	} else {
            		if(active && textureActive != null) {
            			
            			GuiUtils.drawImageNew(textureActive, xPosition, this.yPosition, this.width, this.height, 1.0);
            		} else {
            			GuiUtils.drawImageNew(texture, xPosition, this.yPosition, this.width, this.height, 1.0);
            		}
            	}
            }

            if(colorText != -1) {
            	if(isHovered() && !active) {
            		drawText(colorTextHover);
            	} else {
            		if(active) {
            			drawText(colorTextActive);
            		} else {
            			drawText(colorText);
            		}
            	}
            }
            
        }
    }
    
    public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_)
    {
    	
        if(this.enabled && this.visible && p_146116_2_ >= this.xPosition && p_146116_3_ >= this.yPosition && p_146116_2_ < this.xPosition + this.width && p_146116_3_ < this.yPosition + this.height) {
        	return true;
        }
        return false;
    }

    protected boolean isHovered(int mouseX, int mouseY) {
    	
        return this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }

    protected void drawText(int col) {
        int j;
        if(enabled) {
            int r = ((0x959595 & 0xFF0000) >> 16) + (int)((255 - ((0x959595 & 0xFF0000) >> 16)) * textBlending);
            int g = ((0x959595 & 0xFF00) >> 8) + (int)((255 - ((0x959595 & 0xFF00) >> 8)) * textBlending);
            int b = (0x959595 & 0xFF) + (int)((255 - ((0x959595 & 0xFF))) * textBlending);
            j = (int) Long.parseLong(Integer.toHexString(new Color(r,g,b).getRGB()), 16);
        } else {
            j = 0x333333;
        }

        if (packedFGColour != 0) {
            j = packedFGColour;
        }

        float paddingY = textScale * 4f;
        GuiDrawUtils.drawStringNoScale(FontType.FUTURA_PT_MEDIUM.getFontContainer(), displayString, this.xPosition + this.width / 2.0f
                - FontType.FUTURA_PT_MEDIUM.getFontContainer().width(displayString) * textScale / 2f, this.yPosition + this.height / 2.0f - paddingY, textScale, j);
       

       
        
        //TextRenderUtils.drawCenteredText(this.xPosition+this.width/2, this.yPosition+this.height/6, col, this.displayString, CustomFont.saira_regular.setSize((int) textScale));
    }

    public void addXPosition(float x) {
        this.xPosition = (int) (xBase + x);
    }

    public void addYPosition(float y) {
        this.yPosition = (int) (yBase + y);
    }

    public void scaleXPosition(float x) {
        this.xPosition = (int) (xBase * x);
    }

    public void scaleYPosition(float y) {
        this.yPosition = (int) (yBase * y);
    }

    public void setXPosition(float x) {
        this.xBase = this.xPosition = (int) x;
    }

    public void setYPosition(float y) {
        this.yBase = this.yPosition = (int) y;
    }

    public void setHeight(float height) {
        this.heightBase = this.height = (int) height;
    }

    public void setWidth(float width) {
        this.widthBase = this.width = (int) width;
    }

    public void scaleWidth(float x) {
        this.width = (int) (widthBase * x);
    }

    public void scaleHeight(float y) {
        this.height = (int) (heightBase * y);
    }
}
