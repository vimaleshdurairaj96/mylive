package vimalesh.fire;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {
    private static final String TAG = "displayActivty";
    private FirebaseDatabase mref;
    //private ArrayList<String> mmlistview = new ArrayList<>();
    private ListView mlistview;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        mref = FirebaseDatabase.getInstance();
        mlistview = (ListView) findViewById(R.id.hai);
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        userid=currentFirebaseUser.getUid();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            UserInformation uInfo = new UserInformation();
            //          uInfo.setemail(ds.getValue(UserInformation.class).getemail()); //set the email
          //  uInfo.setname(ds.child(userid).getValue(UserInformation.class).getname()); //set the name
            uInfo.setbalance(ds.child(userid).getValue(UserInformation.class).getbalance()); //set the phone_num
String t=uInfo.getbalance();
            int y= Integer.parseInt(t);
            String h=Integer.toString(y);
            Toast.makeText(getApplicationContext(),h,Toast.LENGTH_LONG).show();
            //display all the information
            //Log.d(TAG, "showData: name: " + uInfo.getname());
            //Log.d(TAG, "showData: email: " + uInfo.getemail());
            //Log.d(TAG, "showData: phone_num: " + uInfo.getPhone_num());

            ArrayList<String> array = new ArrayList<>();

        }
    }



}
