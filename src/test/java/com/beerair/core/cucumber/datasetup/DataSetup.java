package com.beerair.core.cucumber.datasetup;

public abstract class DataSetup {
    private boolean flag = false;

    public final void exec() {
        if (flag) {
            return;
        }
        execute();
        flag = true;
    }

    protected abstract void execute();
}
