package com.example.business;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;

public class InvestorDashboardFragment extends Fragment {



    private DatabaseHelper dbHelper;

    private static final String TAG = InvestorDashboardFragment.class.getSimpleName();
    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.investor_fragment_dashboard, container, false);
        init(root);

        //dbHelper = new DatabaseHelper(getActivity());

        //String name = dbHelper.getTask("username").getString(1);

        return root;
    }

    private void init(View root) {
        CardStackView cardStackView = root.findViewById(R.id.card_stack_view);
        manager = new CardStackLayoutManager(getContext(), new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                Log.d(TAG, "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
            }

            @Override
            public void onCardSwiped(Direction direction) {
                Log.d(TAG, "onCardSwiped: p=" + manager.getTopPosition() + " d=" + direction);
                if (direction == Direction.Right){
                    Toast.makeText(getContext(), "Liked", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Top){
                    Toast.makeText(getContext(), "Direction Top", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Left){
                    Toast.makeText(getContext(), "Removed", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Bottom){
                    Toast.makeText(getContext(), "Direction Bottom", Toast.LENGTH_SHORT).show();
                }

                // Paginating
                if (manager.getTopPosition() == adapter.getItemCount() - 5){
                    paginate();
                }

            }

            @Override
            public void onCardRewound() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardCanceled() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardAppeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", name: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", name: " + tv.getText());
            }
        });

        dbHelper = new DatabaseHelper(getActivity());

        Cursor data = dbHelper.getTask("t");


        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        adapter = new CardStackAdapter(addList(data));
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());
    }

    private void paginate() {

        dbHelper = new DatabaseHelper(getActivity());


        Cursor data = dbHelper.getTask("t");







        List<ItemModel> old = adapter.getItems();
        List<ItemModel> baru = new ArrayList<>(addList(data));
        CardStackCallback callback = new CardStackCallback(old, baru);
        DiffUtil.DiffResult hasil = DiffUtil.calculateDiff(callback);
        adapter.setItems(baru);
        hasil.dispatchUpdatesTo(adapter);
    }

    private List<ItemModel> addList(Cursor data) {

        //SharedPreferences pref = new MainActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
       // SharedPreferences.Editor editor = pref.edit();
       // String username = pref.getString("user", null);
        //String name = dbHelper.getTask("username").getString(1);



        List<ItemModel> items = new ArrayList<>();



        for (int i = 0; i < data.getCount(); i++) {

            String name=data.getString(2);
            String location=data.getString(6);
            String experience=data.getString(12);
            String skills=data.getString(13);
            String investment=data.getString(9);
            String businessType=data.getString(7);


            items.add(new ItemModel(R.drawable.sample1, name, location, experience, skills, investment, businessType ));


            //iterate over the columns
            //for(int j = 0; j < data.getColumnNames().length; j++){

            //append the column value to the string builder and delimit by a pipe symbol
            //    stringBuilder.append(data.getString(j) + "|");
            // }
            //add a new line carriage return
            //  stringBuilder.append("\n");

            //move to the next row
            data.moveToNext();
        }
        //close the cursor
        data.close();


        return items;
    }



}
