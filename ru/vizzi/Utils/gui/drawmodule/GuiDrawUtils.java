package ru.vizzi.Utils.gui.drawmodule;

import static org.lwjgl.opengl.GL11.GL_FLAT;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SMOOTH;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_ZERO;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL14.glBlendFuncSeparate;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector4f;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.util.ResourceLocation;
import ru.vizzi.Utils.CustomFont.EnumStringRenderType;
import ru.vizzi.Utils.CustomFont.FontContainer;
import ru.vizzi.Utils.CustomFont.FontType;
import ru.vizzi.Utils.CustomFont.StringCache;

public class GuiDrawUtils {
    private static final ResourceLocation textureGridTransparent = new ResourceLocation("escmenu", "textures/grid_tr.png");
    private static final ResourceLocation textureGridMask = new ResourceLocation("escmenu", "textures/grid_rect_black.png");

    private static final Tessellator tessellator = Tessellator.instance;
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final RenderItem renderItem = RenderItem.getInstance();
    private static float animBackground;
    private static float breathAnimation;



    public static void renderCuttedRectBack(float x1, float y1, float x2, float y2, float cutSize, float halfY1, float halfY2, float r, float g, float b, float a, float r1, float g1, float b1, float a1) {
        GL11.glShadeModel(GL11.GL_SMOOTH);
        tessellator.startDrawing(GL_TRIANGLES);
        float colorScale = 1.2f;
        float halfColorR = (r1 - r) / colorScale;
        float halfColorG = (g1 - g) / colorScale;
        float halfColorB = (b1 - b) / colorScale;
        float halfColorA = (a1 - a) / colorScale;
        tessellator.setColorRGBA_F(r1, g1, b1, a1);
        tessellator.addVertex(x2, y1, 0);
        tessellator.addVertex(x1 + cutSize, y1, 0);
        tessellator.setColorRGBA_F(halfColorR, halfColorG, halfColorB, halfColorA);
        tessellator.addVertex(x1, y1 + cutSize, 0);

        tessellator.setColorRGBA_F(r1, g1, b1, a1);
        tessellator.addVertex(x2, y1, 0);
        tessellator.setColorRGBA_F(halfColorR, halfColorG, halfColorB, halfColorA);
        tessellator.addVertex(x1, y1 + cutSize, 0);
        tessellator.setColorRGBA_F(r, g, b, a);
        tessellator.addVertex(x1, y1 + halfY1, 0);

        tessellator.setColorRGBA_F(r1, g1, b1, a1);
        tessellator.addVertex(x2, y1, 0);
        tessellator.setColorRGBA_F(r, g, b, a);
        tessellator.addVertex(x1, y1 + halfY1, 0);
        tessellator.addVertex(x2, y1 + halfY2, 0);

        tessellator.addVertex(x2, y1 + halfY2, 0);
        tessellator.addVertex(x1, y1 + halfY1, 0);
        tessellator.addVertex(x2, y2 - cutSize, 0);

        tessellator.addVertex(x1, y1 + halfY1, 0);
        tessellator.addVertex(x2 - cutSize, y2, 0);
        tessellator.addVertex(x2, y2 - cutSize, 0);

        tessellator.addVertex(x1, y1 + halfY1, 0);
        tessellator.addVertex(x1, y2, 0);
        tessellator.addVertex(x2 - cutSize, y2, 0);

        tessellator.draw();
        GL11.glShadeModel(GL_FLAT);
    }

