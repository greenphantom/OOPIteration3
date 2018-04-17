package Model.Command.EntityCommand.SettableCommand.ToggleableCommand;

import Model.Command.Command;
import Model.Command.EntityCommand.NonSettableCommand.ToggleableCommand.ToggleableCommand;
import Model.Command.EntityCommand.SettableCommand.SettableCommand;
import Model.Entity.Entity;

public class ToggleSneaking extends ToggleableCommand implements SettableCommand {

    int stealthAmount;

    public ToggleSneaking(int amount) {
        super();
        this.stealthAmount = amount;
    }

    public ToggleSneaking(int amount, boolean hasFired) {
        super(hasFired);
        this.stealthAmount = amount;
    }

    @Override
    public void execute(Entity entity) {
        if(!hasFired()) {
            entity.decreaseSpeed(stealthAmount);
            entity.decreaseNoiseLevel(stealthAmount);
            toggleHasFired();
        } else {
            entity.increaseSpeed(stealthAmount);
            entity.increaseNoiseLevel(stealthAmount);
            toggleHasFired();
        }
    }

    public void setAmount(int amount) {
        this.stealthAmount = amount;
    }

    public int getAmount() {
        return stealthAmount;
    }
}
