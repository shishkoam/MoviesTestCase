package com.example.user.simplervtest.adapter;

import java.util.HashSet;
/**
 * Created by User on 26.03.2017
 */

public enum RecyclerSelectionController {
    INSTANCE;

    HashSet<Integer> selectedItems = new HashSet<>();

    public boolean isSelected(Integer item) {
        return selectedItems.contains(item);
    }

    public void changeItemSelectionState(Integer item) {
        if (isSelected(item)) {
            selectedItems.remove(item);
        } else {
            selectedItems.add(item);
        }
    }
}
