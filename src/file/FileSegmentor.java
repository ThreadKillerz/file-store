package file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileSegmentor {

    final int noOfPeers;

    public FileSegmentor() {
        this.noOfPeers = 3;
    }

    public List<byte[]> chunkFile(File file) throws IOException {
        Path filePath = file.toPath();
        byte[] fileBytes = Files.readAllBytes(filePath);

        int totalSize = fileBytes.length;
        int chunkSize = totalSize / this.noOfPeers; // Change this to adapt to your needs

        List<byte[]> chunks = new ArrayList<>();
        for (int i = 0; i < this.noOfPeers; i++) {
            int start = i * chunkSize;
            int length = Math.min(chunkSize, totalSize - start);
            byte[] chunk = new byte[length];
            System.arraycopy(fileBytes, start, chunk, 0, length);
            chunks.add(chunk);
        }
        return chunks;
    }

    public ByteArrayOutputStream rebuildFile(List<byte[]> chunks) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (byte[] chunk : chunks) {
            baos.write(chunk);
        }
        return baos;
    }
}
