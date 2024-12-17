package util;/*
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Data {
    public int[] getRandomizedData(int maxX){

        List<Integer> dataList = new ArrayList<>();
        int[] data = new int[maxX];

        for (int i = 1; i <= maxX; i++) {
            dataList.add(i);
        }

        Collections.shuffle(dataList);

        for (int i = 0; i < dataList.size(); i++) {
            data[i] = dataList.get(i);
        }

        return data;
    }

    public boolean isSorted(int[] data){
        boolean sorted = true;

        for (int i = 0; i < data.length - 1; i++) {
            if (!(data[i] < data[i + 1])){
                sorted = false;
                break;
            }
        }

        return sorted;
    }
}