    public static void renderCuttedRect(float x1, float y1, float x2, float y2, float cutSize, float halfY1, float halfY2, float r, float g, float b, float a, float r1, float g1, float b1, float a1) {
        GL11.glShadeModel(GL11.GL_SMOOTH);
        tessellator.startDrawing(GL_TRIANGLES);
        tessellator.setColorRGBA_F(r1, g1, b1, a1);
        tessellator.addVertex(x1, y1, 0);
        tessellator.addVertex(x2, y1 + cutSize, 0);
        tessellator.addVertex(x2 - cutSize, y1, 0);

        tessellator.addVertex(x1, y1, 0);
        tessellator.setColorRGBA_F(r, g, b, a);
        tessellator.addVertex(x2, y1 + halfY2, 0);
        tessellator.setColorRGBA_F(r1, g1, b1, a1);
        tessellator.addVertex(x2, y1 + cutSize, 0);

        tessellator.addVertex(x1, y1, 0);
        tessellator.setColorRGBA_F(r, g, b, a);
        tessellator.addVertex(x1, y1 + halfY1, 0);
        tessellator.addVertex(x2, y1 + halfY2, 0);

        tessellator.addVertex(x1, y1 + halfY1, 0);
        tessellator.addVertex(x2, y2, 0);
        tessellator.addVertex(x2, y1 + halfY2, 0);

        tessellator.addVertex(x1, y1 + halfY1, 0);
        tessellator.addVertex(x1 + cutSize, y2, 0);
        tessellator.addVertex(x2, y2, 0);

        tessellator.addVertex(x1, y1 + halfY1, 0);
        tessellator.addVertex(x1, y2 - cutSize, 0);
        tessellator.addVertex(x1 + cutSize, y2, 0);
        tessellator.draw();
        GL11.glShadeModel(GL_FLAT);
    }


    public static void renderTooltipItem(float x1, float y1, float x2, float y2, float cutSize, Vector4f gradientColor) {
        glDisable(GL_TEXTURE_2D);
        float half = ScaleGui.get(250f);
        float half1 = ScaleGui.get(250f);
        float half2 = ScaleGui.get(350f);
        float bg = 18 / 255f;
        renderCuttedRectBack(x1, y1, x2, y2, cutSize, half, half, bg, bg, bg, gradientColor.w, bg, bg, bg, gradientColor.w);
        renderCuttedRectBack(x1, y1, x2, y2, cutSize, half1, half2, 0f, 0f, 0f, 0f, gradientColor.x, gradientColor.y, gradientColor.z, gradientColor.w);
        glEnable(GL_TEXTURE_2D);
    }

    public static void renderBuildConfirmPopup(float posX, float posY, float posX2, float posY2, float cutSize, float r, float g, float b) {
        glDisable(GL_TEXTURE_2D);
        posX = ScaleGui.getCenterX(posX);
        posX2 = ScaleGui.getCenterX(posX2);
        posY = ScaleGui.getCenterY(posY);
        posY2 = ScaleGui.getCenterY(posY2);
        cutSize = ScaleGui.get(cutSize);
        glShadeModel(GL_SMOOTH);
        float halfY1 = posY + (posY2 - posY) / 1.3f;
        tessellator.startDrawing(GL_TRIANGLES);

        tessellator.setColorRGBA_F(r, g, b, 1.2f);
        tessellator.addVertex(posX2, posY, 0);
        tessellator.addVertex(posX + cutSize, posY, 0);
        tessellator.setColorRGBA_F(18 / 255f, 19 / 255f, 18 / 255f, 1.2f);
        tessellator.addVertex(posX2, halfY1, 0);

        tessellator.setColorRGBA_F(r, g, b, 1.2f);
        tessellator.addVertex(posX, posY + cutSize, 0);
        tessellator.setColorRGBA_F(18 / 255f, 19 / 255f, 18 / 255f, 1.2f);
        tessellator.addVertex(posX2, halfY1, 0);
        tessellator.setColorRGBA_F(r, g, b, 1.2f);
        tessellator.addVertex(posX + cutSize, posY, 0);

        tessellator.addVertex(posX, posY + cutSize, 0);
        tessellator.setColorRGBA_F(18 / 255f, 19 / 255f, 18 / 255f, 1.2f);
        tessellator.addVertex(posX, halfY1, 0);
        tessellator.addVertex(posX2, halfY1, 0);

        tessellator.addVertex(posX2, halfY1, 0);
        tessellator.addVertex(posX, halfY1, 0);
        tessellator.addVertex(posX2, posY2 - cutSize, 0);

        tessellator.addVertex(posX, halfY1, 0);
        tessellator.addVertex(posX2 - cutSize, posY2, 0);
        tessellator.addVertex(posX2, posY2 - cutSize, 0);

        tessellator.addVertex(posX, halfY1, 0);
        tessellator.addVertex(posX, posY2, 0);
        tessellator.addVertex(posX2 - cutSize, posY2, 0);

        tessellator.draw();
        glShadeModel(GL_FLAT);
        glEnable(GL_TEXTURE_2D);
    }

