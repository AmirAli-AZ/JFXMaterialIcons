package com.amirali.jfxmaterialicons;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.css.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.amirali.jfxmaterialicons.Utils.*;

public class MaterialIcon extends Text {

    static {
        for (Style style : Style.values())
            Utils.loadFont(style, 8);
    }

    private final StyleableStringProperty iconProperty = new SimpleStyleableStringProperty(StyleableProperties.ICON_CSS_META_DATA, this, "iconProperty");
    private final StyleableObjectProperty<Style> iconStyleProperty = new SimpleStyleableObjectProperty<>(StyleableProperties.ICON_STYLE_CSS_META_DATA, this, "iconStyleProperty", Style.REGULAR);
    private final StyleableIntegerProperty iconSizeProperty = new SimpleStyleableIntegerProperty(StyleableProperties.ICON_SIZE_CSS_META_DATA, this, "iconSizeProperty", 8);
    private final StyleableObjectProperty<Paint> iconColorProperty = new SimpleStyleableObjectProperty<>(StyleableProperties.ICON_COLOR_CSS_META_DATA, this, "iconColorProperty", Color.BLACK);

    public MaterialIcon() {
        super();
        init();
    }

    public MaterialIcon(String icon, Style iconStyle, int iconSize, Paint paint) {
        this();
        setIcon(icon);
        setIconStyle(iconStyle);
        setIconSize(iconSize);
        setIconColor(paint);
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return StyleableProperties.STYLEABLES;
    }

    private void init() {
        getStyleClass().add("material-icon");

        setTextAlignment(TextAlignment.CENTER);

        textProperty().bind(
                Bindings.createStringBinding(() -> {
                    var codepoint = getUnicode(getIcon(), getIconStyle());
                    setStyle(normalizeStyle(getStyle(), "-fx-font-family", "\"" + getFamily(getIconStyle()) + "\""));
                    if (codepoint == null)
                        return null;
                    return Character.toString(codepoint.unicode());
                }, iconProperty(), iconStyleProperty())
        );

        iconSizeProperty().addListener((observable, oldValue, newValue) -> {
            setFont(Font.font(getFamily(getIconStyle()), newValue.doubleValue()));
            setStyle(normalizeStyle(getStyle(), "-fx-font-size", newValue.intValue() + "px"));
        });

        fontProperty().addListener((observable, oldValue, newValue) -> {
            var size = ((int) newValue.getSize());
            if (size != getIconSize())
                setIconSize(size);
        });

        iconColorProperty.bindBidirectional(fillProperty());
    }

    private String normalizeStyle(String style, String key, String value) {
        int start = style.indexOf(key);
        if (start != -1) {
            int end = style.indexOf(";", start);
            end = end >= start ? end : style.length() - 1;
            style = style.substring(0, start) + style.substring(end + 1);
        }
        return style + key + ": " + value + ";";
    }

    public String getIcon() {
        return iconProperty.get();
    }

    public void setIcon(String icon) {
        iconProperty.set(icon);
    }

    public StringProperty iconProperty() {
        return iconProperty;
    }

    public Style getIconStyle() {
        return iconStyleProperty.get();
    }

    public void setIconStyle(Style style) {
        iconStyleProperty.set(style);
    }

    public ObjectProperty<Style> iconStyleProperty() {
        return iconStyleProperty;
    }

    public int getIconSize() {
        return iconSizeProperty.get();
    }

    public void setIconSize(int iconSize) {
        iconSizeProperty.set(iconSize);
    }

    public IntegerProperty iconSizeProperty() {
        return iconSizeProperty;
    }

    public ObjectProperty<Paint> iconColorProperty() {
        return iconColorProperty;
    }

    public Paint getIconColor() {
        return iconColorProperty.get();
    }

    public void setIconColor(Paint paint) {
        iconColorProperty.set(paint);
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return getClassCssMetaData();
    }

    private static class StyleableProperties {
        private static final CssMetaData<MaterialIcon, String> ICON_CSS_META_DATA = new CssMetaData<>("-material-icon", StyleConverter.getStringConverter()) {

            @Override
            public boolean isSettable(MaterialIcon styleable) {
                return !styleable.iconProperty.isBound();
            }

            @Override
            public StyleableProperty<String> getStyleableProperty(MaterialIcon styleable) {
                return styleable.iconProperty;
            }
        };

        private static final CssMetaData<MaterialIcon, Style> ICON_STYLE_CSS_META_DATA = new CssMetaData<>("-material-icon-style", StyleConverter.getEnumConverter(Style.class), Style.REGULAR) {
            @Override
            public boolean isSettable(MaterialIcon styleable) {
                return !styleable.iconStyleProperty.isBound();
            }

            @Override
            public StyleableProperty<Style> getStyleableProperty(MaterialIcon styleable) {
                return styleable.iconStyleProperty;
            }
        };

        private static final CssMetaData<MaterialIcon, Number> ICON_SIZE_CSS_META_DATA = new CssMetaData<MaterialIcon, Number>("-material-icon-size", StyleConverter.getSizeConverter(), 8) {
            @Override
            public boolean isSettable(MaterialIcon styleable) {
                return !styleable.iconSizeProperty.isBound();
            }

            @Override
            public StyleableProperty<Number> getStyleableProperty(MaterialIcon styleable) {
                return styleable.iconSizeProperty;
            }
        };

        private static final CssMetaData<MaterialIcon, Paint> ICON_COLOR_CSS_META_DATA = new CssMetaData<MaterialIcon, Paint>("-material-icon-color", StyleConverter.getPaintConverter(), Color.BLACK) {
            @Override
            public boolean isSettable(MaterialIcon styleable) {
                return !styleable.iconColorProperty.isBound();
            }

            @Override
            public StyleableProperty<Paint> getStyleableProperty(MaterialIcon styleable) {
                return styleable.iconColorProperty;
            }
        };

        private final static List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

        static {
            var styleables = new ArrayList<>(Text.getClassCssMetaData());
            styleables.add(ICON_CSS_META_DATA);
            styleables.add(ICON_STYLE_CSS_META_DATA);
            styleables.add(ICON_SIZE_CSS_META_DATA);
            styleables.add(ICON_COLOR_CSS_META_DATA);

            STYLEABLES = Collections.unmodifiableList(styleables);
        }

    }
}
