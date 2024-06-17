package org.vamshi.librarymanagementsystem.support;

import java.sql.Timestamp;

public class DueDate {
    public Timestamp getDueDate() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        // Add 15 days worth of milliseconds
        long millisIn15Days = 15L * 24 * 60 * 60 * 1000;
        long futureTimeInMillis = currentTimestamp.getTime() + millisIn15Days;

        // Create a new Timestamp object representing the future time
        return new Timestamp(futureTimeInMillis);
    }
}
