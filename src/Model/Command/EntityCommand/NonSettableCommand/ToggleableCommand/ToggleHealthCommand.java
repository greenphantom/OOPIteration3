package Model.Command.EntityCommand.NonSettableCommand.ToggleableCommand;

import Model.Entity.Entity;

public class ToggleHealthCommand extends ToggleableCommand {

    int amount;

    public ToggleHealthCommand(int amount) {
        super();
        this.amount = amount;
    }

    public ToggleHealthCommand(int amount, boolean hasFired) {
        super(hasFired);
        this.amount = amount;
    }

    public void execute(Entity entity) {
        if(!hasFired()){
            entity.increaseMaxHealth(amount);
            toggleHasFired();
        } else{
            entity.decreaseMaxHealth(amount);
            toggleHasFired();
        }
    }
}