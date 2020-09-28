package thang.com.maybomnuoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private TextView txtNhietDo, txtDoAmDat, txtDoAmKhongKhi, txtSoLanBom;
    private LinearLayout btnMayBom, btnLed, btnController, xemdanhsach;
    private final static String TAG = "MainActivity";

    private boolean checkController = false;
    private FirebaseDatabase database;
    private DatabaseReference myRef, controller, trangthaimaybom, led;

    private ArrayList<danhsachmaybom> arrDS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNhietDo = (TextView) findViewById(R.id.txtNhietDo);
        txtDoAmDat = (TextView) findViewById(R.id.txtDoAmDat);
        txtDoAmKhongKhi = (TextView) findViewById(R.id.txtDoAmKhongKhi);
        txtSoLanBom = (TextView) findViewById(R.id.txtSoLanBom);

        btnController = (LinearLayout) findViewById(R.id.btnController);
        btnMayBom = (LinearLayout) findViewById(R.id.btnMayBom);
        btnLed = (LinearLayout) findViewById(R.id.btnLed);
        xemdanhsach = (LinearLayout) findViewById(R.id.xemdanhsach);

        database = FirebaseDatabase.getInstance();

        controller = database.getReference("controller");
        trangthaimaybom = database.getReference("trangthaimaybom");
        led = database.getReference("led");
        getNhietDo();
        getDoAmDat();
        getDoAmKhongKhi();
        getSoLanBom();
        getTrangThaiMayBom();
        getController();
        getLed();
        btnController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnController.getTag().equals("off")){
                    controller.setValue("on");
                    btnController.setBackgroundResource(R.drawable.button_bom_nuoc);
                    btnController.setTag("on");
                }else if(btnController.getTag().equals("on")){
                    controller.setValue("off");
                    btnController.setBackgroundResource(R.drawable.button_tat_may_bom);
                    btnController.setTag("off");
                }
            }
        });

        btnMayBom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkController == true){
                    if(btnMayBom.getTag().equals("off")){
                        trangthaimaybom.setValue("on");
                        btnMayBom.setBackgroundResource(R.drawable.button_bom_nuoc);
                        btnMayBom.setTag("on");
                    }
                    else if(btnMayBom.getTag().equals("on")){
                        trangthaimaybom.setValue("off");
                        btnMayBom.setBackgroundResource(R.drawable.button_tat_may_bom);
                        btnMayBom.setTag("off");
                    }
                }
            }
        });
        btnLed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnLed.getTag().equals("off")){
                    led.setValue("on");
                    btnLed.setBackgroundResource(R.drawable.button_bom_nuoc);
                    btnLed.setTag("on");
                }
                else if(btnLed.getTag().equals("on")){
                    led.setValue("off");
                    btnLed.setBackgroundResource(R.drawable.button_tat_may_bom);
                    btnLed.setTag("off");
                }
            }
        });
        xemdanhsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DanhSachBomActivity.class);
                startActivity(intent);
            }
        });

    }
    private void getSoLanBom(){
        arrDS = new ArrayList<>();
        myRef = database.getReference("databomnuoc");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                arrDS.clear();
                for(DataSnapshot data : dataSnapshot.getChildren()) {
                    danhsachmaybom sv = data.getValue(danhsachmaybom.class);
                    data.getKey();
                    arrDS.add(sv);
                }
                Collections.reverse(arrDS);
                txtSoLanBom.setText(arrDS.get(0).getSolanbom()+" lần");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    private void getNhietDo(){
        myRef = database.getReference("thongsokythuat/nhietdo");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                txtNhietDo.setText(value + " °C");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    private void getDoAmDat(){
        myRef = database.getReference("thongsokythuat/doamdat");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                txtDoAmDat.setText(value + " %");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    private void getDoAmKhongKhi(){
        myRef = database.getReference("thongsokythuat/doamkhongkhi");
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                txtDoAmKhongKhi.setText(value + " %");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    private void getTrangThaiMayBom(){
        // Read from the database
        trangthaimaybom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                if(value.equals("off")){
                    btnMayBom.setBackgroundResource(R.drawable.button_tat_may_bom);
                    btnMayBom.setTag("off");
                }
                if(value.equals("on")){
                    btnMayBom.setBackgroundResource(R.drawable.button_bom_nuoc);
                    btnMayBom.setTag("on");
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    private void getController(){
        // Read from the database
        led.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                if(value.equals("off")){
                    btnLed.setBackgroundResource(R.drawable.button_tat_may_bom);
                    btnLed.setTag("off");
                }
                if(value.equals("on")){
                    btnLed.setBackgroundResource(R.drawable.button_bom_nuoc);
                    btnLed.setTag("on");
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
    private void getLed(){
        controller.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                if(value.equals("off")){
                    btnController.setBackgroundResource(R.drawable.button_tat_may_bom);
                    btnController.setTag("off");
                    checkController = false;
                }
                if(value.equals("on")){
                    btnController.setBackgroundResource(R.drawable.button_bom_nuoc);
                    btnController.setTag("on");
                    checkController = true;
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
