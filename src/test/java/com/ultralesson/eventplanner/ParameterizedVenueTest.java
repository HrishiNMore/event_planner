package com.ultralesson.eventplanner;

import com.ultralesson.eventplanner.exceptions.VenueException;
import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.DataProvider;
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

    @DataProvider(name = "venueDataProvider")
    public static Object[][] provideVenueData() {
        return new Object[][] {
                {1, "Venue A", "Address A", 100},
                {2, "Venue B", "Address B", 0}, // to result in a VenueException for zero capacity
                // Add more test data as required
        };
    }

    @Factory(dataProvider = "venueDataProvider")
    public static Object[] createInstances(int id, String name, String address, int capacity) {
        return new Object[] { new ParameterizedVenueTest(id, name, address, capacity) };
    }

    @Test
    public void testVenueCreation() {
        Venue venue = new Venue(id, name, address, capacity);
        Assert.assertNotNull(venue, "Venue creation failed");
        Assert.assertEquals(venue.getId(), id, "Venue ID mismatch");
        Assert.assertEquals(venue.getName(), name, "Venue name mismatch");
        Assert.assertEquals(venue.getAddress(), address, "Venue address mismatch");
        Assert.assertEquals(venue.getCapacity(), capacity, "Venue capacity mismatch");
    }

    @Test(expectedExceptions = VenueException.class)
    public void testVenueCreationWithInvalidCapacity() {
        new Venue(id, name, address, capacity);
    }
}
