package rajat.com.mathgame;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {
    TextView time, marks, q,score;

    Button[] Opt;
    Button start,restart;
    Random r;
    int emh=1;
    int att=0;
    int cor=0;
    int correctOption;
    int q1, q2, sign;
    Spinner s;
    ArrayList<Integer> optionList;
    ArrayAdapter<String> ar;
    void terminate(){
        makePaperInvisible();
        score.setText("Your Score is:"+cor+" / "+att);
        score.setVisibility(View.VISIBLE);
        restart.setVisibility(View.VISIBLE);
    }
    public void onRestart(View v){
        v.setVisibility(View.INVISIBLE);
        att=0;
        cor=0;
        emh=1;
        start.setVisibility(View.VISIBLE);
        s.setVisibility(View.VISIBLE);
        score.setVisibility(View.INVISIBLE);
    }
    void buttonCommon(){
        getQuestion();
        marks.setText(cor+"/"+att);
    }
    public void b4(View v){
        att++;
        if(correctOption==4){
            cor++;
        }
        buttonCommon();
    }
    public void b3(View v){
        att++;
        if(correctOption==3){
            cor++;
        }
        buttonCommon();
    }
    public void b2(View v){
        att++;
        if(correctOption==2){
            cor++;
        }
        buttonCommon();
    }
    public void b1(View v){
        att++;
        if(correctOption==1){
            cor++;
        }
        buttonCommon();
    }
    public void getQuestion(){
        int factor=att*emh+10;
        q1=r.nextInt(factor)+1*(att+1);
        q2=r.nextInt(factor)+1*(att+1);
        sign=r.nextInt(2);
        correctOption=r.nextInt(3)+1;
        int ans=0;
        if(sign==0){
            q.setText(q1+" + "+q2);
            ans=q1+q2;
            Opt[correctOption-1].setText(""+ans);
        }
        else {
            if(q1>q2){
                q.setText(q1+" - "+q2);
                ans=q1-q2;
                Opt[correctOption-1].setText(""+(ans));
            }
            else{
                q.setText(q2+" - "+q1);
                ans=q2-q1;
                Opt[correctOption-1].setText(""+(ans));
            }

        }
        optionList.add(ans);
        for(int i=0;i<4;i++){
            if(i==(correctOption-1)){
                continue;
            }
            int put=r.nextInt(ans*4+1);
            while(put==ans||optionList.contains(put)){
                put=r.nextInt(ans*4+5);
            }
            Opt[i].setText(""+put);
            optionList.add(put);
        }
        optionList.clear();
    }
    public void startClicked(View v){
        s.setVisibility(View.INVISIBLE);
        start.setVisibility(View.INVISIBLE);
        for(int i=0;i<4;i++){
            Opt[i].setVisibility(View.VISIBLE);
        }
        marks.setVisibility(View.VISIBLE);
        time.setVisibility(View.VISIBLE);
        q.setVisibility(View.VISIBLE);
        getQuestion();
        marks.setText(cor+"/"+att);
        new CountDownTimer(60000,1000){
            @Override
            public void onTick(long l) {
                time.setText("0:"+(l/1000));
                if(l/1000==30){
                    time.setTextColor(Color.RED);
                }
            }

            @Override
            public void onFinish() {
                time.setText("0:"+0);
                time.setTextColor(Color.BLACK);
                terminate();
            }
        }.start();
    }
    void makePaperInvisible(){
        for(int i=0;i<4;i++){
            Opt[i].setVisibility(View.INVISIBLE);
        }
        score.setVisibility(View.INVISIBLE);
        q.setVisibility(View.INVISIBLE);
        marks.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        optionList=new ArrayList<Integer>();
        Opt = new Button[4];
        Opt[0] = (Button) findViewById(R.id.Option1);
        Opt[1] = (Button) findViewById(R.id.Option2);
        Opt[2] = (Button) findViewById(R.id.Option3);
        Opt[3] = (Button) findViewById(R.id.Option4);
        start=(Button)findViewById(R.id.start);
        restart=(Button)findViewById(R.id.restart);
        restart.setVisibility(View.INVISIBLE);
        score=(TextView) findViewById(R.id.score);
        q = (TextView) findViewById(R.id.question);
        time = (TextView) findViewById(R.id.time);
        q=(TextView)findViewById(R.id.question);
        marks=(TextView)findViewById(R.id.marks);
        makePaperInvisible();
        r = new Random();
        s = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<String>(asList("Easy", "Medium", "Hard"));
        ar = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        s.setAdapter(ar);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                emh=++i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //s=r.nextInt(1);
    }


}
