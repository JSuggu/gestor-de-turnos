package com.example.gestordeturnos.graphic.service;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;

public class CheckBoxCellCustom<T> extends TableCell<T, Boolean> {
    private final CheckBox checkBox;

    public CheckBoxCellCustom() {
        this.checkBox = new CheckBox();
        this.checkBox.setOnAction(event -> {
            if (getItem() != null) {
                getTableView().edit(getIndex(), getTableColumn());
                commitEdit(checkBox.isSelected());
            }
        });
        setGraphic(checkBox);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        setAlignment(javafx.geometry.Pos.CENTER);
    }

    @Override
    protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setGraphic(null);
        } else {
            checkBox.setSelected(item);
            setGraphic(checkBox);
        }
    }
}
