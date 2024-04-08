import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import file.FileHandler;

public class FileStore {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the file path:");
        String filePath = scanner.nextLine();

        File file = new File(filePath);

        FileHandler fileHandler = new FileHandler();
        try {
            List<String> chunks = fileHandler.getFileChunks(file);
            try (FileOutputStream fos = new FileOutputStream("moon_2.png")) {
                fileHandler.getFile(chunks).writeTo(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
