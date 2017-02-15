package com.jw.game;

import com.jme3.app.SimpleApplication;
import com.jme3.system.AppSettings;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.prefs.BackingStoreException;

public class JWSettings {

    private SimpleApplication application;
    private AppSettings settings;
    private String settingsFile = "com.jw.JWGameSettings";

    public AppSettings getSettings() {
        return settings;
    }

    public void setSettings(AppSettings settings) {
        this.settings = settings;
    }

    public JWSettings(SimpleApplication application) {
        this.application = application;
    }

    public void loadDefaults() {
        this.application.setSettings(defaults());
    }

    public AppSettings defaults() {
        settings = new AppSettings(true);
        settings.setTitle("JWGame");

        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode[] modes = device.getDisplayModes();
        int i = 0;
        settings.setResolution(1024, 768);
        //settings.setResolution(modes[i].getWidth(), modes[i].getHeight());
        settings.setFrequency(modes[i].getRefreshRate());
        settings.setBitsPerPixel(modes[i].getBitDepth());
        //settings.setFullscreen(device.isFullScreenSupported());
        return settings;
    }

    public void applySettings(AppSettings settings) {
        this.settings = settings;
        application.setSettings(this.settings);
        application.restart();
    }

    /*
     On WINDOWS, the preferences are saved under the following registry key:
     HKEY_CURRENT_USER\Software\JavaSoft\Prefs\com\foo\MyCoolGame3

     On LINUX, the preferences are saved in an XML file under:
     $HOME/.java/.userPrefs/com/foo/MyCoolGame3

     On MAC OS X, the preferences are saved as XML file under:
     $HOME/Library/Preferences/com.foo.MyCoolGame3.plist
     */
    public void saveSettings() throws BackingStoreException {
        settings.save(settingsFile);
    }

    public void loadSettings() throws BackingStoreException {
        settings.load(settingsFile);
    }
}
