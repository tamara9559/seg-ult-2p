package Utilis;

import Model.Toy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static void saveToys(File file, List<Toy> list){

        try{
            FileOutputStream exit = new FileOutputStream(file);
            ObjectOutputStream objectExit = new ObjectOutputStream(exit);
            objectExit.writeObject(list);
            objectExit.close();
        } catch (FileNotFoundException e){
            System.out.println("The file doesnt exist");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static List<Toy> getToys(File file){
        List<Toy> toys = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInput ois = new ObjectInputStream(fis);
            toys = (List<Toy>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no existe");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return toys;
    }
}