    public static void renderConfirmPopup(float posX, float posY, float posX2, float posY2, float cutSize) {
        glDisable(GL_TEXTURE_2D);
        posX = ScaleGui.getCenterX(posX);
        posX2 = ScaleGui.getCenterX(posX2);
        posY = ScaleGui.getCenterY(posY);
        posY2 = ScaleGui.getCenterY(posY2);
        cutSize = ScaleGui.get(cutSize);
        glShadeModel(GL_SMOOTH);
        float halfY1 = posY + (posY2 - posY) / 1.3f;
        tessellator.startDrawing(GL_TRIANGLES);

        float colorX1 = 60 / 255f;
        float colorY1 = 14 / 255f;
        float colorZ1 = 19 / 255f;
        float colorA1 = 1.2f;
        float colorXZ2 = 18 / 255f;

        tessellator.setColorRGBA_F(colorX1, colorY1, colorZ1, colorA1);
        tessellator.addVertex(posX2, posY, 0);
        tessellator.addVertex(posX + cutSize, posY, 0);
        tessellator.setColorRGBA_F(colorXZ2, colorZ1, colorXZ2, colorA1);
        tessellator.addVertex(posX2, halfY1, 0);

        tessellator.setColorRGBA_F(colorX1, colorY1, colorZ1, colorA1);
        tessellator.addVertex(posX, posY + cutSize, 0);
        tessellator.setColorRGBA_F(colorXZ2, colorZ1, colorXZ2, colorA1);

        tessellator.addVertex(posX2, halfY1, 0);
        tessellator.setColorRGBA_F(colorX1, colorY1, colorZ1, colorA1);
        tessellator.addVertex(posX + cutSize, posY, 0);

        tessellator.addVertex(posX, posY + cutSize, 0);
        tessellator.setColorRGBA_F(colorXZ2, colorZ1, colorXZ2, colorA1);
        tessellator.addVertex(posX, halfY1, 0);
        tessellator.addVertex(posX2, halfY1, 0);

        tessellator.addVertex(posX2, halfY1, 0);
        tessellator.addVertex(posX, halfY1, 0);
        tessellator.setColorRGBA_F(colorXZ2, colorZ1, colorXZ2, colorA1);
        tessellator.addVertex(posX2, posY2 - cutSize, 0);

        tessellator.addVertex(posX, halfY1, 0);
        tessellator.setColorRGBA_F(colorXZ2, colorZ1, colorXZ2, colorA1);
        tessellator.addVertex(posX2 - cutSize, posY2, 0);
        tessellator.addVertex(posX2, posY2 - cutSize, 0);

        tessellator.addVertex(posX, halfY1, 0);
        tessellator.setColorRGBA_F(colorXZ2, colorZ1, colorXZ2, colorA1);
        tessellator.addVertex(posX, posY2, 0);
        tessellator.addVertex(posX2 - cutSize, posY2, 0);

        tessellator.draw();
        glShadeModel(GL_FLAT);
        glEnable(GL_TEXTURE_2D);
    }

    public static void renderToolTipSkill(float posX, float posY, float posX2, float posY2, float cutSize) {
        float cutX = cutSize;
        float cutY = cutSize;
        GL11.glShadeModel(GL11.GL_SMOOTH);
        tessellator.startDrawing(GL_TRIANGLES);
        float botColor = 0.0f;
        float upColor = 0.10f;
        float halfY1 = (posY2 - posY) / 3f;
        float halfY2 = (posY2 - posY) / 2.5f;
        tessellator.setColorRGBA_F(upColor, upColor, upColor, 0.95f);
        tessellator.addVertex(posX, posY, 0);
        tessellator.addVertex(posX2, posY + cutY, 0);
        tessellator.addVertex(posX2 - cutX, posY, 0);

        tessellator.addVertex(posX, posY, 0);
        tessellator.setColorRGBA_F(botColor, botColor, botColor, 0.95f);
        tessellator.addVertex(posX2, posY + halfY2, 0);
        tessellator.setColorRGBA_F(upColor, upColor, upColor, 0.95f);
        tessellator.addVertex(posX2, posY + cutY, 0);

        tessellator.addVertex(posX, posY, 0);
        tessellator.setColorRGBA_F(botColor, botColor, botColor, 0.95f);
        tessellator.addVertex(posX, posY + halfY1, 0);
        tessellator.addVertex(posX2, posY + halfY2, 0);

        tessellator.addVertex(posX, posY + halfY1, 0);
        tessellator.addVertex(posX2, posY2, 0);
        tessellator.addVertex(posX2, posY + halfY2, 0);

        tessellator.addVertex(posX, posY + halfY1, 0);
        tessellator.addVertex(posX + cutX, posY2, 0);
        tessellator.addVertex(posX2, posY2, 0);

        tessellator.addVertex(posX, posY + halfY1, 0);
        tessellator.addVertex(posX, posY2 - cutY, 0);
        tessellator.addVertex(posX + cutX, posY2, 0);
        tessellator.draw();
        GL11.glShadeModel(GL_FLAT);
    }

