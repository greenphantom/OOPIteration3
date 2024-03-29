package Controller.ModelKeyAction;

import Model.Entity.Entity;
import javafx.scene.input.KeyCode;

public class AttackKeyAction extends ModelKeyAction {

    private Entity entity;

    public AttackKeyAction(KeyCode keyCode, Entity entity){
        super(keyCode);
        this.entity = entity;
    }

    @Override
    public void handle(KeyCode incomingKey) {
        if(incomingKey == keyCode){
            entity.attack();
            System.out.println("ATTACK");
        }
    }

    @Override
    public String getName() {
        return "attack";
    }
}
