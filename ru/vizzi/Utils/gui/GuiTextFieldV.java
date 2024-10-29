package ru.vizzi.Utils.gui;
import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.Getter;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ChatAllowedCharacters;
import ru.vizzi.Utils.gui.drawmodule.GuiUtils;
import ru.vizzi.Utils.gui.drawmodule.ScaleGui;

@SideOnly(Side.CLIENT)
@Getter
public class GuiTextFieldV extends Gui
{
    public int xPosition;
    public int yPosition;
    /** The width of this text field. */
    public int width;
    public int height;
    /** Has the current text being edited on the textbox. */
    private String text = "";
    private String backgroundText;
    private int maxStringLength;
    private int maxSplitLength;
    private int colorbackground;
    private int colortext;
    private int colorTextActive;
    private int scaletext;
    private boolean focused;
    
    private boolean visible = true;
    
    private boolean enabled = true;

    public GuiTextFieldV(int x, int y, int w, int h, int colorBackground, int colorText, int colorTextActive,  int scaleText, int maxStringLenght, int maxSplitText)
    {
        this.xPosition = x;
        this.yPosition = y;
        this.width = w;
        this.height = h;
        this.colorbackground = colorBackground;
        this.colortext = colorText;
        this.colorTextActive = colorTextActive;
        this.scaletext = scaleText;
        this.maxStringLength = maxStringLenght;
        this.maxSplitLength = (int)ScaleGui.get(maxSplitText);
        
    }
    
    
    
    public void render() {

    	GuiUtils.drawRectS(xPosition, yPosition, width, height, colorbackground, 1.0);
    	
    	String renderText;
    	int renderColor;
    	if(text.equals("")) {
    		renderText = backgroundText;
    		renderColor = colortext;
    	} else {
    		
    		renderText = text+"_";
    		renderColor = colorTextActive;
    	}
    	//TextRenderUtils.drawSplitText(xPosition+ScaleGui.get(10), yPosition+ScaleGui.get(8), maxSplitLength, renderColor, renderText, CustomFont.saira_regular.setSize(ScaleGui.get(scaletext)));
    }
    
    public void writeText(String text, int key) {
    	if(focused) {
    		switch(key){
    			case 14:
    				deleteWord(1);
    				break;
    			default:
    					if(this.text.length() < maxStringLength) {
    			    		this.text += ChatAllowedCharacters.filerAllowedCharacters(text);
    			    		}
    		}
    		
    	}
    }
    public void deleteWord(int size) {
    	if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
    		this.text = "";
    		return;
    	}
    	if(text.length()>0) {
    	String s = this.text.substring(0, this.text.length()-size);
    	this.text = s;
    	}
    }
    public void setText(String text) {
    	
    	if(text.length()>maxStringLength) {
		this.text = text.substring(0, maxStringLength);
    	} else {
    		this.text = text;
    	}
    }
    
    	public void setBackgroundText(String text) {
    	
    	if(text.length()>maxStringLength) {
		this.backgroundText = text.substring(0, maxStringLength);
    	} else {
    		this.backgroundText = text;
    	}
    }
    
    public void mouseClicked(int x, int y, int button) {
    	
    	if(button == 0) {

    		if(this.enabled && this.visible && x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height) {
            	this.focused = true;
            } else {
            	this.focused = false;
            }
    	}
    }

 
}