package com.gamechanger.uninstallp;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class AppsManager {
    Map<String,String> chApp;
    private Context mContext;
    Set<Map.Entry<String,String>> st;

    public AppsManager(Context context){
        mContext = context;
    }

    // Get a list of installed app
    public List<String> getInstalledPackages() throws PackageManager.NameNotFoundException {
        // Initialize a new Intent which action is main
        Intent intent = new Intent(Intent.ACTION_MAIN,null);

        // Set the newly created intent category to launcher
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        // Set the intent flags
        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK|
                        Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
        );

        // Generate a list of ResolveInfo object based on intent filter
        List<ResolveInfo> resolveInfoList = mContext.getPackageManager().queryIntentActivities(intent,0);

        // Initialize a new ArrayList for holding non system package names
        List<String> packageNames = new ArrayList<>();
        List<String> ApkPackageName1 = new ArrayList<>();

        // Loop through the ResolveInfo list
        for(ResolveInfo resolveInfo : resolveInfoList){
            // Get the ActivityInfo from current ResolveInfo
            ActivityInfo activityInfo = resolveInfo.activityInfo;

            // If this is not a system app package
            if(!isSystemPackage(resolveInfo) && !isSelfAppPackage(activityInfo.applicationInfo.packageName)){
                // Add the non system package to the list
                packageNames.add(activityInfo.applicationInfo.packageName);
            }
        }
        ////
        chApp=new HashMap<>();
        chApp.put("TikTok","Roposo");chApp.put("Vigo Video","Roposo");chApp.put("AppLock","applock");chApp.put("Zoom","google meet");
        chApp.put("Kwai","biugo");chApp.put("BIGO LIVE","Roposo");chApp.put("U-Dictionary","Eng-Hindi");chApp.put("SHEIN","myntra");
        chApp.put("Helo","Roposo");chApp.put("UC Browser","Jio browser");chApp.put("Turbo VPN","secure vpn");chApp.put("Wish","Flipkart");
        chApp.put("Likee","Roposo");chApp.put("VivaVideo","kinemaster");chApp.put("CamScanner","Adobe scanner");chApp.put("AliExpress","Flipkart");
        chApp.put("SHAREit","Jio switch");chApp.put("Vmate","youtube go");chApp.put("Club Factory","myntra");
        chApp.put("Xender","Jio switch");chApp.put("BeautyPlus","snapseed");chApp.put("File Manager","fx file");


//        chApp.put("TikTok","Nacho");chApp.put("Sharechat","");chApp.put("CM Browser","");chApp.put("Beauty Plus","");
//        chApp.put("Kwai","");chApp.put("Xender","");chApp.put("Vigo Video","");chApp.put("Applock","");
//        chApp.put("PUBG","");chApp.put("Vigo","");chApp.put("Viva Video","");chApp.put("Parellel Space","");
//        chApp.put("Like","");chApp.put("Live Me","");chApp.put("VMate","");chApp.put("NewsDog","");
//        chApp.put("Helo","");chApp.put("UC Browser","");chApp.put("Baidu Map","");chApp.put("Cam Scanner","");
//        chApp.put("SHAREit","Fuck");chApp.put("Oppo Store","");chApp.put("UDicitionary","");chApp.put("DU Battery Saver","");chApp.put("ES File Explorer","");
//        chApp.put("TurboVPN","");chApp.put("Zoom","Google Duo");chApp.put("AliExpress","");chApp.put("Clash of Kings","");chApp.put("Mafia City","");
//        chApp.put("Club Factory","");chApp.put("Shein","");chApp.put("Mobile Legends","");chApp.put("Castle Clash","");
//        chApp.put("Mi Store","");chApp.put("Wish","");chApp.put("Clock","Hey");chApp.put("flutter12","google meet");chApp.put("practiceGo","Neha");

        int siz=packageNames.size();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Log.d("Heya", Objects.requireNonNull(chApp.get("TikTok")));
//        }
        //////////////////----------------------------
        st=chApp.entrySet();
        for (int i=0;i<siz;i++)
        {
            int f=0;
            ApplicationInfo applicationInfo;
            PackageManager packageManager = mContext.getPackageManager();
            applicationInfo=packageManager.getApplicationInfo(packageNames.get(i),0);
            String tempName= (String) packageManager.getApplicationLabel(applicationInfo);
            for (Map.Entry<String,String > mp:st)
            {
                if(mp.getKey().equals(tempName))
                {
                    f=1;
//                    alt.add(mp.getValue());
                }
            }
            if(f==1)
            {
                ApkPackageName1.add(packageNames.get(i));
            }
        }


        ////
        return ApkPackageName1;
    }

    // Custom method to check the package name is not this app package name
    public boolean isSelfAppPackage(String packageName){
        String thisAppPackageName = mContext.getPackageName();
        if(thisAppPackageName.equals(packageName)){
            return true;
        }else {
            return false;
        }
    }

    // Custom method to determine an app is system app
    public boolean isSystemPackage(ResolveInfo resolveInfo){
        return ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    // Custom method to get application icon by package name
    public Drawable getAppIconByPackageName(String packageName){
        Drawable icon;
        try{
            icon = mContext.getPackageManager().getApplicationIcon(packageName);
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
            // Get a default icon
            icon = ContextCompat.getDrawable(mContext,R.drawable.ic_launcher_background);
        }
        return icon;
    }

    // Custom method to get application label by package name
    public String getApplicationLabelByPackageName(String packageName){
        PackageManager packageManager = mContext.getPackageManager();
        ApplicationInfo applicationInfo;
        String label = "Unknown";
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            if(applicationInfo!=null){
                label = (String)packageManager.getApplicationLabel(applicationInfo);
            }

        }catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//        label=label.toLowerCase();
        return label;
    }
    /////------------------
    public String getAlterNate(String packageName)
    {
////        PackageManager packageManager = mContext.getPackageManager();
////        ApplicationInfo applicationInfo;
////        String label = "Unknown";
////        try {
////            applicationInfo = packageManager.getApplicationInfo(packageName, 0);
////            if(applicationInfo!=null){
////                label = (String)packageManager.getApplicationLabel(applicationInfo);
////            }
////
////        }catch (PackageManager.NameNotFoundException e) {
////            e.printStackTrace();
////        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Log.d("Heya", Objects.requireNonNull(chApp.get("TikTok")));
//        }
        return "Sandeep";
    }
    //---------------------------------
}