    public static void renderTooltipBuild(float posX, float posY, float posX2, float posY2, float cutSize) {
        glDisable(GL_TEXTURE_2D);
        glShadeModel(GL_SMOOTH);
        float halfY1 = (posY2 - posY) / 3.7f;
        float halfY2 = (posY2 - posY) / 2.4f;
        tessellator.startDrawing(GL_TRIANGLES);

        tessellator.setColorRGBA_F(65 / 255f, 65 / 255f, 65 / 255f, 0.95f);
        tessellator.addVertex(posX2, posY, 0);
        tessellator.addVertex(posX + cutSize, posY, 0);
        tessellator.setColorRGBA_F(18 / 255f, 19 / 255f, 18 / 255f, 0.95f);
        tessellator.addVertex(posX2, posY + halfY2, 0);

        tessellator.setColorRGBA_F(65 / 255f, 65 / 255f, 65 / 255f, 0.95f);
        tessellator.addVertex(posX, posY + cutSize, 0);
        tessellator.setColorRGBA_F(18 / 255f, 19 / 255f, 18 / 255f, 0.95f);
        tessellator.addVertex(posX2, posY + halfY2, 0);
        tessellator.setColorRGBA_F(65 / 255f, 65 / 255f, 65 / 255f, 0.95f);
        tessellator.addVertex(posX + cutSize, posY, 0);

        tessellator.addVertex(posX, posY + cutSize, 0);
        tessellator.setColorRGBA_F(18 / 255f, 19 / 255f, 18 / 255f, 0.95f);
        tessellator.addVertex(posX, posY + halfY1, 0);
        tessellator.addVertex(posX2, posY + halfY2, 0);

        tessellator.addVertex(posX2, posY + halfY2, 0);
        tessellator.addVertex(posX, posY + halfY1, 0);
        tessellator.setColorRGBA_F(18 / 255f, 19 / 255f, 18 / 255f, 0.95f);
        tessellator.addVertex(posX2, posY2 - cutSize, 0);

        tessellator.addVertex(posX, posY + halfY1, 0);
        tessellator.setColorRGBA_F(18 / 255f, 19 / 255f, 18 / 255f, 0.95f);
        tessellator.addVertex(posX2 - cutSize, posY2, 0);
        tessellator.addVertex(posX2, posY2 - cutSize, 0);

        tessellator.addVertex(posX, posY + halfY1, 0);
        tessellator.setColorRGBA_F(18 / 255f, 19 / 255f, 18 / 255f, 0.95f);
        tessellator.addVertex(posX, posY2, 0);
        tessellator.addVertex(posX2 - cutSize, posY2, 0);

        tessellator.draw();
        glShadeModel(GL_FLAT);
        glEnable(GL_TEXTURE_2D);
    }

    public static void renderToolTipSkillType(float posX, float posY, float posX2, float posY2, float cutSize) {
        tessellator.startDrawing(GL_TRIANGLES);
        tessellator.addVertex(posX + cutSize, posY, 0);
        tessellator.addVertex(posX2, posY2 - cutSize, 0);
        tessellator.addVertex(posX2, posY, 0);
        tessellator.addVertex(posX + cutSize, posY, 0);
        tessellator.addVertex(posX2 - cutSize, posY2, 0);
        tessellator.addVertex(posX2, posY2 - cutSize, 0);
        tessellator.addVertex(posX, posY + cutSize, 0);
        tessellator.addVertex(posX2 - cutSize, posY2, 0);
        tessellator.addVertex(posX + cutSize, posY, 0);
        tessellator.addVertex(posX, posY + cutSize, 0);
        tessellator.addVertex(posX, posY2, 0);
        tessellator.addVertex(posX2 - cutSize, posY2, 0);
        tessellator.draw();
    }

