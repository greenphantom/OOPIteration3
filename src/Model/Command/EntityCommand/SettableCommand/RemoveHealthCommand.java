package Model.Command.EntityCommand.SettableCommand;

import Model.Command.Command;
import Model.Command.EntityCommand.SettableCommand.SettableCommand;
import Model.Entity.Entity;

public class RemoveHealthCommand implements SettableCommand {

    int damageAmount;

    public RemoveHealthCommand(int damageAmount) {
        this.damageAmount = damageAmount;
    }

    public void execute(Entity entity) {
        entity.decreaseHealth(damageAmount);
    }

    public void setAmount(int damageAmount) {
        this.damageAmount = damageAmount;
    }

    public int getAmount() {
        return damageAmount;
    }
}