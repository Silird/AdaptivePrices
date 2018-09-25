package ru.SilirdCo.AdaptivePrices.View.impl.Util.TableCell;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import ru.SilirdCo.AdaptivePrices.Core.impl.Entities.DB.Position;
import ru.SilirdCo.AdaptivePrices.View.impl.Util.StructureGUI;

public class UseTableCell extends TableCell<Position, Boolean> {

    public UseTableCell() {
        super();
    }

    public static Callback<TableColumn<Position, Boolean>, TableCell<Position, Boolean>>
    getCellFactory() {
        return e -> {
            UseTableCell cell = new UseTableCell();
            cell.setAlignment(Pos.CENTER);
            return cell;
        };
    }

    @Override
    protected void updateItem(Boolean item, boolean empty) {
        super.updateItem(item, empty);


        if (item == null) {
            setGraphic(StructureGUI.defaultIcon);
        }
        else {
            setGraphic(StructureGUI.getIcon(("dash" + item)));
        }
    }
}
