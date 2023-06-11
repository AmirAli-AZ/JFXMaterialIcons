## JFXMaterialIcons

JFXMaterialIcons makes easier to use Google material font icons in javafx.

### Code example

```java
var icon = new MaterialIcon("code");
icon.setIconStyle(Style.ROUND);
icon.setIconSize(150);
icon.setFill(Color.GRAY);
```

and you can add it as a node

### Css styling

```css
.material-icon {
    -material-icon: "code";
    -material-icon-style: round;
    -fx-font-size: 150px;
    -fx-fill: gray;
}
```