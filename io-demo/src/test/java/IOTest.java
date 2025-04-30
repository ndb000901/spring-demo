import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

public class IOTest {

    @Test
    public void test() throws IOException {
        Properties properties = System.getProperties();
        System.out.println();
        FileInputStream fileInputStream = new FileInputStream("./data/data.log");
        byte[] buf = new byte[1024];
        int size = 0;
        while ( (size = fileInputStream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, size));
        }
    }

    @Test
    public void testFile() {
        File file = new File("./data/data.log");
        System.out.println(file.isFile());
        System.out.println();
    }

    @Test
    public void testDir() {
        File file = new File("/home/hello/code/java/spring/spring-demo/io-demo");
        Queue<File> queue = new LinkedList<>();
        queue.add(file);

        while (!queue.isEmpty()) {
            File curr = queue.poll();
            File[] list = curr.listFiles();

            for (File item : list) {
                if (item.isDirectory()) {
                    queue.add(item);
                    System.out.println("d: " + item.getName());
                }
                else {
                    System.out.println("f: " + item.getName());
                }
            }
        }
    }
}
