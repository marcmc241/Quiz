package masip.marc.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    private final int[] ids_botons={//final vol dir constant, no es pot canviar
            R.id.answer1, R.id.answer2, R.id.answer3, R.id.answer4
    };
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

    }

}
