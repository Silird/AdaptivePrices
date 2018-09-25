package ru.SilirdCo.AdaptivePrices.View.impl.Util.TableCell;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import ru.SilirdCo.AdaptivePrices.Util.VarUtils;

public class BooleanTableCell<EntityMain> extends TableCell<EntityMain, Boolean> {

    private CheckBox checkBox;

    public BooleanTableCell() {
        super();

        this.checkBox = new CheckBox();
        this.checkBox.setDisable(true);
    }

    public static <EntityMain> Callback<TableColumn<EntityMain, Boolean>, TableCell<EntityMain, Boolean>>
    getCellFactory() {
        return e -> {
            BooleanTableCell<EntityMain> cell = new BooleanTableCell<>();
            cell.setAlignment(Pos.CENTER);
            return cell;
        };
    }

    @Override
    protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(null);
            setStyle("");
        }
        else {
            checkBox.setSelected(VarUtils.getBoolean(item));
            setGraphic(checkBox);
        }
    }
}
