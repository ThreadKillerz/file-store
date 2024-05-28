package file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileSegmentor {

    final int chunkSize;

    public FileSegmentor() {
        this.chunkSize = 1024*1024;
    }

    public List<byte[]> chunkFile(File file) throws IOException {
        Path filePath = file.toPath();
        byte[] fileBytes = Files.readAllBytes(filePath);

        int totalSize = fileBytes.length;
        int noOfChunks = (int) Math.ceil((double) totalSize / chunkSize);

        List<byte[]> chunks = new ArrayList<>();
        for (int i = 0; i < noOfChunks; i++) {
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
