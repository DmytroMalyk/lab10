package ua.edu.ucu.apps;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CachedDocument implements Document {

    private final Document inner;

    @Override
    public String parse() {
        String cachedData = 
                DBConnection.getInstance().getDocument(retrieveGcsPath());
        if (cachedData != null) {
            return cachedData;
        } else {
            String parsedContent = inner.parse();
            DBConnection.getInstance().createDocument(retrieveGcsPath(), parsedContent);
            return parsedContent;
        }
    }

    @Override
    public String getGcsPath() {
        return inner.getGcsPath();
    }

    private String retrieveGcsPath() {
        return inner.getGcsPath();
    }
}
