import algorithms.BogoSort;
import algorithms.BubbleSort;
import util.Data;
import util.Graphic;

public class Main {
    public static void main(String[] args) {


        int[] data = new Data().getRandomizedData(10000);
        //int[] data2 = new Data().getRandomizedData(30);
        //int[] data3 = new Data().getRandomizedData(30);

        Graphic graphic = new Graphic(data);
        graphic.draw();
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(data);
        //Graphic graphic2 = new Graphic(data2);
        //graphic2.draw();
        //Graphic graphic23 = new Graphic(data3);
        //graphic23.draw();


        //new BogoSort(false, false, data).sort();
    }
}
