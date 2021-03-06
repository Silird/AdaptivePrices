package ru.SilirdCo.AdaptivePrices.View.impl.Util.TableCell;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.StructureGUI;

public class IdTableCell extends TableCell<Position, Integer> {

    public IdTableCell() {
        super();
    }

    public static Callback<TableColumn<Position, Integer>, TableCell<Position, Integer>>
    getCellFactory() {
        return e -> {
            IdTableCell cell = new IdTableCell();
            cell.setAlignment(Pos.CENTER);
            return cell;
        };
    }

    @Override
    protected void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);


        if (item == null) {
            setGraphic(StructureGUI.defaultIcon);
        }
        else {
            setGraphic(StructureGUI.getIcon(("dash")));
        }
    }
}
