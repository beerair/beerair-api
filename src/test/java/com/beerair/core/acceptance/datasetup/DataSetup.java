package com.beerair.core.acceptance.datasetup;

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
