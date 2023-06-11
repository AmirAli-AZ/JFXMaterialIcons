package com.amirali.jfxmaterialicons;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.css.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaterialIcon extends Text {

    public MaterialIcon() {
        super();
        init();
    }

    public MaterialIcon(String icon) {
        this();
        setIcon(icon);
    }

    public MaterialIcon(String icon, Style iconStyle) {
        this();
        setIcon(icon);
        setIconStyle(iconStyle);
    }

    public MaterialIcon(String icon, Style iconStyle, double iconSize) {
        this();
        setIcon(icon);
        setIconStyle(iconStyle);
        setIconSize(iconSize);
    }

    public MaterialIcon(String icon, Style iconStyle, double iconSize, Paint fill) {
        this();
        setIcon(icon);
        setIconStyle(iconStyle);
        setIconSize(iconSize);
        setFill(fill);
    }

    private void init() {
        getStyleClass().add("material-icon");

        setTextAlignment(TextAlignment.CENTER);
        setFont(Utils.getFont(getIconStyle(), getFont().getSize()));

        textProperty().bind(
                Bindings.createStringBinding(() -> {
                    var codepoint = Utils.getUnicode(getIcon(), getIconStyle());
                    if (codepoint == null)
                        return null;
                    return Character.toString(codepoint.unicode());
                }, iconProperty(), iconStyleProperty())
        );

        iconStyleProperty().addListener((observable, oldValue, newValue) -> setFont(Utils.getFont(newValue, getFont().getSize())));
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

    public void setIcon(String icon) {
        iconProperty.set(icon);
    }

    public String getIcon() {
        return iconProperty.get();
    }

    public ReadOnlyStringProperty iconProperty() {
        return iconProperty;
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

    public void setIconSize(double size) {
        setFont(Utils.getFont(getIconStyle(), size));
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        var metaData = new ArrayList<>(super.getCssMetaData());
        Collections.addAll(metaData, iconCssMetaData, iconStyleCssMetaData);
        return metaData;
    }
}
