package util;

import org.fusesource.jansi.AnsiConsole;

import java.util.ArrayList;
import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

public class Graphic {

    private int line = 1;
    private boolean recursiveCall = false;
    private final int[] data;
    private final List<String> graphic = new ArrayList<>();

    public Graphic(int[] data) {
        this.data = data;
    }

    public void draw(){

        String lineString = "";

        for (int i = 0; i < data.length; i++) {

            int singleData = data[i];

            if (singleData >= line) {
                lineString = lineString + "| ";
            }
            else {
                lineString = lineString + "  ";
            }
            if (singleData > line && !recursiveCall){
                recursiveCall = true;
            }

        }
        graphic.add(lineString);
        line++;
        checkForRecursiveCall();
    }

    private void checkForRecursiveCall() {
        if (recursiveCall){
            recursiveCall = false;
            draw();
        }
        else {
            printGraphic();
        }
    }

    private void printGraphic() {

        System.out.print(ansi().cursor(0,0).eraseScreen());

        for (int i = graphic.size() - 1; i >= 0 ; i--) {
            String s = graphic.get(i);
            System.out.println(s);
        }
    }
}
