package Model.Command.LevelCommand;

import Model.Entity.Entity;
import Model.InfluenceEffect.InfluenceEffect;
import Model.Level.GameModel;
import Model.Level.Level;
import Model.Level.LevelMessenger;
import javafx.geometry.Point3D;

public class SendInfluenceEffectCommand extends LevelCommand {

    private InfluenceEffect influenceEffect;
    private Entity entity;

    public SendInfluenceEffectCommand(LevelMessenger levelMessenger) {
        super(levelMessenger);
    }

    @Override
    public void receiveGameModel(GameModel gameModel) {
        Level level = gameModel.getCurrentLevel();
        Point3D entityPoint = level.getEntityPoint(entity);
        if(entityPoint != null) {
            level.addInfluenceEffectTo(entityPoint, influenceEffect);
        }
    }

    public void sendCommandToLevel() {
        super.sendCommandToLevel(this);
    }

    @Override
    public void execute(Entity entity) {
        this.entity = entity;
    }

    public void setInfluenceEffect(InfluenceEffect influenceEffect) {
        this.influenceEffect = influenceEffect;
    }

    public InfluenceEffect getInfluenceEffect() {
        return influenceEffect;
    }

    public Entity getEntity() {
        return entity;
    }
}
