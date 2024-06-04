package org.example.MarkdownPage;

import java.io.IOException;
import java.net.MalformedURLException;

public interface InsertOpenFileButtonHandler {
    void onButtonOpenFilePressed(String docID) throws IOException;
}