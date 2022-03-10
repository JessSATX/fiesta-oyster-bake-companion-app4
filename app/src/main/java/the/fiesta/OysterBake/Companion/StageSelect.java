package the.fiesta.OysterBake.Companion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StageSelect extends AppCompatActivity {

    public static String day;
    Button stage1, stage2, stage3, stage5, stage6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_select);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        day = (String) bundle.get("Day");

        stage1 = findViewById(R.id.Stage1);
        stage2 = findViewById(R.id.Stage2);
        stage3 = findViewById(R.id.Stage3);
        stage5 = findViewById(R.id.Stage5);
        stage6 = findViewById(R.id.Stage6);

        // change button names depending on day

        if (day.equals("1")) {

            stage1.setText("Classic Rock Stage Presented by Bud Light, KONO 101.1 & The Eagle 106.7");
            stage2.setText("Country Stage Presented by Bud Light & Y100");
            stage3.setText("Tejano & Latin Stage Presented By Bud Light & KXTN 107.5");
            stage5.setText("R&B & Hip Hop Stage Presented by Bud Light & KBBT 98.5");
            stage6.setText("Children’s & Family Stage");

        } else {

            stage1.setText("Rock Stage Presented by Bud Light & KISS 99.5");
            stage2.setText("Country Stage Presented by Bud Light & KJ 97");
            stage3.setText("Tejano & Latin Stage Presented By Bud Light & KXTN 107.5");
            stage5.setText("Variety Stage Presented by Bud Light & 96.1 NOW");
            stage6.setText("Children’s & Family Stage");
        }

        stage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToSchedule = new Intent(StageSelect.this, ScheduleList.class);

                goToSchedule.putExtra("Stage", "1");
                goToSchedule.putExtra("day", day);

                startActivity(goToSchedule);
            }
        });

        stage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToSchedule = new Intent(StageSelect.this, ScheduleList.class);

                goToSchedule.putExtra("Stage", "2");
                goToSchedule.putExtra("day", day);

                startActivity(goToSchedule);
            }
        });
        stage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToSchedule = new Intent(StageSelect.this, ScheduleList.class);

                goToSchedule.putExtra("Stage", "3");
                goToSchedule.putExtra("day", day);

                startActivity(goToSchedule);
            }
        });

        stage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToSchedule = new Intent(StageSelect.this, ScheduleList.class);

                goToSchedule.putExtra("Stage", "5");
                goToSchedule.putExtra("day", day);

                startActivity(goToSchedule);
            }
        });

        stage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToSchedule = new Intent(StageSelect.this, ScheduleList.class);

                goToSchedule.putExtra("Stage", "4");
                goToSchedule.putExtra("day", day);

                startActivity(goToSchedule);
            }
        });
    }
}