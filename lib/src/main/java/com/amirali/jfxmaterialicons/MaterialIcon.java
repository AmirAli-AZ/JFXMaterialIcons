package com.amirali.jfxmaterialicons;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.css.*;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaterialIcon extends Control {

    public MaterialIcon() {
        getStyleClass().add("material-icon");
    }

    public MaterialIcon(String icon) {
        setIcon(icon);
        getStyleClass().add("material-icon");
    }

    private final CssMetaData<MaterialIcon, String> iconCssMetaData = new CssMetaData<>("-material-icon", StyleConverter.getStringConverter()) {

        @Override
        public boolean isSettable(MaterialIcon styleable) {
            return !styleable.iconProperty.isBound();
        }

        @Override
        public StyleableProperty<String> getStyleableProperty(MaterialIcon styleable) {
            return styleable.iconProperty;
        }
    };

    private final StyleableStringProperty iconProperty = new SimpleStyleableStringProperty(iconCssMetaData, this, "iconProperty");

    private final CssMetaData<MaterialIcon, Number> iconSizeCssMetaData = new CssMetaData<>("-material-icon-size", StyleConverter.getSizeConverter(), 30) {
        @Override
        public boolean isSettable(MaterialIcon styleable) {
            return !styleable.iconSizeProperty.isBound();
        }

        @Override
        public StyleableProperty<Number> getStyleableProperty(MaterialIcon styleable) {
            return styleable.iconSizeProperty;
        }
    };

    private final StyleableIntegerProperty iconSizeProperty = new SimpleStyleableIntegerProperty(iconSizeCssMetaData, this, "iconSizeProperty", 30);

    private final CssMetaData<MaterialIcon, Style> iconStyleCssMetaData = new CssMetaData<>("-material-icon-style", StyleConverter.getEnumConverter(Style.class), Style.REGULAR) {
        @Override
        public boolean isSettable(MaterialIcon styleable) {
            return !styleable.iconStyleProperty.isBound();
        }

        @Override
        public StyleableProperty<Style> getStyleableProperty(MaterialIcon styleable) {
            return styleable.iconStyleProperty;
        }
    };

    private final StyleableObjectProperty<Style> iconStyleProperty = new SimpleStyleableObjectProperty<>(iconStyleCssMetaData, this, "iconStyleProperty", Style.REGULAR);

    private final CssMetaData<MaterialIcon, Color> iconColorCssMetaData = new CssMetaData<>("-material-icon-color", StyleConverter.getColorConverter(), Color.BLACK) {
        @Override
        public boolean isSettable(MaterialIcon styleable) {
            return !styleable.iconStyleProperty.isBound();
        }

        @Override
        public StyleableProperty<Color> getStyleableProperty(MaterialIcon styleable) {
            return styleable.iconColorProperty;
        }
    };

    private final StyleableObjectProperty<Color> iconColorProperty = new SimpleStyleableObjectProperty<>(iconColorCssMetaData, this, "iconColorProperty", Color.BLACK);

    public void setIcon(String icon) {
        iconProperty.set(icon);
    }

    public String getIcon() {
        return iconProperty.get();
    }

    public ReadOnlyStringProperty iconProperty() {
        return iconProperty;
    }

    public void setIconSize(int size) {
        iconSizeProperty.set(size);
    }

    public int getIconSize() {
        return iconSizeProperty.get();
    }

    public ReadOnlyIntegerProperty iconSizeProperty() {
        return iconSizeProperty;
    }

    public void setIconStyle(Style style) {
        iconStyleProperty.set(style);
    }

    public Style getIconStyle() {
        return iconStyleProperty.get();
    }

    public ReadOnlyObjectProperty<Style> iconStyleProperty() {
        return iconStyleProperty;
    }

    public void setIconColor(Color color) {
        iconColorProperty.set(color);
    }

    public Color getIconColor() {
        return iconColorProperty.get();
    }

    public ReadOnlyObjectProperty<Color> iconColorProperty() {
        return iconColorProperty;
    }

    @Override
    protected List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        var metaData = new ArrayList<>(super.getControlCssMetaData());
        Collections.addAll(metaData, iconCssMetaData, iconSizeCssMetaData, iconStyleCssMetaData, iconColorCssMetaData);
        return metaData;
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new MaterialIconSkin(this);
    }
}
