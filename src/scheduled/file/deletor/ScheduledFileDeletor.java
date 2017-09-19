package scheduled.file.deletor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Aniruddha
 */
public class ScheduledFileDeletor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        ArrayList<String> fileAddresses = new ArrayList<>();
        ArrayList<String> timeOfDeletion = new ArrayList<>();
        int fileCount = 0;
        
        System.out.println("Enter full addresses of the files:");
        String t = s.nextLine();

        while (!t.isEmpty()) {
            fileAddresses.add(t);
            System.out.println("Enter time of deletion in DD/MM/YY hh:mm:ss format:");
            timeOfDeletion.add(s.nextLine());
            t = s.nextLine();
        }

        System.out.println("Files to delete:");

        for (int i = 0; i < fileAddresses.size(); i++) {
            System.out.println(fileAddresses.get(i) + " at " + timeOfDeletion.get(i));
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

        while (true) {
            Calendar c = Calendar.getInstance();
            String currentTime = sdf.format(c.getTime());
            for (int i = 0; i < fileAddresses.size(); i++) {
                if (currentTime.equals(timeOfDeletion.get(i))) {

                    File file = new File(fileAddresses.get(i));
                    if (file.exists()) {
                        file.delete();
                        System.out.println(fileAddresses.get(i) + " was deleted at "+ timeOfDeletion.get(i));
                    } else {
                        System.out.println("File " + fileAddresses.get(i) + " was not found!");
                    }
                    fileCount++;
                } else {
                    System.out.print("Waiting...\b\b\b\b\b\b\b\b\b\b");
                }

            }
            if(fileCount==fileAddresses.size())
                break;
        }
    }
}

