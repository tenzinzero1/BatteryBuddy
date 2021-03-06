package com.receptix.batterybuddy.optimizeractivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.receptix.batterybuddy.R;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInAnimator;

import static com.receptix.batterybuddy.helper.Constants.Params.MINIMUM_INSTALLED_APPS;
import static com.receptix.batterybuddy.helper.Constants.Params.NUMBER_OF_SYSTEM_APPS_TO_SHOW;

public class OptimizerActivity extends AppCompatActivity {

    private static final String TAG = "Optimize Android";
    Context context;
    View view_optimize;
    MyOptimizerAdapter myOptimizerAdapter;
    ArrayList<OptimizerData> optimizerDataArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    PackageManager packageManager;
    Drawable appicon;
    Toolbar toolbar;
    NotificationManager notificationManager;
    private long mShortAnimationDuration = 300;
    private boolean isOptimizationInProgress = false;
    TextView textView_analysisProgressIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        view_optimize = getLayoutInflater().inflate(R.layout.activity_optimizer, null);
        setContentView(view_optimize);
        packageManager = this.getPackageManager();

        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(999);
        initView();
        setupToolBar(getString(R.string.poweroptimization));

        //get count of apps installed
        int count = 100;
        isOptimizationInProgress = true;
        startCountAnimation(count);

        /*new TestAsync().execute();*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showListOfInstalledApps();
            }
        }, 1500);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                performOptimization();
            }
        }, 5000);

    }

    private void setupToolBar(String title) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        TextView textViewTitle = (TextView) toolbar.findViewById(R.id.textViewTitle);
        textViewTitle.setText(title);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void performOptimization() {
        Log.e(TAG, "performOptimization");
        KillAllProcess();
        removeRecyclerItems();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(OptimizerActivity.this, SuccessOptimizerActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

    private void KillAllProcess() {
        //get list of installed apps
        List<ApplicationInfo> packages;
        packages = packageManager.getInstalledApplications(0);

        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        String myPackage = getApplicationContext().getPackageName();

        for (ApplicationInfo packageInfo : packages) {
            if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) continue;
            if (packageInfo.packageName.equals(myPackage)) continue;
            mActivityManager.killBackgroundProcesses(packageInfo.packageName);
        }
    }

    private void startCountAnimation(int count) {
        ValueAnimator animator = ValueAnimator.ofInt(0, count);
        animator.setDuration(1400);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView_analysisProgressIndicator.setText(animation.getAnimatedValue() + " % completed");
            }
        });
        animator.start();
    }

    private void showListOfInstalledApps() {
        Log.e(TAG, "showListOfInstalledApps");
        List<ApplicationInfo> applicationInfoList = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo applicationInfo : applicationInfoList) {
            // list only NON-SYSTEM apps
            if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                String packageLabel = applicationInfo.loadLabel(packageManager).toString();
                String packageName = applicationInfo.packageName;
                appicon = applicationInfo.loadIcon(packageManager);
                try {
                    appicon = packageManager.getApplicationIcon(packageName);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                OptimizerData singleApplicationData = new OptimizerData(appicon, packageName, packageLabel);
                Log.d("Label", singleApplicationData.getPackageLable());
                // add all applications to list besides our application
                if (!packageName.equalsIgnoreCase(context.getPackageName())) {
                    optimizerDataArrayList.add(singleApplicationData);
                    myOptimizerAdapter.notifyItemInserted(0);
                }
            }
        }

        if (optimizerDataArrayList.size() < MINIMUM_INSTALLED_APPS) {
            int numSystemApps = 0;
            for (ApplicationInfo applicationInfo : applicationInfoList) {
                if (numSystemApps < NUMBER_OF_SYSTEM_APPS_TO_SHOW) {
                    // list only NON-SYSTEM apps
                    if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                        String packageLabel = applicationInfo.loadLabel(packageManager).toString();
                        String packageName = applicationInfo.packageName;
                        appicon = applicationInfo.loadIcon(packageManager);
                        try {
                            appicon = packageManager.getApplicationIcon(packageName);
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                        OptimizerData singleApplicationData = new OptimizerData(appicon, packageName, packageLabel);
                        Log.d("Label", singleApplicationData.getPackageLable());
                        // add all applications to list besides our application
                        if (!packageName.equalsIgnoreCase(context.getPackageName())) {
                            optimizerDataArrayList.add(singleApplicationData);
                            myOptimizerAdapter.notifyItemInserted(0);
                            numSystemApps += 1;
                        }
                    }
                }
            }
        }


    }

    public void removeRecyclerItems() {
        if (optimizerDataArrayList.size() > 0) {
            for (int i = 0; i < optimizerDataArrayList.size(); i++) {
                myOptimizerAdapter.removeItem(i);
            }
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.animate()
                        .alpha(0f)
                        .setDuration(mShortAnimationDuration)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                recyclerView.setVisibility(View.INVISIBLE);
                            }
                        });
            }
        }, 500);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.optimizerecycler);
        FadeInAnimator animator = new FadeInAnimator(new OvershootInterpolator(1f));
        recyclerView.setItemAnimator(animator);
        recyclerView.getItemAnimator().setAddDuration(300);
        recyclerView.getItemAnimator().setRemoveDuration(300);
        myOptimizerAdapter = new MyOptimizerAdapter(optimizerDataArrayList, context);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 5));
        recyclerView.setAdapter(myOptimizerAdapter);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView_analysisProgressIndicator = (TextView) findViewById(R.id.applistTextView);

    }

    @Override
    public void onBackPressed() {
        if (!isOptimizationInProgress)
            finish();
    }
}
