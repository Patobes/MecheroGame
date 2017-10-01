package tobes.mecherogame;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class PreguntaActivity extends AppCompatActivity {

    private TextView question_text;
    private TextView drink_text;
    private ImageView yes_image;
    private ImageView no_image;
    private ImageView coin_image;
    private Button next_button;
    private Button coin_button;
    private Button answered_button;
    private ImageButton change_question_button;

    private int question_changes = 0;
    private boolean isYes = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);

        question_text = (TextView)findViewById(R.id.tv_question);
        drink_text = (TextView)findViewById(R.id.tv_drink);
        yes_image = (ImageView)findViewById(R.id.iv_yes);
        no_image = (ImageView)findViewById(R.id.iv_no);
        coin_image = (ImageView)findViewById(R.id.iv_coin);
        next_button = (Button)findViewById(R.id.btn_next);
        coin_button = (Button)findViewById(R.id.btn_coin);
        answered_button = (Button)findViewById(R.id.btn_answered);
        change_question_button = (ImageButton)findViewById(R.id.ib_changequestion);

        drink(2);

        change_question_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //BBDD.nextQuestion()
                Random r = new Random();
                int i = r.nextInt(100);
                question_text.setText(String.valueOf(i));

                question_changes++;

                if(question_changes == 3)
                    change_question_button.setVisibility(View.INVISIBLE);

            }
        });

        answered_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                question_text.setVisibility(View.INVISIBLE);
                coin_button.setVisibility(View.VISIBLE);
                answered_button.setVisibility(View.INVISIBLE);

                drink_text.setVisibility(View.INVISIBLE);

                change_question_button.setVisibility(View.INVISIBLE);
                question_changes = 0;
            }
        });

        coin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                coin_image.clearAnimation();

                Random r = new Random();
                final int coin = r.nextInt(2);
                if (coin == 0){

                    isYes = false;
                    drink(222);
                    flipIt(coin_image,no_image);

                }else if (coin == 1){

                    isYes = true;
                    drink(111);
                    flipIt(coin_image,yes_image);

                };
                coin_button.setVisibility(View.INVISIBLE);

            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //BBDD.nextQuestion()
                Random r = new Random();
                int i = r.nextInt(100);
                question_text.setText(String.valueOf(i));

                question_text.setVisibility(View.VISIBLE);
                next_button.setVisibility(View.INVISIBLE);
                answered_button.setVisibility(View.VISIBLE);

                drink(5);

                change_question_button.setVisibility(View.VISIBLE);

                yes_image.setVisibility(View.INVISIBLE);
                no_image.setVisibility(View.INVISIBLE);

                coin_image.setVisibility(View.VISIBLE);

            }
        });

    }

    private void drink(int amount){
        drink_text.setText("¡¡Bebes " + amount + "!!");
        drink_text.setVisibility(View.VISIBLE);
    }

    private void flipIt(final View viewToFlip, final View viewAfter) {

        viewToFlip.setRotationY(0);

        viewToFlip.animate()
                .rotationY(720f)
                .setDuration(500)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        coin_image.setVisibility(View.INVISIBLE);
                        viewAfter.setVisibility(View.VISIBLE);
                        if (isYes){
                            question_text.setVisibility(View.VISIBLE);
                        }
                        next_button.setVisibility(View.VISIBLE);

                    }
                }).start();

    }
}
