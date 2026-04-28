package io.github.mgx01.brassburgchegg.api.gui.managers;

public class WidgetSelectionManager<T> {
    private T selectedValue;

    public WidgetSelectionManager(T defaultValue) {
        this.selectedValue = defaultValue;
    }

    public void select(T value) {
        if (isSelected(value)) {
            this.selectedValue = null;
        } else {
            this.selectedValue = value;
        }
    }

    public boolean isSelected(T value) {
        return value != null && value.equals(selectedValue);
    }

    public T getSelected() {
        return selectedValue;
    }
}