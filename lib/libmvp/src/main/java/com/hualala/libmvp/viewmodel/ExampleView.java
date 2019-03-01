package com.hualala.libmvp.viewmodel;

import android.support.annotation.Keep;
import android.view.View;
import com.hualala.libmvp.contractor.IExampleContractor;
import com.hualala.libmvp.view.ViewDelegate;

@Keep
public class ExampleView extends ViewDelegate<View> implements IExampleContractor.IExampleView {


    public ExampleView(View view) {
        super(view);
    }

    @Override
    public void showName() {

    }
}
