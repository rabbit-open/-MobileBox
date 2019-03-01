package com.hualala.libmvp.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.hualala.libmvp.contractor.ExampleContractor;
import com.hualala.libmvp.presenter.BaseContractorActivity;

public class MVPVMContractorActivity extends BaseContractorActivity {

    private ExampleContractor presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ExampleContractor(getWindow().getDecorView(), this);
    }

}
