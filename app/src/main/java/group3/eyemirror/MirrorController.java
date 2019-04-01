package group3.eyemirror;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MirrorController extends AppCompatActivity {
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("MWD");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mirror_controller);
        Button dailyBtn = (Button)findViewById(R.id.DailyBtn);
        Button weeklyBtn = (Button)findViewById(R.id.WeeklyBtn);
        Button monthlyBtn = (Button)findViewById(R.id.MonthlyBtn);
    }
    public void dailySubmit(View v){
        ref.setValue("001");
        startActivity(new Intent(MirrorController.this, MainActivity.class));
    }
    public void weeklySubmit(View v){
        ref.setValue("010");
        startActivity(new Intent(MirrorController.this, MainActivity.class));
    }
    public void monthlySubmit(View v){
        ref.setValue("100");
        startActivity(new Intent(MirrorController.this, MainActivity.class));
    }
}
