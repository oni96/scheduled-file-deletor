package scheduled.file.deletor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aniruddha
 */
public class ScheduledFileDeletor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {

        Scanner s = new Scanner(System.in);
        ArrayList<String> fileAddresses = new ArrayList<>();

        System.out.println("Enter full addresses of the files:");
        String t = s.nextLine();

        while (!t.isEmpty()) {
            fileAddresses.add(t);
            t = s.nextLine();
        }

        System.out.println("Files to delete:" + fileAddresses);

        System.out.println("Enter time in DD/MM/YYYY hh:mm:ss format:");
        String timeOfDeletion = s.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

        //System.out.println("The Command as shown is: " + "DEL " + fileAddresses.get(0));
        while (true) {
            Calendar c = Calendar.getInstance();
            String currentTime = sdf.format(c.getTime());
            if (currentTime.equals(timeOfDeletion)) {
                int i = 0;
                while (i < fileAddresses.size()) {
                        File file = new File(fileAddresses.get(i++));
                        if(file.exists())
                            file.delete();                 
                        else
                            System.out.println("File "+ fileAddresses.get(i-1)+ " was not found!");
                    
                }
                break;
            } else {
                try {
                    System.out.println(currentTime);
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ScheduledFileDeletor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }
}