    public static void drawString(FontType fontType, String string, float x, float y, float scale, int color) {
        drawStringNoScale(fontType.getFontContainer(), string, ScaleGui.get(x), ScaleGui.get(y), ScaleGui.get(scale), color);
    }
    public static void drawStringNoScaleGui(FontType fontType, String string, float x, float y, float scale, int color) {
        drawStringNoScale(fontType.getFontContainer(), string, x, y, scale, color);
    }
    public static void drawCenteredString(FontType fontType, String string, float x, float y, float scale, int color) {
        FontContainer fontContainer = fontType.getFontContainer();
        scale = ScaleGui.get(scale);
        drawStringNoScale(fontContainer, string, ScaleGui.get(x) - fontContainer.width(string) * scale / 2f, ScaleGui.get(y), scale, color);
    }

    public static void drawStringNoXYScale(FontType fontType, String string, float x, float y, float scale, int color) {
        scale = ScaleGui.get(scale);
        drawStringNoScale(fontType.getFontContainer(), string, x, y, scale, color);
    }

    public static void drawRightStringNoXYScale(FontType fontType, String string, float x, float y, float scale, int color) {
        FontContainer fontContainer = fontType.getFontContainer();
        scale = ScaleGui.get(scale);
        drawStringNoScale(fontContainer, string, x - fontContainer.width(string) * scale, y, scale, color);
    }

    public static void drawCenteredStringNoXYScale(FontType fontType, String string, float x, float y, float scale, int color) {
        FontContainer fontContainer = fontType.getFontContainer();
        scale = ScaleGui.get(scale);
        drawStringNoScale(fontContainer, string, x - fontContainer.width(string) * scale / 2f, y, scale, color);
    }

    public static void drawCenteredStringCenterX(FontType fontType, String string, float x, float y, float scale, int color) {
        FontContainer fontContainer = fontType.getFontContainer();
        scale = ScaleGui.get(scale);
        drawStringNoScale(fontContainer, string, ScaleGui.getCenterX(x) - fontContainer.width(string) * scale / 2f, ScaleGui.get(y), scale, color);
    }

    public static void drawCenteredStringCenter(FontType fontType, String string, float x, float y, float scale, int color) {
        FontContainer fontContainer = fontType.getFontContainer();
        scale = ScaleGui.get(scale);
        drawStringNoScale(fontContainer, string, ScaleGui.getCenterX(x) - fontContainer.width(string) * scale / 2f, ScaleGui.getCenterY(y), scale, color);
    }

    public static void drawStringCenter(FontType fontType, String string, float x, float y, float scale, int color) {
        drawStringNoScale(fontType.getFontContainer(), string, ScaleGui.getCenterX(x), ScaleGui.getCenterY(y), ScaleGui.get(scale), color);
    }

    public static void drawStringCenterX(FontType fontType, String string, float x, float y, float scale, int color) {
        drawStringNoScale(fontType.getFontContainer(), string, ScaleGui.getCenterX(x), ScaleGui.get(y), ScaleGui.get(scale), color);
    }

    public static void drawStringCenterYRight(FontType fontType, String string, float x, float y, float scale, int color) {
        drawStringNoScale(fontType.getFontContainer(), string, ScaleGui.getRight(x), ScaleGui.getCenterY(y), ScaleGui.get(scale), color);
    }

    public static void drawRightStringCenter(FontType fontType, String string, float x, float y, float scale, int color) {
        FontContainer fontContainer = fontType.getFontContainer();
        scale = ScaleGui.get(scale);
        drawStringNoScale(fontContainer, string, ScaleGui.getCenterX(x) - fontContainer.width(string) * scale, ScaleGui.getCenterY(y), scale, color);
    }

    public static void drawRightString(FontType fontType, String string, float x, float y, float scale, int color) {
        FontContainer fontContainer = fontType.getFontContainer();
        scale = ScaleGui.get(scale);
        drawStringNoScale(fontContainer, string, ScaleGui.get(x) - fontContainer.width(string) * scale, ScaleGui.get(y), scale, color);
    }

    public static void drawStringCenterXBot(FontType fontType, String string, float x, float y, float scale, int color) {
        drawStringNoScale(fontType.getFontContainer(), string, ScaleGui.getCenterX(x), ScaleGui.getBot(y), ScaleGui.get(scale), color);
    }

