package com.amirali.jfxmaterialicons;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Skin;
import javafx.scene.control.SkinBase;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MaterialIconSkin extends SkinBase<MaterialIcon> implements Skin<MaterialIcon> {

    private Text fontIcon;

    private ChangeListener<Style> iconStyleListener;

    private ChangeListener<Number> iconSizeListener;

    private final MaterialIcon control;

    protected MaterialIconSkin(MaterialIcon control) {
        super(control);
        this.control = control;

        init();
    }

    private void init() {
        iconSizeListener = (observable, oldValue, newValue) -> fontIcon.setFont(Utils.getFont(control.getIconStyle(), (Double) newValue));
        iconStyleListener = (observable, oldValue, newValue) -> fontIcon.setFont(Utils.getFont(newValue, control.getIconSize()));

        fontIcon = new Text();
        fontIcon.setTextAlignment(TextAlignment.CENTER);
        fontIcon.setFont(Utils.getFont(control.getIconStyle(), control.getIconSize()));

        fontIcon.textProperty().bind(
                Bindings.createStringBinding(() -> {
                    var codepoint = Utils.getUnicode(control.getIcon(), control.getIconStyle());
                    if (codepoint == null)
                        return null;
                    return Character.toString(codepoint.unicode());
                }, control.iconProperty(), control.iconStyleProperty())
        );
        fontIcon.fillProperty().bind(control.iconColorProperty());

        control.iconSizeProperty().addListener(iconSizeListener);
        control.iconStyleProperty().addListener(iconStyleListener);

        getChildren().add(fontIcon);
    }

    @Override
    public void dispose() {
        super.dispose();

        fontIcon.textProperty().unbind();
        fontIcon.fillProperty().unbind();
        fontIcon = null;

        control.iconSizeProperty().removeListener(iconSizeListener);
        control.iconStyleProperty().removeListener(iconStyleListener);

        iconSizeListener = null;
        iconStyleListener = null;
    }
}
