package com.example.taxiapp.Adapter;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.taxiapp.Fragment.CreditDebitFragment;
import com.example.taxiapp.Fragment.NetBankingFragment;
import com.example.taxiapp.Utils.taxiapp.SdkuiUtil.SdkUIConstants;
import com.payu.india.Model.PayuResponse;
import com.payu.india.Payu.PayuConstants;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by piyush on 29/7/15.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> mTitles;
    private PayuResponse payuResponse;
    private PayuResponse valueAddedResponse;
    private HashMap<Integer, Fragment> mPageReference = new HashMap<Integer, Fragment>();

    public PagerAdapter(FragmentManager fragmentManager, ArrayList<String> titles, PayuResponse payuResponse, PayuResponse valueAddedResponse) {
        super(fragmentManager);
        this.mTitles = titles;
        this.payuResponse = payuResponse;
        this.valueAddedResponse = valueAddedResponse;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        switch (mTitles.get(i)){


            case SdkUIConstants.CREDIT_DEBIT_CARDS:
                fragment = new CreditDebitFragment();
                bundle.putParcelableArrayList(PayuConstants.CREDITCARD, payuResponse.getCreditCard());
                bundle.putParcelableArrayList(PayuConstants.DEBITCARD, payuResponse.getDebitCard());
                bundle.putSerializable(SdkUIConstants.VALUE_ADDED, valueAddedResponse.getIssuingBankStatus());
                bundle.putInt(SdkUIConstants.POSITION, i);
                fragment.setArguments(bundle);
                mPageReference.put(i, fragment);
                return fragment;

            case SdkUIConstants.NET_BANKING:
                fragment = new NetBankingFragment();
                bundle.putParcelableArrayList(PayuConstants.NETBANKING, payuResponse.getNetBanks());
                bundle.putSerializable(SdkUIConstants.VALUE_ADDED, valueAddedResponse.getNetBankingDownStatus());
                fragment.setArguments(bundle);
                mPageReference.put(i, fragment);
                return fragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        if(mTitles != null)
            return mTitles.size();
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    public Fragment getFragment(int key){
        return mPageReference.get(key);
    }


}
