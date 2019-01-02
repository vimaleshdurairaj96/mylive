package vimalesh.fire;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class display  extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout sliderLayout ;

    HashMap<String, String> HashMapForURL ;

    HashMap<String, Integer> HashMapForLocalRes ;

    private static final String TAG = "displayActivty";
    private FirebaseDatabase mref;
    //private ArrayList<String> mmlistview = new ArrayList<>();
    private ListView mlistview;
String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        sliderLayout = (SliderLayout)findViewById(R.id.slider);

        //Call this method if you want to add images from URL .
        AddImagesUrlOnline();

        //Call this method to add images from local drawable folder .
        //AddImageUrlFormLocalRes();

        //Call this method to stop automatic sliding.
        //sliderLayout.stopAutoCycle();

        for(String name : HashMapForURL.keySet()){

            TextSliderView textSliderView = new TextSliderView(display.this);

            textSliderView
                    .description(name)
                    .image(HashMapForURL.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());

            textSliderView.getBundle()
                    .putString("extra",name);

            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);

        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        sliderLayout.setCustomAnimation(new DescriptionAnimation());

        sliderLayout.setDuration(3000);

        sliderLayout.addOnPageChangeListener(display.this);
        mref = FirebaseDatabase.getInstance();
        mlistview = (ListView) findViewById(R.id.hai);
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        userid=currentFirebaseUser.getUid();

        /*final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mmlistview);
        mlistview.setAdapter(arrayAdapter);*/
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        //DatabaseReference mostafa = ref.child("user");

        /*mostafa.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                map.put("q",value);
                String e=map+"";
                Toast.makeText(getApplicationContext(),e,Toast.LENGTH_LONG).show();
                //Log.d(TAG, "Value is: " + value);


               /* String email = dataSnapshot.getValue(String.class);
                /*Integer h=dataSnapshot.getValue(Integer.class);
                String emasil=h.toString();
                Toast.makeText(getApplicationContext(),email,Toast.LENGTH_LONG).show();
                //do what you want with the email
            //    mmlistview.add(email);
                //mmlistview.add(key);
//                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        //DatabaseReference myRef1 = ref.child("user"); //Getting root reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
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
            uInfo.setname(ds.child(userid).getValue(UserInformation.class).getname()); //set the name
            uInfo.setbalance(ds.child(userid).getValue(UserInformation.class).getbalance()); //set the phone_num

            //display all the information
            //Log.d(TAG, "showData: name: " + uInfo.getname());
            //Log.d(TAG, "showData: email: " + uInfo.getemail());
            //Log.d(TAG, "showData: phone_num: " + uInfo.getPhone_num());

            ArrayList<String> array = new ArrayList<>();

            array.add("username"+uInfo.getname());
            array.add(uInfo.getbalance());
            //Integer a=new Integer(uInfo.getphonenum());
            //int e=uInfo.getphonenum()
            //String y=Integer.toString(uInfo.getphonenum());
            //String r=String.valueOf(e);
            //array.add(y);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
            mlistview.setAdapter(adapter);
            //adapter.notifyDataSetChanged();
        }
    }


        /*myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                mmlistview.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    UserInformation university = postSnapshot.getValue(UserInformation.class);
                    ArrayList<String> array  = new ArrayList<>();
                    array.add(university);
                    arrayAdapter.notifyDataSetChanged();

                    // here you can access to name property like university.name

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
        /*DatabaseReference myty=myRef1.child("hai");
        myRef1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //String key=dataSnapshot.getKey();
                String value=dataSnapshot.getValue(String.class);

               String h=Integer.toString(value);
                mmlistview.add(h);
                //mmlistview.add(key);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

    @Override
    protected void onStop() {

        sliderLayout.stopAutoCycle();

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this,slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void AddImagesUrlOnline(){

        HashMapForURL = new HashMap<String, String>();
        HashMapForURL.put("offer","https://firebasestorage.googleapis.com/v0/b/hellofire-7180f.appspot.com/o/WhatsApp%20Image%202018-02-11%20at%2010.21.09%20PM.jpeg?alt=media&token=4af15ba7-aba8-4d8e-8400-ba0fdb9f9d42");
        HashMapForURL.put("offer1","https://firebasestorage.googleapis.com/v0/b/hellofire-7180f.appspot.com/o/WhatsApp%20Image%202018-02-11%20at%2010.38.59%20PM.jpeg?alt=media&token=3c5c6951-944e-493f-8db8-d2d2e10cf0c6");

        HashMapForURL.put("vimalesh","https://firebasestorage.googleapis.com/v0/b/x-oxygen-170903.appspot.com/o/IMG-20161026-WA0019%20(2).jpg?alt=media&token=84bc9320-b265-4e9e-97d8-9cb23ad5555d");
        HashMapForURL.put("favourite star","https://firebasestorage.googleapis.com/v0/b/hellofire-7180f.appspot.com/o/WhatsApp%20Image%202018-02-11%20at%2010.21.09%20PM.jpeg?alt=media&token=4af15ba7-aba8-4d8e-8400-ba0fdb9f9d42");
        /*HashMapForURL.put("Eclair", "http://androidblog.esy.es/images/eclair-3.png");
        HashMapForURL.put("Froyo", "http://androidblog.esy.es/images/froyo-4.png");
        HashMapForURL.put("GingerBread", "http://androidblog.esy.es/images/gingerbread-5.png");*/
    }

    public void AddImageUrlFormLocalRes(){

        HashMapForLocalRes = new HashMap<String, Integer>();
        HashMapForLocalRes.put("offer",R.drawable.offer);
        HashMapForLocalRes.put("offer1",R.drawable.offer1);

        HashMapForLocalRes.put("vimalesh", R.drawable.cupcake);
        HashMapForLocalRes.put("favourite star", R.drawable.dotnut);
        /*HashMapForLocalRes.put("Eclair", R.drawable.eclair);
        HashMapForLocalRes.put("Froyo", R.drawable.froyo);
        HashMapForLocalRes.put("GingerBread", R.drawable.gingerbread);*/

    }
}

