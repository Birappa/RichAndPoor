package com.example.birappa.randp;

import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public ImageView user1Playcard;
    public ImageView user2Playcard;
    public Button button1;
    public Button button2;
    public Button resetButton;
    public Random random;
    public boolean flag;
    public int num=0;
    public boolean turn;
    public Integer[] user1_cards={
            R.drawable.c2,
            R.drawable.d2,
            R.drawable.s3,
            R.drawable.h3,
            R.drawable.c4,
            R.drawable.d4,
            R.drawable.h5,
            R.drawable.s5,
            R.drawable.c6,
            R.drawable.d6,
            R.drawable.h7,
            R.drawable.s7,
            R.drawable.c8,
            R.drawable.d8,
            R.drawable.h9,
            R.drawable.s9,
            R.drawable.c10,
            R.drawable.d10,
            R.drawable.hj,
            R.drawable.sj,
            R.drawable.cq,
            R.drawable.dq,
            R.drawable.hk,
            R.drawable.sk,
            R.drawable.ca,
            R.drawable.da
    };
    public Integer[] user2_cards={
            R.drawable.s2,
            R.drawable.h2,
            R.drawable.c3,
            R.drawable.d3,
            R.drawable.s4,
            R.drawable.h4,
            R.drawable.c5,
            R.drawable.d5,
            R.drawable.h6,
            R.drawable.s6,
            R.drawable.c7,
            R.drawable.d7,
            R.drawable.h8,
            R.drawable.s8,
            R.drawable.c9,
            R.drawable.d9,
            R.drawable.h10,
            R.drawable.s10,
            R.drawable.cj,
            R.drawable.dj,
            R.drawable.hq,
            R.drawable.sq,
            R.drawable.ck,
            R.drawable.dk,
            R.drawable.ha,
            R.drawable.sa
    };
    public int userOneScore=26;
    public int userSecondScore=26;
    public Integer[] played_cards=new Integer[52];
    public int count=0;
    ArrayList<Integer> playedCards;
    String temp;
    public TextView user1_score;
    public TextView user2_score;
    String temp1;
    public boolean isFlag;

    public ArrayList<Integer> h=new ArrayList<Integer>();
    public ArrayList<Integer> d=new ArrayList<Integer>();
    public ArrayList<Integer> c=new ArrayList<Integer>();
    public ArrayList<Integer> s=new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s.add(R.drawable.s4);
        s.add( R.drawable.s3);
        s.add( R.drawable.s2);
        s.add( R.drawable.s5);
        s.add( R.drawable.s6);
        s.add( R.drawable.s7);
        s.add( R.drawable.s8);
        s.add( R.drawable.s9);
        s.add( R.drawable.s10);
        s.add( R.drawable.sj);
        s.add( R.drawable.sq);
        s.add( R.drawable.sk);
        s.add( R.drawable.sa);

        d.add(R.drawable.d2);
        d.add(R.drawable.d3);
        d.add(R.drawable.d4);
        d.add(R.drawable.d5);
        d.add(R.drawable.d6);
        d.add(R.drawable.d7);
        d.add(R.drawable.d8);
        d.add(R.drawable.d9);
        d.add(R.drawable.d10);
        d.add(R.drawable.dj);
        d.add(R.drawable.dq);
        d.add(R.drawable.dk);
        d.add(R.drawable.da);

        s.add(R.drawable.s2);
        s.add(R.drawable.s3);
        s.add(R.drawable.s4);
        s.add(R.drawable.s5);
        s.add(R.drawable.s6);
        s.add(R.drawable.s7);
        s.add(R.drawable.s8);
        s.add(R.drawable.s9);
        s.add(R.drawable.s10);
        s.add(R.drawable.sj);
        s.add(R.drawable.sq);
        s.add(R.drawable.sk);
        s.add(R.drawable.sa);

        h.add(R.drawable.h2);
        h.add(R.drawable.h3);
        h.add(R.drawable.h4);
        h.add(R.drawable.h5);
        h.add(R.drawable.h6);
        h.add(R.drawable.h7);
        h.add(R.drawable.h8);
        h.add(R.drawable.h9);
        h.add(R.drawable.h10);
        h.add(R.drawable.hj);
        h.add(R.drawable.hq);
        h.add(R.drawable.hk);
        h.add(R.drawable.ha);

        isFlag=false;
        temp1="no";
        playedCards=new ArrayList<Integer>();
        flag=false;
        turn=false;
        random=new Random();
        user1Playcard=findViewById(R.id.user1Playcard);
        user2Playcard=findViewById(R.id.user2Playcard);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        resetButton=findViewById(R.id.reset);
        user1_score=findViewById(R.id.user1_score);
        user2_score=findViewById(R.id.user2_score);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(flag)
                   victory();
                else if(turn==false)
                rollDice(user1_cards);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag)
                    victory();
                else if(turn)
                 rollDice(user2_cards);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playedCards.removeAll(playedCards);
                flag=false;
                turn=false;
                userOneScore=26;
                userSecondScore=26;
                String currentText="User1 Score : ";
                currentText+=userOneScore;
                user1_score.setText(currentText);
                currentText="User2 Score : ";
                currentText+=userSecondScore;
                user2_score.setText(currentText);
                count=0;
                temp1="no";

                setImage();
            }
        });
    }
    public void rollDice(Integer[] cardArray){

        Integer value;

       while (true){
           num=random.nextInt(26)+1;
           value=cardArray[num-1];

            if (!playedCards.contains(value)){
                playedCards.add(value);
               // Toast.makeText(getApplicationContext(),playedCards.get(0),Toast.LENGTH_LONG).show();
                break;
            }
            if(playedCards.size()==52){
                flag=true;
                break;
            }
        }

        if(flag)
            victory();
        else {
                    if (turn) {
                                user2Playcard.setImageResource(cardArray[num - 1]);
                                turn = false;
                                count++;
                                userSecondScore--;
                                if(temp1.equals(checkCard(cardArray[num-1])))
                                {
                                        userSecondScore+=count;
                                        count=0;
                                        temp1="no";
                                       // scoreUpdate();
                                    isFlag=true;
                                }
                                else
                                    temp1=checkCard(cardArray[num-1]);
                    }
                    else {
                                user1Playcard.setImageResource(cardArray[num - 1]);
                                turn = true;
                                count++;
                                userOneScore--;
                                if(temp1.equals(checkCard(cardArray[num-1])))
                                {
                                    userOneScore+=count;
                                    count=0;
                                    temp1="no";
                                    //scoreUpdate();
                                    isFlag=true;
                                }
                                else temp1=checkCard(cardArray[num-1]);
                    }
            scoreUpdate();
                    if (isFlag){
                        final Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                setImage();
                            }
                        },1500);

                        isFlag=false;
                    }
        }
    }


    public void scoreUpdate(){
        String currentText="User1 Score : ";
            currentText+=userOneScore;
            user1_score.setText(currentText);

         currentText="User2 Score : ";
        currentText+=userSecondScore;
        user2_score.setText(currentText);

        }
        public void setImage(){
            user1Playcard.setImageResource(R.drawable.honor_heart);
            user2Playcard.setImageResource(R.drawable.honor_heart);
        }

    public void victory(){
        if (userOneScore>userSecondScore)
        Toast.makeText(getApplicationContext(),"User 1 is winner",Toast.LENGTH_LONG).show();
        else if(userSecondScore>userOneScore)
            Toast.makeText(getApplicationContext(),"User 2 is winner",Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplicationContext(),"Match Tied",Toast.LENGTH_LONG).show();
    }

    public String checkCard(Integer cardArray){

       if (d.contains(cardArray))
           return "diamond";
       else if (s.contains(cardArray))
                return "spade";
       else if (h.contains(cardArray))
           return "heart";
       else if (c.contains(cardArray))
           return "club";

        return "no";
    }
}
