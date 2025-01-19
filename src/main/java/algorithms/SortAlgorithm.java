
package algorithms;

import util.SortArray;

public abstract class SortAlgorithm {

    protected boolean isCancelled = false;
    protected int changes = 0;

    public abstract void sort(SortArray sortArray);
    public abstract String name();


    public void cancel(){
        isCancelled = true;
    }

    public void reset(){
        changes = 0;
        isCancelled = false;
    }

    public int getChanges(){
        return changes;
    }
}
