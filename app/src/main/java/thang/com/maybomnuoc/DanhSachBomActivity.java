package thang.com.maybomnuoc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class DanhSachBomActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ArrayList<danhsachmaybom> arrDS;
    private AdapterDanhSachBom adapterDanhSachBom;
    private RecyclerView rclvdanhsachbom;
    private LinearLayoutManager layoutManager;
    private final static String TAG = "DanhSachActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bom);
        rclvdanhsachbom = (RecyclerView) findViewById(R.id.rclvdanhsachbom);
        rclvdanhsachbom.setHasFixedSize(true);
        layoutManager =  new LinearLayoutManager
                (this, LinearLayoutManager.VERTICAL, false);
        rclvdanhsachbom.setLayoutManager(layoutManager);
        arrDS = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("databomnuoc");
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
                adapterDanhSachBom.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        adapterDanhSachBom = new AdapterDanhSachBom(arrDS , getApplicationContext());
        rclvdanhsachbom.setAdapter(adapterDanhSachBom);
    }

}
