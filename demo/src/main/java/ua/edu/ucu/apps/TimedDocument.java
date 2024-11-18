package ua.edu.ucu.apps;

import java.time.Duration;
import java.time.LocalTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TimedDocument implements Document {

    private final Document innerDocument;

    @Override
    public String parse() {
        LocalTime startTimestamp = LocalTime.now();

        String parsedContent = innerDocument.parse();

        LocalTime finishTimestamp = LocalTime.now();

        System.out.println("Time: " +
            Duration.between(startTimestamp, finishTimestamp).getSeconds() + " seconds");

        return parsedContent;
    }

    @Override
    public String getGcsPath() {
        throw new UnsupportedOperationException("Method 'getGcsPath' is not implemented");
    }
}
