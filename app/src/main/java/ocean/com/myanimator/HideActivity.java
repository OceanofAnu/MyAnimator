package ocean.com.myanimator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by lenovo on 2018/5/25.
 */

public class HideActivity extends Activity implements View.OnClickListener {
    private LinearLayout mHiddenView;
    private float mDensity;
    private int mHiddenViewHeight;


    private LinearLayout show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide);
        show = (LinearLayout) findViewById(R.id.show);
        show.setOnClickListener(this);

        mHiddenView = (LinearLayout) findViewById(R.id.hide_view);
        //获取像素密度
        mDensity = getResources().getDisplayMetrics().density;

        //获取布局高度
        mHiddenViewHeight = (int)(mDensity*40+0.5);

    }

    public void llClick(View view){
        if(mHiddenView.getVisibility() == View.GONE){
            animatorOpen(mHiddenView);
        }else{
            animatorClose(mHiddenView);
        }
    }


    private void animatorOpen(View view){
        view.setVisibility(View.VISIBLE);

        ValueAnimator animator = createDropAnimator(view,0,mHiddenViewHeight);
        animator.start();
    }
    private void animatorClose(final View view){
        int origHeight = view.getHeight();

        ValueAnimator animator = createDropAnimator(view,origHeight,0);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.start();
    }






    private ValueAnimator createDropAnimator(final View view,int start,int end){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start,end);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return valueAnimator;
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.show:
                llClick(mHiddenView);
                break;
        }
    }
}
