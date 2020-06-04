package com.gamechanger.uninstallp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.gamechanger.uninstallp.R;

import java.util.List;
public class UninstallAppsAdapter extends RecyclerView.Adapter<UninstallAppsAdapter.ViewHolder>{
    private Context mContext;
    private List<String> mDataSet;
    int c;

    public UninstallAppsAdapter(Context context, List<String> list){
        mContext = context;
        mDataSet = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextViewLabel;
        public TextView mTextViewPackage;
        public ImageView mImageViewIcon;
        public ImageButton mImageButtonUninstall;
        TextView Alternate;
        ImageButton Downbtn;

        public ViewHolder (View v){
            super(v);
            // Get the widgets reference from custom layout
            mCardView = (CardView) v.findViewById(R.id.card_view);
            mTextViewLabel = (TextView) v.findViewById(R.id.app_label);
//            mTextViewPackage = (TextView) v.findViewById(R.id.app_package);
            mImageViewIcon = (ImageView) v.findViewById(R.id.iv_icon);
            mImageButtonUninstall = (ImageButton) v.findViewById(R.id.ib_uninstall);
            Alternate=v.findViewById(R.id.AlterNateApp);
            Downbtn=v.findViewById(R.id.DownBtn);
        }
    }

    @Override
    public UninstallAppsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(mContext).inflate(R.layout.custom_view_uninstall_apps,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position){
        // Initialize a new instance of AppManager class
        AppsManager appsManager = new AppsManager(mContext);

        // Get the current package name
        final String packageName = (String) mDataSet.get(position);

        // Get the current app icon
        Drawable icon = appsManager.getAppIconByPackageName(packageName);

        // Get the current app label
        final String label = appsManager.getApplicationLabelByPackageName(packageName);
//        final String l1=label.toLowerCase();
        //******************
        final ChinaAppClass obj=new ChinaAppClass();
//        String Alternate=appsManager.getAlterNate(packageName);
//        holder.Alternate.setText(obj.chApp.get(l1));
        holder.Alternate.setText(obj.chApp.get(label));
        final AppsLink obj1=new AppsLink();

//        final String url=obj1.DownLink.get(alt);

        holder.Downbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String alt=obj.chApp.get(label);
                final String url=obj1.DownLink.get(alt);
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Toast.makeText(mContext.getApplicationContext(),url,Toast.LENGTH_SHORT).show();
                mContext.startActivity(i);
            }
        });
        //**********************

        // Set the current app label
        holder.mTextViewLabel.setText(label);
        holder.mImageViewIcon.setImageDrawable(icon);
        holder.mImageButtonUninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:"+packageName));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                c=0;
//                Toast.makeText(mContext.getApplicationContext(),c,Toast.LENGTH_SHORT).show();
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        // Count the installed apps
//        c=mDataSet.size();
        return mDataSet.size();
    }
}