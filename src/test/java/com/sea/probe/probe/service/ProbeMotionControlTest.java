package com.sea.probe.probe.service;

import com.sea.probe.probe.model.Coordinate;
import com.sea.probe.probe.model.Direction;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProbeMotionControlTest {

    @InjectMocks
    ProbeMotionControl probeMotionControl;

    @Test
    void move() {
        probeMotionControl.introduceObstacle(new Coordinate(5,6));
        List<Coordinate> coordinates1 = probeMotionControl.moveForward(3);
        assertTrue(coordinates1.contains(new Coordinate(5,8)));
        List<Coordinate> coordinates2 = probeMotionControl.moveBackwards(3);
        assertTrue(coordinates2.contains(new Coordinate(5,5)));
    }

    @Test
    void turnRight() {
        Direction currentDirection = probeMotionControl.getCurrentDirection();
        Direction expectedDirection = switch (currentDirection){
            case E -> Direction.S;
            case N -> Direction.E;
            case S -> Direction.W;
            case W -> Direction.N;
        };
        assertEquals(currentDirection,expectedDirection);
    }

    @Test
    void turnLeft() {
        Direction currentDirection = probeMotionControl.getCurrentDirection();
        Direction expectedDirection = switch (currentDirection){
            case E -> Direction.N;
            case N -> Direction.W;
            case S -> Direction.E;
            case W -> Direction.S;
        };
        assertEquals(currentDirection,expectedDirection);
    }
}