package Model.InfluenceEffect;

import Controller.Visitor.Visitor;
import Model.Command.EntityCommand.SettableCommand.SettableCommand;
import Model.Entity.EntityAttributes.Orientation;
import javafx.geometry.Point3D;

import java.util.ArrayList;

public class RadialInfluenceEffect extends InfluenceEffect{
    public RadialInfluenceEffect(SettableCommand command, int range, long speed, Orientation orientation) {
        super(command, range, speed, orientation);
    }

    public RadialInfluenceEffect(SettableCommand command, int range, long speed, Orientation orientation, int movesRemaining) {
        super(command, range, speed, orientation, movesRemaining);
    }

    //Defines logic for moving in every direction
    public ArrayList<Point3D> nextMove(Point3D point) {
        if(canMove()) {
            //Out of moves, return empty list
            if (noMovesRemaining()) {
                return new ArrayList<>();
            }

            ArrayList<Point3D> newPoints = new ArrayList<>();

            if (rangeIsZero()) {
                newPoints.add(point);
                return newPoints;
            }

            int ringDistance = getRange() - getMovesRemaining();
            Point3D currentPoint = point;
            for (int i = 0; i < ringDistance; i++) {//Find starting point
                currentPoint = Orientation.getAdjacentPoint(currentPoint, Orientation.NORTH);
            }

            //Generates ring of radius ringDistance
            for (int i = 0; i < Orientation.values().length - 1; i++) {
                for (int j = 0; j < ringDistance; j++) {
                    newPoints.add(currentPoint);
                    currentPoint = Orientation.getAdjacentPoint(currentPoint, Orientation.values()[(i + 2) % 6]);
                }
            }

            decrementMovesRemaining();
            return newPoints;
        }

        return new ArrayList<>();
    }

    @Override
    public InfluenceEffect cloneInfluenceEffect() {
        return new RadialInfluenceEffect(getCommand(), getRange(), getSpeed(), getOrientation(), getMovesRemaining());
    }

    public void accept(Visitor visitor) {
        visitor.visitInfluenceEffect(this);
    }
}
