package com.sea.probe.probe.service;

import com.sea.probe.probe.model.Coordinate;
import com.sea.probe.probe.model.Direction;

import java.util.ArrayList;
import java.util.List;

public class ProbeMotionControl {

    static Coordinate currentCoordinate = new Coordinate(5,5);
    static Direction currentDirection = Direction.N;
    static int[][] grid = new int[10][10];
    static List<Coordinate> obstacles = new ArrayList<>();

    public List<Coordinate> moveForward(int value){
        List<Coordinate> visitedCoordinates = new ArrayList<>();
        return visitedCoordinates;
    }
    public List<Coordinate> moveBackwards(int value){
        List<Coordinate> visitedCoordinates = new ArrayList<>();
        return visitedCoordinates;
    }

    public Direction turnRight(){
        return null;
    }

    public Direction turnLeft(){
        return null;
    }

    public void introduceObstacle(Coordinate coordinate){

    }

    public List<Coordinate> getObstacle(Coordinate coordinate){

        return null;
    }

    public Direction getCurrentDirection(){
        return currentDirection;
    }

}
