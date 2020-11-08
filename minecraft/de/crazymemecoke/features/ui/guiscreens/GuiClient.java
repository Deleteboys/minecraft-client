package de.crazymemecoke.features.ui.guiscreens;

import de.crazymemecoke.Client;
import de.crazymemecoke.features.ui.guiscreens.altmanager.GuiAltManager;
import de.crazymemecoke.manager.fontmanager.FontManager;
import de.crazymemecoke.utils.Wrapper;
import de.crazymemecoke.utils.render.RenderUtils;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GuiClient extends GuiScreen {
    public GuiScreen parent;

    FontManager fM = Client.main().fontMgr();

    private GuiScreen parentScreen;

    public GuiClient(GuiScreen parentScreen) {
        parent = parentScreen;
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        buttonList.add(new GuiButton(0, width / 2 - 120, height / 2 - 60, 120, 20, "AltManager"));
        buttonList.add(new GuiButton(4, width / 2 + 10, height / 2 - 60, 120, 20, "Credits"));
        buttonList.add(new GuiButton(2, width / 2 - 120, height / 2, 120, 20, "Changelog"));
        buttonList.add(new GuiButton(3, width / 2 + 10, height / 2, 120, 20, "Reset Client"));
        buttonList.add(new GuiButton(1, width / 2 - 120, height / 2 - 30, 250, 20, "Zurück"));
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            mc.displayGuiScreen(parentScreen);
        }
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 1: {
                mc.displayGuiScreen(parent);
                break;
            }
            case 0: {
                mc.displayGuiScreen(new GuiAltManager(this));
                break;
            }
            case 4: {
                mc.displayGuiScreen(new GuiCredits(this));
                break;
            }
            case 2: {
                Thread changelogThread = new Thread(() -> {
                    try {
                        Desktop desktop = Desktop.getDesktop();
                        URI uri = new URI(Client.main().getClientChangelog());
                        desktop.browse(uri);
                    } catch (URISyntaxException | IOException ignored) {
                    }
                });
                changelogThread.start();

                break;
            }
            case 3: {
                mc.displayGuiScreen(new GuiResetClient(this));
                break;
            }
        }
    }

    public void drawScreen(int posX, int posY, float f) {
        ScaledResolution sr = new ScaledResolution(mc);

        mc.getTextureManager().bindTexture(new ResourceLocation(Client.main().getClientBackground()));
        Gui.drawScaledCustomSizeModalRect(0, 0, 0.0F, 0.0F, sr.width(), sr.height(),
                width, height, sr.width(), sr.height());

        RenderUtils.drawRect(width / 2 - 130, height / 2 - 70, width / 2 + 140, height / 2 + 30, new Color(55, 55, 55, 150).getRGB());

        super.drawScreen(posX, posY, f);
    }
}