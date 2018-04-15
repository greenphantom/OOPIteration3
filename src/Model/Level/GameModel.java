package Model.Level;

import Model.AI.AIController;
import Model.AI.AIState;
import Model.Command.GameModelCommand.GameModelCommand;
import Model.Command.LevelCommand.LevelCommand;
import Model.Entity.Entity;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameModel {

    private Level currentLevel;
    private List<Level> levels;
    private Map<Level, LevelMessenger> levelMessengers;
    private Entity player;
    private Map<Level, List<AIController>> aiMap;

    public void receiveGameModelCommand(GameModelCommand command) {
        command.receiveGameModel(this);
    }

    public void receiveLevelCommand(LevelCommand levelCommand) {
        levelCommand.receiveGameModel(this);
    }

    public Level getCurrentLevel(){
        return currentLevel;
    }

    public AIController getAIForEntity(Entity entity) {
        ArrayList<AIController> ais = (ArrayList)aiMap.get(currentLevel);
        for(int i = 0; i < ais.size(); ++i){
            if(ais.get(i).getEntity() == entity){
                return ais.get(i);
            }
        }
        try {
            throw new Exception("couldnt find ai for that entity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void moveEntity(Entity entity, Level sourceLevel, Level destinationLevel, Point3D destinationPoint) {
        sourceLevel.removeEntityFrom(entity);
        destinationLevel.addEntityTo(destinationPoint, entity);
    }

}
