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

import util.Data;
import util.Graphic;

import java.sql.Time;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BogoSort {

    private final boolean showTime;
    private final boolean showSorting;
    private final Data dataClass = new Data();
    private final int[] data;
    private final List<Integer> dataList;
    private double started = 0;

    public BogoSort(boolean showTime, boolean showSorting, int[] data) {
        this.showTime = showTime;
        this.showSorting = showSorting;
        this.data = data;
        dataList = IntStream.of(data)
                .boxed()
                .collect(Collectors.toList());
    }

    public void sort(){
        started = System.currentTimeMillis();

        Collections.shuffle(dataList);

        int[] shuffled = new int[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            shuffled[i] = dataList.get(i);
        }

        if (dataClass.isSorted(shuffled)){
            finish(shuffled);
        }
        else sort();
    }

    private void finish(int[] sorted) {
        if (showTime){
            double time = System.currentTimeMillis() - started;
        }

        new Graphic(sorted).draw();
    }


}
