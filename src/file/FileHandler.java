package file;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    
    FileSegmentor fileSegmentor;

    public FileHandler() {
        this.fileSegmentor = new FileSegmentor();
    }

    public List<String> getFileChunks(File file) throws IOException {

        List<String> serializedChunks = new ArrayList<>();
        List<byte[]> chunks = fileSegmentor.chunkFile(file);

        for (byte[] chunk : chunks) {
            serializedChunks.add(FileSerializer.serialize(chunk));
        }

        return serializedChunks;
    }

    public ByteArrayOutputStream getFile(List<String> fileChunks) throws IOException {
        List<byte[]> deserializedChunks = new ArrayList<>();

        for (String chunk : fileChunks) {
            deserializedChunks.add(FileSerializer.deserialize(chunk));
        }

        return fileSegmentor.rebuildFile(deserializedChunks);
    }
}
