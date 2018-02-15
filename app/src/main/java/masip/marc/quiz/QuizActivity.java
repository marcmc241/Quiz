package masip.marc.quiz;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static masip.marc.quiz.R.id.btn_next;

public class QuizActivity extends AppCompatActivity {

    private final int[] ids_botons={//final vol dir constant, no es pot canviar
            R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4
    };

    private RadioGroup rgroup;

    //dades sobre preguntes
    private String[] questions;
    private String [][] answers;
    private int[] solutions;

    private int[] responses;//the answers from the user

    private int curr; //current question index

    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        curr = 0;
        loadQuestions();
        showQuestion();

        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {//button listener
            @Override
            public void onClick(View view) {
                nextQuestion();
            }
        });

        rgroup = (RadioGroup) findViewById(R.id.answers);
    }

    private void nextQuestion() {
        responses[curr] = getResponse();
        // si estic a la última pregunta, donar els resultats
        if (curr == questions.length -1){
            giveResults();
        }else {
            curr++;
            // si veiem que es la última hem de canviar el text del botó
            if (curr == questions.length -1){
                btn_next.setText(R.string.check);
            }
            showQuestion();
        }
    }

    private void giveResults() {
        int good = 0;
        int bad = 0;
        for (int i = 0; i < responses.length; i++) {
            if(responses[i] == solutions[i]){
                good++;
            }else {
                bad++;
            }
        }
        String msg = String.format("Correctes: %d / Incorrectes: %d", good, bad);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    private void loadQuestions() {
        Resources res = getResources();
        questions = res.getStringArray(R.array.questions);
        solutions = res.getIntArray(R.array.solutions);
        String[] answ = res.getStringArray(R.array.answers);
        answers = new String[answ.length][];//li assignem el  tamany de answ
        for (int i = 0; i < answ.length; i++){
            answers[i] = answ[i].split(";");
        }
        responses=new int[answ.length]; // en principi esta ple de zeros
    }

    private void showQuestion() {
        TextView question_text = (TextView) findViewById(R.id.question_text);
        question_text.setText(questions[curr]);

        for(int i = 0; i < answers[curr].length; i++){
            RadioButton rb = (RadioButton) findViewById(ids_botons[i]);
            rb.setText(answers[curr][i]);
        }
    }

    private void checkAnswer() {
        int quin = getResponse();
        if (quin != -1){
            if (quin == solutions[curr]){
                Toast.makeText(this, "Correcte!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Incorrecte...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private int getResponse() {
        int id_checked = rgroup.getCheckedRadioButtonId();
        int quin = -1;
        for (int i = 0; i < ids_botons.length; i++){
            if(id_checked == ids_botons[i]){
                quin = i+1;
            }
        }
        return quin;
    }

}
