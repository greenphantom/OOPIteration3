package Model.Command.EntityCommand.SettableCommand;

import Controller.GameLoop;
import Controller.Visitor.Visitor;
import Model.Command.GameLoopCommand.GameLoopCommand;
import Model.Entity.Entity;
import Model.Entity.EntityAttributes.Orientation;
import Model.Level.GameModel;
import Model.Level.Level;
import Model.Level.LevelMessenger;
import javafx.geometry.Point3D;

public class BarterCommand extends GameLoopCommand implements SettableCommand {

    private Entity invokingEntity;
    private Entity receivingEntity;

    private int playerBarterStrength;

    public BarterCommand(LevelMessenger levelMessenger) {
        super(levelMessenger);
    }

    public void receiveGameLoop(GameLoop gameLoop) {
        gameLoop.openBarterWindow(invokingEntity, receivingEntity);
    }

    public void receiveGameModel(GameModel gameModel) {
        if(!gameModel.entityIsPlayer(invokingEntity)) {
            invokingEntity = null;
        }
    }

    public void receiveLevel(Level level) {
        Point3D invokerPoint = level.getEntityPoint(invokingEntity);

        Point3D receiverPoint = Orientation.getAdjacentPoint(invokerPoint, invokingEntity.getOrientation());

        receivingEntity = level.getEntityAtPoint(receiverPoint);
    }

    public void execute(Entity entity) {
        this.invokingEntity = entity;
        sendCommandToGameLoop();
    }

    public void setAmount(int amount) {
        this.playerBarterStrength = amount;
    }

    public int getAmount() {
        return playerBarterStrength;
    }

    public void accept(Visitor visitor) {
        visitor.visitBarterCommand(this);
    }
}
