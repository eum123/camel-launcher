package net.mj.camel.launcher;

import org.junit.Test;

import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SampleTest {
    @Test
    public void test() {

        int[] array[][] = {
                null,
                {{1, 2, 3, 4, 5}},
                new int[3][],
                {{6, 7, 8, 9, 10, 11}, {12, 13, 14, 15}, {16, 17, 18, 19}}
        };
        // line 11: Write your statement here!
        System.out.println(array[3][2][2]);
    }

    @Test
    public void path() throws Exception {
        String pathString = "file://" +System.getProperty("user.dir") + "/src/test/resources/conf/route/*.xml";

        if(pathString.lastIndexOf("/") < pathString.length()) {
            pathString = pathString.substring(0, pathString.lastIndexOf("/") + 1);
        }

        System.out.println("pathString :" + pathString);

        URI uri = new URI(pathString);

        Path path = Paths.get(uri);
        System.out.println(Files.list(path).count());

        Files.list(path).forEach(x -> {
            System.out.println(x.toString());
            System.out.println(x.getFileName().toString());
        });
    }

    @Test
    public void pathPattern() throws Exception {
        String pathString = "file://" +System.getProperty("user.dir") + "/src/test/resources/conf/route/g*.xml";

        String fileName = pathString.substring(pathString.lastIndexOf("/") +1);

        if(pathString.lastIndexOf("/") < pathString.length()) {
            pathString = pathString.substring(0, pathString.lastIndexOf("/") + 1);
        }

        System.out.println("pathString :" + pathString + " " + fileName);

        URI uri = new URI(pathString);

        Path path = Paths.get(uri);
        System.out.println(Files.list(path).count());

        Files.newDirectoryStream(path, fileName).forEach( x -> {
            System.out.println(x.toString());
        });


    }
}
