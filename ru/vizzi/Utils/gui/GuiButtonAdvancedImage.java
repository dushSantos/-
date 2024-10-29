package ru.vizzi.Utils.gui;

import java.awt.Color;

import ru.vizzi.Utils.CustomFont.FontType;
import ru.vizzi.Utils.gui.drawmodule.GuiDrawUtils;
import ru.vizzi.Utils.gui.drawmodule.GuiUtils;

public class GuiButtonAdvancedImage extends GuiButtonAdvanced {
	
	private int xImage, yImage, wImage, hImage;
	private int xText;

	public GuiButtonAdvancedImage(int id, float x, float y, float width, float height, String text, float textScale, int xImage, int yImage, int wImage, int hImage, int xText) {
		super(id, x, y, width, height, text, textScale);
		// TODO Auto-generated constructor stub
		this.xImage = xImage;
		this.yImage = yImage;
		this.wImage = wImage;
		this.hImage = hImage;
		this.xText = xText;
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
            		GuiUtils.drawImageNew(texture, xPosition+xImage, this.yPosition+yImage, this.wImage, this.hImage, 1.0);
            	} else {
            		if(active && textureActive != null) {
            			
            			GuiUtils.drawImageNew(texture, xPosition+xImage, this.yPosition+yImage, this.wImage, this.hImage, 1.0);
            		} else {
            			GuiUtils.drawImageNew(texture, xPosition+xImage, this.yPosition+yImage, this.wImage, this.hImage, 1.0);
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
	       

	       
	        
	       // TextRenderUtils.drawText(this.xPosition+xText, this.yPosition+this.height/6, col, this.displayString, CustomFont.saira_regular.setSize((int) textScale));
	    }

}