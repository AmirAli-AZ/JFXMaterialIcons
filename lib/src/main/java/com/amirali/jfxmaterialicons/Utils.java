package com.amirali.jfxmaterialicons;

import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public final class Utils {

    public static Codepoint getUnicode(String icon, Style style) {
        if (icon == null || style == null)
            return null;
        var codepoint = getCodePointPath(style);

        try(var bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Utils.class.getResourceAsStream(codepoint))))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                var split = line.split("\\s+");
                if (icon.equalsIgnoreCase(split[0]))
                    return new Codepoint(split[0], ((char) Integer.parseInt(split[1], 16)));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Codepoint> getIcons(Style style) {
        if (style == null)
            return List.of();

        var list = new ArrayList<Codepoint>();

        try(var bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Utils.class.getResourceAsStream(getCodePointPath(style)))))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                var split = line.split("\\s+");
                var codepoint = new Codepoint(split[0], ((char) Integer.parseInt(split[1], 16)));
                list.add(codepoint);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    private static String getCodePointPath(Style style) {
        return switch (style) {
            case ROUND -> "/MaterialIconsRound-Regular.codepoints";
            case SHARP -> "/MaterialIconsSharp-Regular.codepoints";
            case OUTLINED -> "/MaterialIconsOutlined-Regular.codepoints";

            default -> "/MaterialIcons-Regular.codepoints";
        };
    }

    public static Font getFont(Style style, double size) {
        return switch (style) {
            case ROUND -> Font.loadFont(Utils.class.getResourceAsStream("/MaterialIconsRound-Regular.otf"), size);
            case SHARP -> Font.loadFont(Utils.class.getResourceAsStream("/MaterialIconsSharp-Regular.otf"), size);
            case OUTLINED -> Font.loadFont(Utils.class.getResourceAsStream("/MaterialIconsOutlined-Regular.otf"), size);

            default -> Font.loadFont(Utils.class.getResourceAsStream("/MaterialIcons-Regular.ttf"), size);
        };
    }
}
