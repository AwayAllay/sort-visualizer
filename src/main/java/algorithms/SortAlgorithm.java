/*
 * WarpsAndHomes - Minecraft plugin
 * Copyright (C) 2024 AwayAllay
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package algorithms;

import util.SortArray;

public abstract class SortAlgorithm {

    protected boolean isCancelled = false;
    protected int swaps = 0;

    public abstract void sort(SortArray sortArray);
    public abstract String name();


    public void cancel(){
        isCancelled = true;
    }

    public void reset(){
        swaps = 0;
        isCancelled = false;
    }

    public int getSwaps(){
        return swaps;
    }
}
