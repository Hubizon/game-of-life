package com.example.hubert.gameoflife.Work;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hubert.gameoflife.Education.EducationFragment;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Shop.BuyActivity;


public class FindJobFragment extends Fragment
        implements View.OnClickListener{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FindJobFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FindJobFragment.
     */
    public static FindJobFragment newInstance(String param1, String param2) {
        FindJobFragment fragment = new FindJobFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find_job,
                container, false);

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity().getApplicationContext(), BuyActivity.class);
        switch (view.getId()) {
            case R.id.cardview_officeWork:
                intent.putExtra(getResources().getString(R.string.send_shop_click_id), view.getId());
                break;

            case R.id.cardview_criminalWork:
                intent.putExtra(getResources().getString(R.string.send_shop_click_id), view.getId());
                break;

            case R.id.cardview_artsWork:
                intent.putExtra(getResources().getString(R.string.send_shop_click_id), view.getId());
                break;

            case R.id.cardview_outsideWork:
                intent.putExtra(getResources().getString(R.string.send_shop_click_id), view.getId());
                break;

            case R.id.cardview_otherWork:
                intent.putExtra(getResources().getString(R.string.send_shop_click_id), view.getId());
                break;

            case R.id.cardview_educationWork:
                Fragment newFragment = new EducationFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.pager, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();
                break;

            default:
                break;
        }
        startActivity(intent);
    }
}
