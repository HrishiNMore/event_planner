package com.ultralesson.eventplanner;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import com.ultralesson.eventplanner.model.Venue;

public class ParameterizedVenueTest {
    private int id;
    private String name;
    private String address;
    private int capacity;

    public ParameterizedVenueTest(int id, String name, String address, int capacity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    @Factory(dataProvider = "venueDataProvider")
    public static Object[] createInstances(int id, String name, String address, int capacity) {
        return new Object[] {new ParameterizedVenueTest(id, name, address, capacity)};
    }

    @Test
    public void testVenueCreation() {
        // Test the creation of the Venue instance
    }

    // Additional test methods as required

    // Data provider supplying test data
    @org.testng.annotations.DataProvider(name = "venueDataProvider")
    public static Object[][] provideVenueData() {
        return new Object[][] {
                {1, "Venue A", "Address A", 100},
                {2, "Venue B", "Address B", 0}, // to result in a VenueException for zero capacity
        };
    }
}
