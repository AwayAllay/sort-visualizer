import util.Visualizer;

public class Main {
    public static void main(String[] args) {

        Visualizer visualizer = new Visualizer(200);
        visualizer.randomize(10);
        visualizer.bubbleSort(1);
        //visualizer.randomize(5);
        //visualizer.mergeSort(20);
        //visualizer.selectionSort(1);
        //visualizer.insertionSort(10);

    }
}
