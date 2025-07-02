package com.sea.probe.probe.service;

import com.sea.probe.probe.model.Coordinate;
import com.sea.probe.probe.model.Direction;
import com.sea.probe.probe.model.ServiceStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProbeMotionControl {
    static Direction currentDirection = Direction.N;
    static List<Coordinate> obstacles = new ArrayList<>();

    public ServiceStatus moveForward(int value){

        return null;
    }
    public ServiceStatus moveBackwards(int value){
        return null;
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

    public Direction getCurrentDirection(){
        return currentDirection;
    }

}
