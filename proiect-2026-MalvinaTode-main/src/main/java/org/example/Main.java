package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/*Main citeste comenzile din in si le execute, folosesc CommandFactory*/

public class Main {
    public static void main(String[] args) {
        if (args.length != 2 && args.length != 4) { //verif daca are 2 sau 4 argumente
            return;
        }

        try {
            if (args.length == 2) { //cazul cu un singur fisier de in
                String firstArg = args[0];
                String workingFile = args[1];
                handleOneFile(workingFile);
            } else {  //cazul cu mai multe fisiere de in
                String firstArg = args[0];
                String serverFile = args[1];
                String groupFile = args[2];
                String eventFile = args[3];

                handleOneFile(serverFile);
                handleOneFile(groupFile);
                handleOneFile(eventFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*procesez un fisier de in*/
    private static void handleOneFile(String inputFileName) throws Exception {
        String outputFileName = inputFileName + ".out"; //fisier de out
        File outputFile = new File(outputFileName);

        PrintStream normalOutput = System.out;
        PrintStream fileOutput = new PrintStream(new FileOutputStream(outputFileName)) {
            @Override
            public void println(String s) {
                print(s + "\n");
            }
        };
        System.setOut(fileOutput);

        try {
            Database database = Database.getInstance();  //obtin instanta bazei de date
            CommandFactory commandMaker = new CommandFactory(database); //creez factoryul de comenzi

            String realInputFileName = inputFileName + ".in";
            List<String> fileLines = Files.readAllLines(Paths.get(realInputFileName)); //citesc toate liniile din fisier


            //incep de la linia 1
            for (int i = 1; i < fileLines.size(); i++) {
                String currentLine = fileLines.get(i).trim();
                String[] lineParts = currentLine.split("\\|");  //delimitator

                if (lineParts.length > 0) {
                    String whatCommand = lineParts[0].trim(); //tipul comenzii

                    //iau parametrii comenzii
                    String[] parameters = new String[lineParts.length - 1];
                    for (int j = 0; j < parameters.length; j++) {
                        parameters[j] = lineParts[j + 1];
                    }

                    //execut comanda
                    Command command = commandMaker.createCommand(whatCommand);
                    command.execute(parameters, i);
                }
            }
        } finally {
            System.setOut(normalOutput);
            fileOutput.close();
        }
    }
}
