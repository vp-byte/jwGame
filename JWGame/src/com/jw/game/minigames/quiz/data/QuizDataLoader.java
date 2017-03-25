/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jw.game.minigames.quiz.data;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetKey;
import com.jme3.asset.AssetLoader;
import com.jme3.asset.AssetManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author vp-byte
 */
public class QuizDataLoader implements AssetLoader {

    private final AssetManager assetManager;

    public QuizDataLoader(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public List<String> load(String path) throws IOException {
        AssetKey<Object> key = new AssetKey<>(path);
        AssetInfo assetInfo = assetManager.locateAsset(key);
        return (List<String>) load(assetInfo);
    }

    @Override
    public Object load(AssetInfo assetInfo) throws IOException {
        Scanner scan = new Scanner(assetInfo.openStream());
        List<String> lines = new ArrayList<>();
        try {
            while (scan.hasNextLine()) {
                lines.add(scan.nextLine());
            }
        } finally {
            scan.close();
        }
        return lines;
    }
}
