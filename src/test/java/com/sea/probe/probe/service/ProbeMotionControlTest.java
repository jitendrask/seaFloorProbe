package com.sea.probe.probe.service;

import com.sea.probe.probe.model.Coordinate;
import com.sea.probe.probe.model.Direction;
import com.sea.probe.probe.model.ServiceStatus;
import com.sea.probe.probe.model.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
class ProbeMotionControlTest {

    @InjectMocks
    ProbeMotionControl probeMotionControl;

    @Test
    void moveForwardObstructed() {
        probeMotionControl.introduceObstacle(new Coordinate(5,7));
        ServiceStatus serviceStatus = probeMotionControl.moveForward(4);
        assertEquals(Status.OBSTRUCTED, serviceStatus.status());
    }

    @Test
    void moveForwardSuccess() {
        ServiceStatus serviceStatus = probeMotionControl.moveForward(4);
        assertEquals(Status.SUCCESS, serviceStatus.status());
    }

    @Test
    void moveForwardOutOfBounds() {
        ServiceStatus serviceStatus = probeMotionControl.moveForward(6);
        assertEquals(Status.OUTOFBOUNDS, serviceStatus.status());
    }


    @Test
    void moveBackwardsObstructed() {
        probeMotionControl.introduceObstacle(new Coordinate(9,1));
        ServiceStatus serviceStatus = probeMotionControl.moveBackwards(2);
        System.out.println(serviceStatus);
        assertEquals(Status.OBSTRUCTED, serviceStatus.status());
    }

    @Test
    void moveBackwardsSuccess() {
        ServiceStatus serviceStatus = probeMotionControl.moveBackwards(4);
        assertEquals(Status.SUCCESS, serviceStatus.status());
    }

    @Test
    void moveBackwardsOutOfBounds() {
        ServiceStatus serviceStatus = probeMotionControl.moveBackwards(6);
        assertEquals(Status.OUTOFBOUNDS,serviceStatus.status());
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
        assertEquals(probeMotionControl.turnRight(),expectedDirection);
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
        assertEquals(probeMotionControl.turnLeft(),expectedDirection);
    }
}