    public static void drawRightStringCenterXBot(FontType fontType, String string, float x, float y, float scale, int color) {
        FontContainer fontContainer = fontType.getFontContainer();
        scale = ScaleGui.get(scale);
        drawStringNoScale(fontContainer, string, ScaleGui.getCenterX(x) - fontContainer.width(string) * scale, ScaleGui.getBot(y), scale, color);
    }

    public static void drawRightStringRightBot(FontType fontType, String string, float x, float y, float scale, int color) {
        FontContainer fontContainer = fontType.getFontContainer();
        scale = ScaleGui.get(scale);
        drawStringNoScale(fontContainer, string, ScaleGui.getRight(x) - fontContainer.width(string) * scale, ScaleGui.getBot(y), scale, color);
    }

    public static float drawSplittedStringCenter(FontType font, String text, float x, float y, float scale, float width, float heightLimit, int color, EnumStringRenderType renderType) {
        return drawSplittedString(font, text, ScaleGui.getCenterX(x), ScaleGui.getCenterY(y), ScaleGui.get(scale), width, heightLimit, color, renderType);
    }

    public static float drawSplittedStringRightBot(FontType font, String text, float x, float y, float scale, float width, float heightLimit, int color, EnumStringRenderType renderType) {
        return drawSplittedString(font, text, ScaleGui.getRight(x), ScaleGui.getBot(y), ScaleGui.get(scale), width, heightLimit, color, renderType);
    }

    public static float drawSplittedStringCenterXBot(FontType font, String text, float x, float y, float scale, float width, float heightLimit, int color) {
        return drawSplittedString(font, text, ScaleGui.getCenterX(x), ScaleGui.getBot(y), ScaleGui.get(scale), width, heightLimit, color, EnumStringRenderType.DEFAULT);
    }

    public static float drawSplittedRightStringCenterXBot(FontType font, String text, float x, float y, float scale, float width, float heightLimit, int color) {
        return drawSplittedString(font, text, ScaleGui.getCenterX(x), ScaleGui.getBot(y), ScaleGui.get(scale), width, heightLimit, color, EnumStringRenderType.RIGHT);
    }

    public static float drawSplittedStringNoScale(FontType font, String text, float x, float y, float scale, float width, float heightLimit, int color, EnumStringRenderType type) {
        return drawSplittedString(font, text, x, y, ScaleGui.get(scale), width, heightLimit, color, type);
    }

    private static final List<String> tempList = new ArrayList<>();
    private static final List<String> tempSplitted = new ArrayList<>();

    public static float drawSplittedString(FontType font, String text, float x, float y, float scale, float width, float heightLimit, int color, EnumStringRenderType type) {
        if (text == null) return 0;
        FontContainer fontContainer = font.getFontContainer();
        StringCache textFont = fontContainer.getTextFont();
        text = text.replaceAll(String.valueOf((char) 160), " ");
        tempList.clear();

        String defaultColor = "";
        String preColor = defaultColor;

        int offset = 0;
        while (text.contains("\n")) {
            int index = text.indexOf("\n");
            String temp = text.substring(offset, index);
            int lastColorIndex = temp.lastIndexOf("§");
            tempList.add(preColor + temp);
            if (lastColorIndex != -1) {
                preColor = temp.substring(lastColorIndex, lastColorIndex + 2);
            }
            text = preColor + text.replaceFirst("\n", "");
            offset = index;
        }
        tempList.add(preColor + text.substring(offset));

        tempSplitted.clear();
        preColor = defaultColor;
        for (String s : tempList) {
            if (s.length() == 0) {
                tempSplitted.add("");
            } else {
                String string = s;
                while (string.length() > 0) {
                    String temp = textFont.trimStringToWidthSaveWords(string, width / scale, false);
                    tempSplitted.add(preColor + temp);
                    int lastColorIndex = temp.lastIndexOf("§");
                    if (lastColorIndex != -1) {
                        preColor = temp.substring(lastColorIndex, lastColorIndex + 2);
                    }
                    string = string.replace(temp, "");
                }
            }
        }

        int height = 0;
        for (String s : tempSplitted) {
            drawStringNoScale(fontContainer, s, x - (type == EnumStringRenderType.DEFAULT ? 0 : type == EnumStringRenderType.RIGHT ?
                    fontContainer.width(s) * scale : fontContainer.width(s) * scale / 2f), y + height, scale, color);
            height += fontContainer.height() / 1.25f * scale;
            if (heightLimit != -1 && height >= heightLimit) {
                return height;
            }
        }

        return height;
    }

