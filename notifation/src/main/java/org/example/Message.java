package org.example;
import java.io.IOException;
public interface Message {
    void EnvoyerEmail(String objetEmail, String contenuEmail) throws IOException;
    void EnvoyerNotification() throws IOException;
}
