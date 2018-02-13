package masip.marc.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private final int[] ids_botons={//final vol dir constant, no es pot canviar
            R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4
    };

    private int solution;
    private RadioGroup answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        TextView question_text = (TextView) findViewById(R.id.question_text);
        question_text.setText(R.string.question_content);

        String[] answer_texts = getResources().getStringArray(R.array.answer_tetxts);

        for(int i = 0; i < answer_texts.length; i++){
            RadioButton rb = (RadioButton) findViewById(ids_botons[i]);
            rb.setText(answer_texts[i]);
        }
        Button btn_check = (Button) findViewById(R.id.btn_check);
        btn_check.setOnClickListener(new View.OnClickListener() {//button listener
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });

        solution = getResources().getInteger(R.integer.solution);
        answers = (RadioGroup) findViewById(R.id.answers);
    }

    private void checkAnswer() {
        int id_checked = answers.getCheckedRadioButtonId();
        int quin = -1;
        for (int i = 0; i < ids_botons.length; i++){
            if(id_checked == ids_botons[i]){
                quin = i+1;
            }
        }
        if (quin != -1){
            if (quin == solution){
                Toast.makeText(this, "Correcte!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Incorrecte...", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
