package Model.Level;

import Model.Command.EntityCommand.NonSettableCommand.DialogCommand;
import Model.Entity.Entity;
import Model.InfluenceEffect.InfluenceEffect;
import Model.Utility.BidiMap;
import View.LevelView.InfluenceEffectView;
import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;

import java.util.*;

public class MovementHandler {
    private Map<Point3D,Terrain> terrainLocations;
    private Map<Point3D, Obstacle> obstacleLocations;
    private BidiMap<Point3D, Entity> entityLocations;
    private Map<Point3D, Mount> mountLocations;
    private Map<Point3D, InfluenceEffect> influenceEffectLocations;
    private Map<Point3D, River> riverLocations;

    private DialogCommand dialogCommand;

    public MovementHandler(Map<Point3D, Terrain> terrainLocations,
                           Map<Point3D, Obstacle> obstacleLocations,
                           BidiMap<Point3D, Entity> entityLocations,
                           Map<Point3D, Mount> mountLocations,
                           Map<Point3D, InfluenceEffect> influenceEffectLocations,
                           Map<Point3D, River> riverLocations) {

        this.terrainLocations = terrainLocations;
        this.obstacleLocations = obstacleLocations;
        this.entityLocations = entityLocations;
        this.mountLocations = mountLocations;
        this.influenceEffectLocations = influenceEffectLocations;
        this.riverLocations = riverLocations;
    }

    public void processMoves(){
        moveEntities();

        moveInfluenceEffects();
    }



    private void moveEntities() {
        for (Entity entity: entityLocations.getValueList()){//For each entry in the map
            Point3D entityPoint = entityLocations.getKeyFromValue(entity);

            if (entity.isMoving()) {
                Point3D contestedPoint = calculateMove(entityPoint, entity.getVelocity());

                if (!obstacleLocations.containsKey(contestedPoint) && entity.canMoveOnTerrain(terrainLocations.get(contestedPoint))){
                    if (entityLocations.hasKey(contestedPoint) && isAlive(entityLocations.getValueFromKey(contestedPoint))){
                        if(!riverLocations.containsKey(entityPoint)) { // dont start dialog if a river is forcing you onto another entity
                            dialogCommand.execute(entity);
                        }
                    } else {
                        //Update entity movement
                        entityLocations.removeByKey(entityPoint);
                        entityLocations.place(contestedPoint, entity);

                        if (entity.isMounted()) {
                            Mount mount = mountLocations.get(entityPoint);
                            mount.setOrientation(entity.getOrientation());

                            mountLocations.remove(entityPoint);
                            mountLocations.put(contestedPoint, mount);

                            mount.notifyObservers(contestedPoint);
                        }

                        if (mountLocations.containsKey(contestedPoint) && !entity.isMounted()) {
                            entity.mountVehicle(mountLocations.get(contestedPoint));
                        }

                        entity.notifyObservers(contestedPoint);
                    }
                }
                entity.decrementVelocity();
            }
        }
    }

    private boolean isAlive(Entity valueFromKey) {
        return !valueFromKey.isDead();
    }

    private void moveInfluenceEffects() {
        List<Point3D> influenceEffectPoints = new ArrayList<>(influenceEffectLocations.keySet());

        for(Point3D oldPoint : influenceEffectPoints) {

            InfluenceEffect influenceEffect = influenceEffectLocations.get(oldPoint); // Get current influence effect

            List<Point3D> nextEffectPoints = influenceEffect.nextMove(oldPoint); // Get list of points to move effect to
            influenceEffect.decreaseCommandAmount();

            InfluenceEffect newInfluenceEffect = influenceEffect.cloneInfluenceEffect();

            for (Point3D newPoint : nextEffectPoints) {
                influenceEffectLocations.put(newPoint, newInfluenceEffect); // put influence effect at its new position
            }

            if(influenceEffect.noMovesRemaining()) {
                influenceEffectLocations.remove(oldPoint);
            }
        }
    }

    private Point3D calculateMove(Point3D startingPoint, Vec3d velocity){
        double newX = startingPoint.getX();
        double newY = startingPoint.getY();
        double newZ = startingPoint.getZ();

        if (Math.abs(velocity.x) > 0){ newX = newX+(velocity.x/Math.abs(velocity.x)); }

        if (Math.abs(velocity.y) > 0) { newY = newY+(velocity.y/Math.abs(velocity.y));}

        if (Math.abs(velocity.z)>0) { newZ = newZ+(velocity.z/Math.abs(velocity.z));}

        return new Point3D(newX,newY,newZ);
    }

    public void setDialogCommandLevelMessenger(LevelMessenger levelMessenger) {
        this.dialogCommand = new DialogCommand(levelMessenger);
    }
}