package org.example.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {

    private static volatile Logger instance;

    private String fileName;
    private BufferedWriter writer;

    // Default file name
    private static final String DEFAULT_FILE_NAME = "log.txt";

    // Private constructor (Singleton requirement)
    private Logger() {
        this.fileName = DEFAULT_FILE_NAME;
        openWriter(this.fileName);
    }

    // Lazy initialization + thread-safe Singleton
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    /**
     * Change log file name dynamically.
     * Closes current file and opens the new one.
     */
    public synchronized void setFileName(String newFileName) {
        if (newFileName == null || newFileName.trim().isEmpty()) {
            System.out.println("Logger: file name cannot be empty. Keeping current file: " + fileName);
            return;
        }

        // If same name, do nothing
        if (newFileName.equals(this.fileName)) {
            return;
        }

        close(); // close current writer safely
        this.fileName = newFileName;
        openWriter(this.fileName);
    }

    /**
     * Write one log message per line.
     */
    public synchronized void write(String message) {
        if (writer == null) {
            // Try to recover: reopen current file
            openWriter(this.fileName);
        }

        if (writer == null) {
            // Still null -> give up gracefully
            System.out.println("Logger: cannot write because file is not available.");
            return;
        }

        try {
            writer.write(message);
            writer.newLine();
            writer.flush(); // ensure message is written immediately
        } catch (IOException e) {
            System.out.println("Logger: error while writing to file: " + fileName);
            e.printStackTrace();
        }
    }

    /**
     * Close logger (safe to call multiple times).
     */
    public synchronized void close() {
        if (writer == null) return;

        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Logger: error while closing file: " + fileName);
            e.printStackTrace();
        } finally {
            writer = null;
        }
    }

    // Helper: open writer in append mode
    private void openWriter(String fileName) {
        try {
            // append = true (keeps old logs, adds new lines)
            this.writer = new BufferedWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            System.out.println("Logger: could not open file: " + fileName);
            e.printStackTrace();
            this.writer = null;
        }
    }
}
