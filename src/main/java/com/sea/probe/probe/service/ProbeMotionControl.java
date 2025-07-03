package com.sea.probe.probe.service;

import com.sea.probe.probe.model.Coordinate;
import com.sea.probe.probe.model.Direction;
import com.sea.probe.probe.model.ServiceStatus;
import com.sea.probe.probe.model.Status;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProbeMotionControl {

    static int maxX = 10;
    static int maxY = 10;
    static Coordinate currentCoordinate = new Coordinate(5,5);
    static Direction currentDirection = Direction.N;
    static List<Coordinate> obstacles = new ArrayList<>();

    public ServiceStatus moveForward(int value){
        Coordinate expectedCoordinate = getExpectedLocation(value, currentDirection, false);
        if(isExpectedCoordinateOutOfGrid(expectedCoordinate)){
            return new ServiceStatus(Status.OUTOFBOUNDS, List.of(), currentCoordinate);
        }
        List<Coordinate> visitedCoordinates = new ArrayList<>();
        while(!currentCoordinate.equals(expectedCoordinate)){
            Coordinate coordinate = switch (currentDirection){
                case N -> new Coordinate(currentCoordinate.x(), currentCoordinate.y()+1);
                case E -> new Coordinate(currentCoordinate.x()+1, currentCoordinate.y());
                case W -> new Coordinate(currentCoordinate.x()-1, currentCoordinate.y());
                case S -> new Coordinate(currentCoordinate.x(), currentCoordinate.y()-1);
            };
            if(obstacles.contains(coordinate)){
                return new ServiceStatus(Status.OBSTRUCTED, visitedCoordinates, currentCoordinate);
            }
            visitedCoordinates.add(coordinate);
            currentCoordinate = coordinate;
        }


        return new ServiceStatus(Status.SUCCESS, visitedCoordinates, currentCoordinate);
    }
    public ServiceStatus moveBackwards(int value){
        Coordinate expectedCoordinate = getExpectedLocation(value, currentDirection, true);
        if(isExpectedCoordinateOutOfGrid(expectedCoordinate)){
            return new ServiceStatus(Status.OUTOFBOUNDS, List.of(), currentCoordinate);
        }
        List<Coordinate> visitedCoordinates = new ArrayList<>();
        while(!currentCoordinate.equals(expectedCoordinate)){
            Coordinate coordinate = switch (currentDirection){
                case N -> new Coordinate(currentCoordinate.x(), currentCoordinate.y()-1);
                case E -> new Coordinate(currentCoordinate.x()-1, currentCoordinate.y());
                case W -> new Coordinate(currentCoordinate.x()+1, currentCoordinate.y());
                case S -> new Coordinate(currentCoordinate.x(), currentCoordinate.y()+1);
            };
            if(obstacles.contains(coordinate)){
                return new ServiceStatus(Status.OBSTRUCTED, visitedCoordinates, currentCoordinate);
            }
            visitedCoordinates.add(coordinate);
            currentCoordinate = coordinate;
        }


        return new ServiceStatus(Status.SUCCESS, visitedCoordinates, currentCoordinate);
    }

    public Direction turnRight(){
        currentDirection = switch (currentDirection){
            case E -> Direction.S;
            case N -> Direction.E;
            case S -> Direction.W;
            case W -> Direction.N;
        };
        return currentDirection;
    }

    public Direction turnLeft(){
        currentDirection = switch (currentDirection){
            case E -> Direction.N;
            case N -> Direction.W;
            case S -> Direction.E;
            case W -> Direction.S;
        };
        return currentDirection;
    }

    public void introduceObstacle(Coordinate coordinate){
        if(!obstacles.contains(coordinate)){
            obstacles.add(coordinate);
        }
    }

    public List<Coordinate> getObstacles(){
        return obstacles;
    }

    public Direction getCurrentDirection(){
        return currentDirection;
    }

    public Coordinate getCurrentCoordinate(){
        return currentCoordinate;
    }

    private Coordinate getExpectedLocation(int numberOfCoordinates, Direction direction, boolean isBackward){
        int factor = isBackward?-1:1;
        return switch (direction){
            case N -> new Coordinate(currentCoordinate.x(), currentCoordinate.y()+(numberOfCoordinates*factor));
            case E -> new Coordinate(currentCoordinate.x()+(numberOfCoordinates*factor), currentCoordinate.y());
            case W -> new Coordinate(currentCoordinate.x()-(numberOfCoordinates*factor), currentCoordinate.y());
            case S -> new Coordinate(currentCoordinate.x(), currentCoordinate.y()-(numberOfCoordinates*factor));
        };
    }

    private boolean isExpectedCoordinateOutOfGrid(Coordinate coordinate){
        if(coordinate.x()>maxX-1 || coordinate.x()<0){
            return true;
        }
        if(coordinate.y()>maxY-1 || coordinate.y()<0){
            return true;
        }
        return false;
    }

}
