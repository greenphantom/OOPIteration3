package Controller.ModelKeyAction;

import Model.MenuModel.MenuModel;
import javafx.scene.input.KeyCode;

public class ScrollUpKeyAction extends ModelKeyAction {

    private MenuModel menuModel;

    public ScrollUpKeyAction(KeyCode keyCode, MenuModel menuModel) {
        super(keyCode);
        this.menuModel = menuModel;
    }

    @Override
    public void handle(KeyCode incomingKey) {
        if(incomingKey == keyCode){
            menuModel.scrollUp();
        }
    }

    @Override
    public String getName() {
        return null;
    }
}
