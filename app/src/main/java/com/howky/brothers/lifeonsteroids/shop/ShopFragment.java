package com.howky.brothers.lifeonsteroids.shop;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.howky.brothers.lifeonsteroids.R;


public class ShopFragment extends Fragment
    implements View.OnClickListener {

    public ShopFragment() {}

    public static ShopFragment newInstance() {
        return new ShopFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shop,
                container, false);

        CardView foodcardview = view.findViewById(R.id.cardview_food);
        foodcardview.setOnClickListener(this);

        CardView medicinescardview = view.findViewById(R.id.cardview_medicines);
        medicinescardview.setOnClickListener(this);

        CardView funcardview = view.findViewById(R.id.cardview_fun);
        funcardview.setOnClickListener(this);

        CardView blackmarketcardview = view.findViewById(R.id.cardview_blackmarket);
        blackmarketcardview.setOnClickListener(this);

        CardView housecardview = view.findViewById(R.id.cardview_house);
        housecardview.setOnClickListener(this);

        CardView transportcardview = view.findViewById(R.id.cardview_transport);
        transportcardview.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity().getApplicationContext(), BuyActivity.class);
        /*switch (view.getId()) {
            case R.id.cardview_food:
                break;
            case R.id.cardview_medicines:
                break;
            case R.id.cardview_fun:
                break;
            case R.id.cardview_lottery:
                break;
            case R.id.cardview_house:
                break;
            case R.id.cardview_transport:
                break;
            default:
                break;
        }*/
        intent.putExtra(getResources().getString(R.string.send_shop_click_id), view.getId());
        startActivity(intent);
    }

}

