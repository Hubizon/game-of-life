package com.example.hubert.gameoflife.Shop;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hubert.gameoflife.MainActivity;
import com.example.hubert.gameoflife.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShopBuyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShopBuyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopBuyFragment extends Fragment implements RecyclerViewShopBuyAdapter.ItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ShopBuyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopBuyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopBuyFragment newInstance(String param1, String param2) {
        ShopBuyFragment fragment = new ShopBuyFragment();
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

    RecyclerViewShopBuyAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_buy,
                container, false);


        ArrayList<String> itemsNames = new ArrayList<>();
        ArrayList<String> itemsPrices = new ArrayList<>();

        ArrayList<String> itemsNamesOfficeJob = new ArrayList<>();
        itemsNamesOfficeJob.add("Lawyer");
        itemsNamesOfficeJob.add("Reporter");
        itemsNamesOfficeJob.add("Engraver");
        itemsNamesOfficeJob.add("Programmer");
        itemsNamesOfficeJob.add("Businessman");

        ArrayList<String> itemsRewardOfficeJob = new ArrayList<>();
        itemsRewardOfficeJob.add("$ 10000");
        itemsRewardOfficeJob.add("$ 2500");
        itemsRewardOfficeJob.add("$ 9500");
        itemsRewardOfficeJob.add("$ 7000");
        itemsRewardOfficeJob.add("$ 11000");

        ArrayList<String> itemsNamesCriminalJob = new ArrayList<>();
        itemsNamesCriminalJob.add("Pickpocket");
        itemsNamesCriminalJob.add("Thief");
        itemsNamesCriminalJob.add("Drug dealer");
        itemsNamesCriminalJob.add("Terrorist");
        itemsNamesCriminalJob.add("Kidnap kids");
        itemsNamesCriminalJob.add("Mafia member");
        itemsNamesCriminalJob.add("Assasin");

        ArrayList<String> itemsRewardCriminalJob = new ArrayList<>();
        itemsRewardCriminalJob.add("$ 6500");
        itemsRewardCriminalJob.add("$ 9000");
        itemsRewardCriminalJob.add("$ 12000");
        itemsRewardCriminalJob.add("$ 10000");
        itemsRewardCriminalJob.add("$ 15000");
        itemsRewardCriminalJob.add("$ 10000");
        itemsRewardCriminalJob.add("$ 20000");

        ArrayList<String> itemsNamesArtsJob = new ArrayList<>();
        itemsNamesArtsJob.add("Writer");
        itemsNamesArtsJob.add("Painter");

        ArrayList<String> itemsRewardArtsJob = new ArrayList<>();
        itemsRewardArtsJob.add("$ 4000");
        itemsRewardArtsJob.add("$ 3750");

        ArrayList<String> itemsNamesMediaJob = new ArrayList<>();
        itemsNamesMediaJob.add("YouTuber");
        itemsNamesMediaJob.add("Streamer");
        itemsNamesMediaJob.add("Blogger");

        ArrayList<String> itemsRewardMediaJob = new ArrayList<>();
        itemsRewardMediaJob.add("$ 30000");
        itemsRewardMediaJob.add("$ 15000");
        itemsRewardMediaJob.add("$ 20000");

        ArrayList<String> itemsNamesOutsideJob = new ArrayList<>();
        itemsNamesOutsideJob.add("Footballer");
        itemsNamesOutsideJob.add("Basketball Player");
        itemsNamesOutsideJob.add("Swimmer");
        itemsNamesOutsideJob.add("Runner");
        itemsNamesOutsideJob.add("Cyclist");
        itemsNamesOutsideJob.add("Geologist");
        itemsNamesOutsideJob.add("Dustman");

        ArrayList<String> itemsRewardOutsideJob = new ArrayList<>();
        itemsRewardOutsideJob.add("$ 2000");
        itemsRewardOutsideJob.add("$ 4000");
        itemsRewardOutsideJob.add("$ 3000");
        itemsRewardOutsideJob.add("$ 1500");
        itemsRewardOutsideJob.add("$ 3500");
        itemsRewardOutsideJob.add("$ 8000");
        itemsRewardOutsideJob.add("$ 2500");

        ArrayList<String> itemsNamesOtherJob = new ArrayList<>();
        itemsNamesOtherJob.add("Doctor");
        itemsNamesOtherJob.add("Teacher");
        itemsNamesOtherJob.add("Scientist");
        itemsNamesOtherJob.add("Salesperson");

        ArrayList<String> itemsRewardOtherJob = new ArrayList<>();
        itemsRewardOtherJob.add("$ 11500");
        itemsRewardOtherJob.add("$ 2250");
        itemsRewardOtherJob.add("$ 8500");
        itemsRewardOtherJob.add("$ 3000");

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewShopBuy);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        switch (view.getId())
        {
            case R.id.foodBuyShop:
                for(int i = 0; i < ShopFragment.foodList.length; i++)
                    itemsNames.add(ShopFragment.foodList[i].getName());
                for(int i = 0; i < ShopFragment.foodList.length; i++)
                    itemsPrices.add("$" + ShopFragment.foodList[i].getPrice());
                break;

            case R.id.medicinesBuyShop:
                for(int i = 0; i < ShopFragment.medicinesList.length; i++)
                    itemsNames.add(ShopFragment.medicinesList[i].getName());
                for(int i = 0; i < ShopFragment.medicinesList.length; i++)
                    itemsPrices.add("$" + ShopFragment.medicinesList[i].getPrice());
                break;

            case R.id.funBuyShop:
                for(int i = 0; i < ShopFragment.funList.length; i++)
                    itemsNames.add(ShopFragment.funList[i].getName());
                for(int i = 0; i < ShopFragment.funList.length; i++)
                    itemsPrices.add("$" + ShopFragment.funList[i].getPrice());
                break;

            case R.id.lotteryBuyShop:
                for(int i = 0; i < ShopFragment.lotteryList.length; i++)
                    itemsNames.add(ShopFragment.lotteryList[i].getName());
                for(int i = 0; i < ShopFragment.lotteryList.length; i++)
                    itemsPrices.add("$" + ShopFragment.lotteryList[i].getPrice());
                break;

            case R.id.houseBuyShop:
                for(int i = 0; i < ShopFragment.lodgingList.length; i++)
                    itemsNames.add(ShopFragment.lodgingList[i].getName());
                for(int i = 0; i < ShopFragment.lodgingList.length; i++)
                    itemsPrices.add("$" + ShopFragment.lodgingList[i].getPrice());
                break;

            case R.id.transportBuyShop:
                for(int i = 0; i < ShopFragment.transportList.length; i++)
                    itemsNames.add(ShopFragment.transportList[i].getName());
                for(int i = 0; i < ShopFragment.transportList.length; i++)
                    itemsPrices.add("$" + ShopFragment.transportList[i].getPrice());
                break;

            case R.id.officeWork:
                itemsNames = itemsNamesOfficeJob;
                itemsPrices = itemsRewardOfficeJob;
                break;

            case R.id.criminalWork:
                itemsNames = itemsNamesCriminalJob;
                itemsPrices = itemsRewardCriminalJob;
                break;

            case R.id.artsWork:
                itemsNames = itemsNamesArtsJob;
                itemsPrices = itemsRewardArtsJob;
                break;

            case R.id.outsideWork:
                itemsNames = itemsNamesMediaJob;
                itemsPrices = itemsRewardMediaJob;
                break;

            case R.id.otherWork:
                itemsNames = itemsNamesOutsideJob;
                itemsPrices = itemsRewardOutsideJob;
                break;

            case R.id.educationWork:
                itemsNames = itemsNamesOtherJob;
                itemsPrices = itemsRewardOtherJob;
                break;

            default:
                itemsNames = itemsNamesOfficeJob;
                itemsPrices = itemsRewardOfficeJob;
                break;
        }

        adapter = new RecyclerViewShopBuyAdapter(view.getContext(), itemsNames, itemsPrices);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId())
        {
            case R.id.foodBuyShop:
                if(MainActivity.Money >= ShopFragment.foodList[position].getPrice())
                {
                    MainActivity.Money -= ShopFragment.foodList[position].getPrice();
                    MainActivity.Hungry += ShopFragment.foodList[position].getGivenFood();
                    MainActivity.Health += ShopFragment.foodList[position].getGivenHealth();
                    MainActivity.Energy += ShopFragment.foodList[position].getGivenEnergy();
                    MainActivity.Happiness += ShopFragment.foodList[position].getGivenHappiness();
                }
                else
                    Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                break;

            case R.id.medicinesBuyShop:
                if(MainActivity.Money >= ShopFragment.medicinesList[position].getPrice())
                {
                    MainActivity.Money -= ShopFragment.medicinesList[position].getPrice();
                    MainActivity.Health += ShopFragment.medicinesList[position].getGivenHealth();
                }
                else
                    Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                break;

            case R.id.funBuyShop:
                if("Computer".equals(ShopFragment.funList[position].getType()) && MainActivity.MyComputer == null)
                    buyFun(position);
                else if("Computer".equals(ShopFragment.funList[position].getType()))
                {
                    Toast.makeText(view.getContext(), "You already have purchased '" + MainActivity.MyComputer +"'. Do you want to sell it for" + (ShopFragment.funList[position].getPrice() / 2)
                            + "and buy " + ShopFragment.funList[position].getName() + "?", Toast.LENGTH_LONG).show();
                    MainActivity.Money += (ShopFragment.funList[position].getPrice() / 2);
                    if(!buyFun(position))
                        MainActivity.Money -= (ShopFragment.funList[position].getPrice() / 2);
                }

                else if(ShopFragment.funList[position].getType().equals("Phone") && MainActivity.MyPhone == null)
                    buyFun(position);
                else if ("Phone".equals(ShopFragment.funList[position].getType()))
                {
                    Toast.makeText(view.getContext(), "You already have purchased '" + MainActivity.MyPhone +"'. Do you want to sell it for" + (ShopFragment.funList[position].getPrice() / 2)
                            + "and buy " + ShopFragment.funList[position].getName() + "?", Toast.LENGTH_LONG).show();
                    MainActivity.Money += (ShopFragment.funList[position].getPrice() / 2);
                    if(!buyFun(position))
                        MainActivity.Money -= (ShopFragment.funList[position].getPrice() / 2);
                }

                else if(ShopFragment.funList[position].getType().equals("TV") && MainActivity.MyTv == null)
                    buyFun(position);
                else if ("TV".equals(ShopFragment.funList[position].getType()))
                {
                    Toast.makeText(view.getContext(), "You already have purchased '" + MainActivity.MyTv +"'. Do you want to sell it for" + (ShopFragment.funList[position].getPrice() / 2)
                            + "and buy " + ShopFragment.funList[position].getName() + "?", Toast.LENGTH_LONG).show();
                    MainActivity.Money += (ShopFragment.funList[position].getPrice() / 2);
                    if(!buyFun(position))
                        MainActivity.Money -= (ShopFragment.funList[position].getPrice() / 2);
                }
                break;

            case R.id.lotteryBuyShop:
                if(MainActivity.Money >= ShopFragment.lotteryList[position].getPrice())
                {
                    MainActivity.Money -= ShopFragment.lotteryList[position].getPrice();
                    MainActivity.OwnedLotteries.add(ShopFragment.lotteryList[position]);
                }
                else
                    Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                break;

            case R.id.houseBuyShop:
                if(MainActivity.MyLodging == null || !MainActivity.MyLodging.getName().equals("Parents House"))
                {
                    if(MainActivity.Money >= ShopFragment.lodgingList[position].getPrice())
                    {
                        MainActivity.Money -= ShopFragment.lodgingList[position].getPrice();
                        MainActivity.MyLodging = ShopFragment.lodgingList[position];
                    }
                    else
                        Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(view.getContext(), "You already have purchased (phone). Do you want to sell it for (price / 2) and but (computer)?", Toast.LENGTH_LONG).show();
                break;

            case R.id.transportBuyShop:
                if(MainActivity.MyTransport == null || MainActivity.MyTransport.getName().equals("Foots"))
                {
                    if(MainActivity.Money >= ShopFragment.transportList[position].getPrice())
                    {
                        MainActivity.Money -= ShopFragment.transportList[position].getPrice();
                        MainActivity.MyTransport = ShopFragment.transportList[position];
                    }
                    else
                        Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(view.getContext(), "You already have purchased (phone). Do you want to sell it for (price / 2) and but (computer)?", Toast.LENGTH_LONG).show();
                break;

            default:
                if(MainActivity.MyTransport == null)
                {
                    if(MainActivity.Money >= ShopFragment.transportList[position].getPrice())
                    {
                        MainActivity.Money -= ShopFragment.transportList[position].getPrice();
                        MainActivity.MyTransport = ShopFragment.transportList[position];
                    }
                    else
                        Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(view.getContext(), "You already have purchased (phone). Do you want to sell it for (price / 2) and but (computer)?", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private boolean buyFun(int position)
    {
        if (MainActivity.Money >= ShopFragment.funList[position].getPrice())
        {
            MainActivity.Money -= ShopFragment.funList[position].getPrice();
            switch (ShopFragment.funList[position].getType()) {
                case "Exit":
                    MainActivity.Happiness += ShopFragment.funList[position].getGivenFun();
                    break;
                case "Tv":
                    MainActivity.MyTv = ShopFragment.funList[position];
                    break;
                case "Computer":
                    MainActivity.MyComputer = ShopFragment.funList[position];
                    break;
                case "Phone":
                    MainActivity.MyPhone = ShopFragment.funList[position];
                    break;
            }
            return true;
        } else
        {
            Toast.makeText(getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
