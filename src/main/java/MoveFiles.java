import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.Collection;

public class MoveFiles {

    /**
     * Instructions:
     * publish
     * releaseClient
     * make public
     * releaseClient again
     * make public again
     * MoveFiles
     * push hosting files
     */
    static String[] FILE_NAMES = {"http-api", "polish", "rlawt", "runelite-api", "runelite-jshell", "runescape-api", "runelite-client"};
    static String SNAPSHOT = "4.25.7";

    static String[] PATHS = {
            System.getProperty("user.home") + "/Development/k204/kronos-client/http-api/build/repo/com/openosrs/http-api/" + SNAPSHOT + "/",
            System.getProperty("user.home") + "/Development/k204/kronos-client/polish/build/repo/com/openosrs/polish/" + SNAPSHOT + "/",
            System.getProperty("user.home") + "/Development/k204/kronos-client/runelite-jshell/build/repo/com/openosrs/runelite-jshell/" + SNAPSHOT + "/",
            System.getProperty("user.home") + "/Development/k204/kronos-client/runescape-api/build/repo/com/openosrs/rs/runescape-api/" + SNAPSHOT + "/",
            System.getProperty("user.home") + "/Development/k204/kronos-client/runelite-api/build/repo/com/openosrs/runelite-api/" + SNAPSHOT + "/",
            System.getProperty("user.home") + "/Development/k204/kronos-client/runelite-client/build/repo/com/openosrs/runelite-client/" + SNAPSHOT + "/",
    };

    public static void main(String[] args) throws IOException {

        String[] extensions = {"jar"};
        for (int i = 0; i <PATHS.length; i++) {
            Collection<File> files = FileUtils.listFiles(new File(PATHS[i]), extensions, true);
            files.forEach(file -> {
                for (String fileName : FILE_NAMES) {
                    if (file.getName().contains(fileName) && file.getName().contains(SNAPSHOT)) {
                        try {
                            FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "/stable/" + file.getName()), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });
        }
    }

}
