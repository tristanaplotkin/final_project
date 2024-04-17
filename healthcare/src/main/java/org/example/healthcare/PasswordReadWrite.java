package org.example.healthcare;

import java.io.*;
import java.util.ArrayList;

public class PasswordReadWrite {
    private final ArrayList<Password> passwords;
    private final String path = "src/main/resources/data/passwords.dat";
    private final String errorPath = "src/main/resources/data/error.txt";
    private final File passwordsFile = new File(path);
    private final File errorsFile = new File(errorPath);

    public PasswordReadWrite() throws FileNotFoundException {
        passwords = new ArrayList<>();
    }

    public ArrayList<Password> getPasswords() {
        return passwords;
    }

    public void setPasswords(ArrayList<Password> passwords) {
        this.passwords.clear();
        this.passwords.addAll(passwords);
    }

    public void addPassword(Password password) {
        passwords.add(password);
    }

    public void readPasswords() throws FileNotFoundException {
        Password password;
        boolean endOfFile = false;
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(passwordsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectInputStream inputFile = null;
        try {
            inputFile = new ObjectInputStream(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!endOfFile) {
            try {
                password = (Password) inputFile.readObject();
                passwords.add(password);
            } catch (EOFException e) {
                endOfFile = true;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            inputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writePasswords() throws FileNotFoundException {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            PrintWriter writer = new PrintWriter(errorPath);
            writer.write(e.toString());
            writer.flush();
            writer.close();
        }

        ObjectOutputStream outputFile = null;
        try {
            outputFile = new ObjectOutputStream(fileOutputStream);
        } catch (IOException e) {
            PrintWriter writer = new PrintWriter(errorPath);
            writer.write(e.toString());
            writer.flush();
            writer.close();
        }

        for (Password password : passwords) {
            try {
                assert outputFile != null;
                outputFile.writeObject(password);
            } catch (IOException e) {
                PrintWriter writer = new PrintWriter(errorPath);
                writer.write(e.toString());
                writer.flush();
                writer.close();
            }

            try {
                outputFile.flush();
                outputFile.close();
            } catch (IOException e) {
                PrintWriter writer = new PrintWriter(errorPath);
                writer.write(e.toString());
                writer.flush();
                writer.close();
            }
        }
    }
}
