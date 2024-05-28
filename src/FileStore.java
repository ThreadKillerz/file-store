import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import file.FileHandler;
import java.util.Random;

public class FileStore {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the file path:");
        String filePath = scanner.nextLine();

        File file = new File(filePath);

        FileHandler fileHandler = new FileHandler();
        try {
            List<String> chunks = fileHandler.getFileChunks(file);
            Random random = new Random();
            try (FileOutputStream fos = new FileOutputStream( random.nextInt(1000) + file.getName())) {
                fileHandler.getFile(chunks).writeTo(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
