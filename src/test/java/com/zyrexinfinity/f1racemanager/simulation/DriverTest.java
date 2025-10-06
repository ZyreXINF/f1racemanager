package com.zyrexinfinity.f1racemanager.simulation;

import com.zyrexinfinity.f1racemanager.enums.Track;
import com.zyrexinfinity.f1racemanager.model.BolidEntity;
import com.zyrexinfinity.f1racemanager.model.ConstructorEntity;
import com.zyrexinfinity.f1racemanager.model.DriverEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DriverTest {
    @Test
    void lapTimeTest(){
        Driver driver = new Driver(getSampleModel());
        assertNotEquals(0, driver.projectLapTime(Track.Monza));
    }

    DriverEntity getSampleModel(){
        DriverEntity de = new DriverEntity();
        de.setFullName("Test Driver");
        de.setNationality("Germany");
        de.setDriverAwareness(100);
        de.setDriverPace(100);
        BolidEntity be = new BolidEntity();
        be.setBolidModel("T1");
        be.setReliability(100);
        ConstructorEntity ce = new ConstructorEntity();
        ce.setCrewRating(100);
        ce.setTeamName("Test Team");
        ce.setBolid(be);
        return de;
    }
}
