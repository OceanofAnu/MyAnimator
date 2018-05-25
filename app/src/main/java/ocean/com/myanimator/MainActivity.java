package ocean.com.myanimator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageView;
    private ImageView top;
    private ImageView bottom;
    private ImageView left;
    private ImageView right;
    boolean flag = true;

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();

        tvTimer(textView);
    }

    private void initView(){
        imageView = (ImageView) findViewById(R.id.click);
        top = (ImageView) findViewById(R.id.top);
        bottom = (ImageView) findViewById(R.id.bottom);
        left = (ImageView) findViewById(R.id.left);
        right = (ImageView) findViewById(R.id.right);

        textView = (TextView) findViewById(R.id.text);
        imageView.setOnClickListener(this);
        if(!flag){

        }


    }


    private void startAni(){
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(
                imageView,
                "alpha",
                1F,
                0.5F);

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(
                bottom,
                "translationY",
                200F);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(
                left,
                "translationX",
                -200F);

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(
                top,
                "translationY",
                -200F);

        ObjectAnimator animator4 = ObjectAnimator.ofFloat(
                right,
                "translationX",
                200F);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator0,
                animator1,
                animator2,
                animator3,
                animator4);

        set.start();
        flag = false;
    }

    private void endAni(){
        ObjectAnimator animator0 = ObjectAnimator.ofFloat(
                imageView,
                "alpha",
                0.5F,
                1F);

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(
                bottom,
                "translationY",
                -200F,
                0);

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(
                left,
                "translationX",
                200F,
                0);

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(
                top,
                "translationY",
                200F,
                0);

        ObjectAnimator animator4 = ObjectAnimator.ofFloat(
                right,
                "translationX",
                -200F,
                0);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(animator0,
                animator1,
                animator2,
                animator3,
                animator4);

        set.start();
        flag = true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.click:
                if(flag){
                    startAni();


                    top.setOnClickListener(this);
                    bottom.setOnClickListener(this);
                    left.setOnClickListener(this);
                    right.setOnClickListener(this);

                }else {
                    endAni();
                    top.setClickable(false);
                    bottom.setClickable(false);
                    left.setClickable(false);
                    right.setClickable(false);
                }
                break;
            case R.id.left:
                Intent intent = new Intent(this,HideActivity.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this,v.getId()+"",Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private void tvTimer(final View view){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ((TextView) view).setText("$"+(Integer)animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(3000);
        valueAnimator.start();


    }
}