    public static void drawStringNoScale(FontContainer fontContainer, String string, float x, float y, float scale, int color) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0);
        GL11.glScalef(scale, scale, 1.0f);
        fontContainer.drawString(string, 0, 0, color);
        GL11.glPopMatrix();
    }

    public static void clearMaskBuffer(float x, float y, float width, float height) {
        glDisable(GL_TEXTURE_2D);
        glColor4f(1f, 1f, 1f, 1f);
        glBlendFuncSeparate(GL_ZERO, GL_ONE, GL_ZERO, GL_ZERO);
        drawRect(x, y, width, height);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_TEXTURE_2D);
    }



    public static void drawRectXY(double x1, double y1, double x2, double y2) {
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x1, y2, 0.0, 0.0, 1.0);
        tessellator.addVertexWithUV(x2, y2, 0.0, 1.0, 1.0);
        tessellator.addVertexWithUV(x2, y1, 0.0, 1.0, 0.0);
        tessellator.addVertexWithUV(x1, y1, 0.0, 0.0, 0.0);
        tessellator.draw();
    }

    public static void drawRect(double x, double y, double width, double height, float r, float g, float b, float a) {
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(r, g, b, a);
        tessellator.addVertexWithUV(x, y + height, 0.0, 0.0, 1.0);
        tessellator.addVertexWithUV(x + width, y + height, 0.0, 1.0, 1.0);
        tessellator.addVertexWithUV(x + width, y, 0.0, 1.0, 0.0);
        tessellator.addVertexWithUV(x, y, 0.0, 0.0, 0.0);
        tessellator.draw();
    }

    public static void drawRect(double x, double y, double width, double height) {
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + height, 0.0, 0.0, 1.0);
        tessellator.addVertexWithUV(x + width, y + height, 0.0, 1.0, 1.0);
        tessellator.addVertexWithUV(x + width, y, 0.0, 1.0, 0.0);
        tessellator.addVertexWithUV(x, y, 0.0, 0.0, 0.0);
        tessellator.draw();
    }


    public static void drawRect(double x, double y, double x1, double y1, double x2, double y2, double x3, double y3, float r, float g, float b, float a) {
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F(r, g, b, a);
        tessellator.addVertexWithUV(x, y, 0.0, 0.0, 1.0);
        tessellator.addVertexWithUV(x1, y1, 0.0, 1.0, 1.0);
        tessellator.addVertexWithUV(x2, y2, 0.0, 1.0, 0.0);
        tessellator.addVertexWithUV(x3, y3, 0.0, 0.0, 0.0);
        tessellator.draw();
    }

    public static void drawRecLines(float x, float y, float width, float height) {
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x + width, y);
        GL11.glVertex2f(x + width, y + height);
        GL11.glVertex2f(x, y + height);
        GL11.glVertex2f(x, y);
        GL11.glEnd();
    }

    public static void drawPreAlpha(float x, float y, float width, float height, float fontScale) {
        float rectX1 = ScaleGui.getCenterX(x);
        float rectY1 = ScaleGui.getCenterY(y);
        float rectX2 = ScaleGui.getCenterX(x + width);
        float rectY2 = ScaleGui.getCenterY(y + height);
        float halfY1 = (rectY2 - rectY1) / 2f;
        float halfY2 = (rectY2 - rectY1) / 2f;
        glDisable(GL_TEXTURE_2D);
        GuiDrawUtils.renderCuttedRect(rectX1, rectY1, rectX2, rectY2, ScaleGui.get(height / 3f), halfY1, halfY2, 0.9f, 0.5f, 0.2f, 0.5f, 0.9f, 0.5f, 0.2f, 0.5f);
        glEnable(GL_TEXTURE_2D);
        drawSplittedStringCenter(FontType.FUTURA_PT_MEDIUM, "Внимание! Это Pre-Alpha версия игры, предназначенная для тестирования. " +
                "Она не отображает финального качества продукта. Спасибо за понимание и поддержку!", x + height / 3f, y + height / 5f, fontScale, ScaleGui.get(width / 1.1f), -1, 0x000000, EnumStringRenderType.DEFAULT);
    }
}
