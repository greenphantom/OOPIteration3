package Model.AI;

import Model.Entity.Entity;
import Model.Level.Obstacle;
import Model.Level.Terrain;
import Model.Utility.HexDistanceCalculator;
import Model.Utility.VectorToPointCalculator;
import com.sun.javafx.geom.Vec3d;
import javafx.geometry.Point3D;
import javafx.util.Pair;

import java.util.*;

import static Model.Entity.EntityAttributes.Orientation.*;

public class PathingAlgorithm {
    private Map<Point3D, Terrain> terrainMap;
    private Map<Point3D, Obstacle> obstacleMap;
    private List<Vec3d> cardinalVelocities;

    public PathingAlgorithm(Map<Point3D, Terrain> terrainMap, Map<Point3D, Obstacle> obstacleMap) {
        this.terrainMap = terrainMap;
        this.obstacleMap = obstacleMap;
        cardinalVelocities = new ArrayList<>();
        /*cardinalVelocities.add(new Vec3d(0, 1, -1));
        cardinalVelocities.add(new Vec3d(-1, 1, 0));
        cardinalVelocities.add(new Vec3d(1, -1, 0));
        cardinalVelocities.add(new Vec3d(1, 0, -1));
        cardinalVelocities.add(new Vec3d(-1, 0, 1));
        cardinalVelocities.add(new Vec3d(0, -1, 1));*/

    }

    public Point3D getAStarPoint(Point3D start, Point3D goal, Entity mover){
        Point3D next = start;
        Pair<Point3D,Integer> cost = new Pair<>(start,Integer.MAX_VALUE);
        for (Point3D p: getAdjacentList(start)){
            int new_cost = getCost(p,goal,mover);
            if (new_cost < cost.getValue()){
                next = p;
                cost = new Pair<>(p,new_cost);
            }
        }
        return next;
    }

    private int getCost(Point3D point, Point3D goal, Entity actor){
        if (!isValidPoint(point,actor)){
            return Integer.MAX_VALUE;
        }
        else {
            return HexDistanceCalculator.getHexDistance(point, goal);
        }
    }

    public ArrayList<Point3D> getReachablePoints(Point3D start, int rangeLimit, Entity actor){
        ArrayList<Point3D> visited = new ArrayList<>();
        visited.add(start);
        ArrayList<ArrayList<Point3D>> fringe = new ArrayList<>();
        ArrayList<Point3D> zeroth = new ArrayList<>();
        zeroth.add(start);
        fringe.add(zeroth);

        for (int i = 1; i <= rangeLimit; ++i){
            ArrayList<Point3D> temp = new ArrayList<>();
            fringe.add(temp);
            for (Point3D point: fringe.get(i-1)){
                ArrayList<Point3D> adj = (ArrayList<Point3D>) getAdjacentList(point);
                for (Point3D p: adj){
                    if (isValidPointToMoveTo(p,visited, actor)){
                        visited.add(p);
                        fringe.get(i).add(p);
                    }
                }
            }

        }
        return visited;
    }

    private boolean isValidPoint(Point3D p, Entity e){
        return (e.canMoveOnTerrain(terrainMap.get(p)) && !obstacleMap.containsKey(p));
    }

    private boolean isValidPointToMoveTo(Point3D p, ArrayList<Point3D> visited, Entity e){
        return (e.canMoveOnTerrain(terrainMap.get(p)) && !obstacleMap.containsKey(p) || !visited.contains(p));
    }

    public static Model.Entity.EntityAttributes.Orientation calculateOrientation(Point3D position, Point3D goal) {
        Vec3d oriVector = VectorToPointCalculator.calculateNewVelocity(position,goal);
        if (oriVector.equals(new Vec3d(0, 1, -1))){
            return NORTH;
        }
        else if (oriVector.equals(new Vec3d(-1, 1, 0))){
            return NORTHWEST;
        }
        else if (oriVector.equals(new Vec3d(1, -1, 0))){
            return NORTHEAST;
        }
        else if (oriVector.equals(new Vec3d(1, 0, -1))){
            return SOUTHEAST;
        }
        else if (oriVector.equals(new Vec3d(-1, 0, 1))){
            return SOUTHWEST;
        }
        else {
            return SOUTH;
        }
    }

    public List<Point3D> getAdjacentList(Point3D p) {
        List<Point3D> adj = new ArrayList<>();
        adj.add(VectorToPointCalculator.calculateNewPoint(p,new Vec3d(0, 1, -1)));
        adj.add(VectorToPointCalculator.calculateNewPoint(p,new Vec3d(0, -1, 1)));
        adj.add(VectorToPointCalculator.calculateNewPoint(p,new Vec3d(1, 0, -1)));
        adj.add(VectorToPointCalculator.calculateNewPoint(p,new Vec3d(-1, 0, 1)));
        adj.add(VectorToPointCalculator.calculateNewPoint(p,new Vec3d(-1, 1, 0)));
        adj.add(VectorToPointCalculator.calculateNewPoint(p,new Vec3d(1, -1, 0)));
        return adj;
    }
}
