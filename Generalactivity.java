package vimalesh.fire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Generalactivity extends AppCompatActivity {
    Button gen, scan,but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generalactivity);
        gen = (Button) findViewById(R.id.gen);
        scan = (Button) findViewById(R.id.scan);
        but=(Button)findViewById(R.id.button2);
        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gIntent = new Intent(Generalactivity.this, GeneratorActivity.class);
                startActivity(gIntent);
            }
        });
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rIntent = new Intent(Generalactivity.this, ReaderActivity.class);
                startActivity(rIntent);
            }
        });
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gIntent = new Intent(Generalactivity.this,display.class);
                startActivity(gIntent);
            }
        });
    }
}
