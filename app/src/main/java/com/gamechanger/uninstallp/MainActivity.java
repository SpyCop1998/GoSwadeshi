package com.gamechanger.uninstallp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private CoordinatorLayout mCLayout;
    private FloatingActionButton mFab;
    int c;
    TextView msg,msg1,txt1,txt2;
//    RelativeLayout relativeLayout;
    CardView cardView;
    CircleImageView circleImageView;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    int AppCount;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        cardView=findViewById(R.id.Card_View_);
        circleImageView=findViewById(R.id.L1);

        // Get the application context
        mContext = getApplicationContext();
        mActivity = MainActivity.this;
        msg=findViewById(R.id.MessageW);
        msg1=findViewById(R.id.MessageW1);
//        relativeLayout=findViewById(R.id.Rel);
        // Get the widget reference from XML layout
        mCLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        cardView=findViewById(R.id.Card_View_);
        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);

        // Define a layout for RecyclerView
        mLayoutManager = new GridLayoutManager(mActivity,1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialize a new adapter for RecyclerView
        try {
            mAdapter = new UninstallAppsAdapter(
                    mContext,
                    new AppsManager(mContext).getInstalledPackages()
            );
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        // Set the adapter for RecyclerView
        mRecyclerView.setAdapter(mAdapter);
//        Toast.makeText(getApplicationContext(),AppCount,Toast.LENGTH_SHORT).show();
//        Log.d("Item", String.valueOf(AppCount));
        c=mAdapter.getItemCount();
        if (c==0)
        {
//            txt1.setEnabled(false);
//            txt2.setEnabled(false);
//            cardView.setEnabled(false);
//            for (int i=0;i<cardView.getChildCount();i++)
//            {
//                View child=cardView.getChildAt(i);
//                child.setEnabled(false);
//            }

//            txt2.setEnabled(false);
//            txt1.setEnabled(false);
//            txt1.setVisibility(View.GONE);
//            txt2.setVisibility(View.GONE);
            circleImageView.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.GONE);
            mCLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            msg.setEnabled(true);
            msg1.setEnabled(true);
            msg.setText("Congratulations!");
            msg1.setText("You are now a\nSwadeshi Warrior");
        }

        // Set a click listener for floating action button
        mFab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                // Recreate the adapter with installed apps list
                try {
                    mAdapter = new UninstallAppsAdapter(mContext, new AppsManager(mContext).getInstalledPackages());
                    c=mAdapter.getItemCount();
                    Log.d("AppLe", String.valueOf(c));
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                // Set the adapter for Recycler view
//                Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
                // Refresh recycler view with updated data
                mRecyclerView.setAdapter(mAdapter);
                if (c==0)
                {
//                    for (int i=0;i<cardView.getChildCount();i++)
//                    {
//                        View child=cardView.getChildAt(i);
//                        child.setEnabled(false);
//                    }
//                    txt1.setVisibility(View.GONE);
//                    txt2.setVisibility(View.GONE);
                    cardView.setVisibility(View.GONE);
                    circleImageView.setVisibility(View.VISIBLE);
                    mCLayout.setBackgroundColor(Color.parseColor("#ffffff"));
                    msg.setEnabled(true);
                    msg1.setEnabled(true);
                    msg.setText("Congratulations!");
                    msg1.setText("You are now a\nSwadeshi Warrior");
//                    Toast.makeText(getApplicationContext(),"App :: 0 ",Toast.LENGTH_SHORT).show();
//                    LinearLayout v = new LinearLayout(getApplicationContext());
//                    //populate layout with your image and text or whatever you want to put in here
//                    ImageView imageView=new ImageView(getApplicationContext());
//                    imageView.setImageResource(R.drawable.warrior);
//                    v.addView(imageView);
//                    Toast toast = new Toast(getApplicationContext());
//                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//                    toast.setDuration(Toast.LENGTH_LONG);
//                    toast.setView(v);
//                    toast.show();
                }
//
            }
        });
    }
}