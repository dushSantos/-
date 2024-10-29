package ru.vizzi.Utils.gui;
import java.awt.Color;

import ru.vizzi.Utils.gui.drawmodule.GuiUtils;


@Deprecated
public class GuiButtonAdvancedImageCooldown extends GuiButtonAdvanced {
	
	private int xImage, yImage, wImage, hImage;
	private int xText;
	public long cooldown = 0;

	public GuiButtonAdvancedImageCooldown(int id, float x, float y, float width, float height, String text, float textScale, int xImage, int yImage, int wImage, int hImage, int xText) {
		super(id, x, y, width, height, text, textScale);
		// TODO Auto-generated constructor stub
		this.xImage = xImage;
		this.yImage = yImage;
		this.wImage = wImage;
		this.hImage = hImage;
		this.xText = xText;
	}
	
	
	
	public void drawButton(int mouseX, int mouseY) {
		double alpha = 1.0;
        if (enabled && visible) {
        
        	alpha = 1.0;
        	
        if(cooldown != 0)
        	alpha = 0.5;
        
        } else {
        	alpha = 0.5;
        }
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
            			
            			GuiUtils.drawRectS(xPosition, yPosition, width, height, colorBackground, alpha);
            		}
            }
            }
            
            if(texture != null && cooldown == 0) {
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

            		if(active) {
            			drawText(colorTextActive, alpha);
            		} else {
            			drawText(colorText, alpha);
            		}
            	}
            
            
        
    }
	
	 protected void drawText(int col, double alpha) {
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
	     //   GuiDrawUtils.drawStringNoScale(FontType.FUTURA_PT_DEMI.getFontContainer(), displayString, this.xPosition + this.width / 2.0f
	          //      - FontType.FUTURA_PT_DEMI.getFontContainer().width(displayString) * textScale / 2f, this.yPosition + this.height / 2.0f - paddingY, textScale, j);
	       

	       if(cooldown != 0) {
	    	   long cool = cooldown / 20;
	    	 //  TextRenderUtils.drawCenteredTextWithAlpha(this.xPosition+this.width / 2, this.yPosition+this.height/6,alpha, col, cool, CustomFont.saira_regular.setSize((int) textScale));
	       } else {
	        
	      //  TextRenderUtils.drawTextWithAlpha(this.xPosition+xText, this.yPosition+this.height/6,alpha, col, this.displayString, CustomFont.saira_regular.setSize((int) textScale));
	       }
	    }

}
