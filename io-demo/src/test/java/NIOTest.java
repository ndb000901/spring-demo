import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;

public class NIOTest {

    @Test
    public void testPath() {

        Path path = Paths.get("./");
        System.out.println();
    }

    @Test()
    public void testFiles() throws IOException {
        Path path = Paths.get("./data/log1");
        if (Files.notExists(path)) {
            Files.createFile(path);
        }

        Files.write(Paths.get("./data/log1"), "hello\n".getBytes());
    }

    @Test
    public void testWatch() throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get("./data");
        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
        );

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println(event.kind() + ": " + event.context());
            }
            key.reset();
        }
    }
}
