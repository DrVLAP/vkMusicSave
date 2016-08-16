package Experimental;

import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by First on 09.08.2016.
 */
public class Legs{

    @Test
        public void writerBlya()  throws IOException {
        FileWriter writer = new FileWriter("C:/vkmusic.txt", true);

        writer.write("jhjhkjsfgkbsh" + "\n");
        writer.write("o/  \\o/  " );
        writer.write("\\o" + "\n");

        }

}
