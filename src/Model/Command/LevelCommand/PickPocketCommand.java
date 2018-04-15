package Model.Command.LevelCommand;

import Model.Entity.Entity;
import Model.Level.GameModel;
import Model.Level.Level;
import Model.Level.LevelMessenger;

public class PickPocketCommand extends LevelCommand {

    private Entity entity;

    public PickPocketCommand(LevelMessenger levelMessenger) {
        super(levelMessenger);
    }

    public void recieveLevel(Level level) {

    }

    @Override
    public void execute(Entity entity) {
        this.entity = entity;
        sendSelfToLevel();
    }

}
