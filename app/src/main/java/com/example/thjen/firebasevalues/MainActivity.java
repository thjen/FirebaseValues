package com.example.thjen.firebasevalues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        /** non Object **/
        databaseReference.child("Ho ten").setValue("Bùi Quốc Thiện");

        /** object **/
        SinhVien sinhVien = new SinhVien("Quốc Thiện", "Phủ Lý", 1998);
        databaseReference.child("Sinh Vien").setValue(sinhVien);

        /** map **/
        Map<String, Integer> map = new HashMap<>();
        map.put("Xe may", 2);
        databaseReference.child("Phuong tien").setValue(map);

        SinhVien sinhVien1 = new SinhVien("Tũn Hentai", "Hà Nội", 1997);
        /** push Khi update giá trị không bị mất giá trị cũ mà chỉ thêm vào giá trị mới và key **/
        databaseReference.child("Học Viên").push().setValue(sinhVien1);

        /** bắt sự kiện hoàn thành khi setValue **/
        databaseReference.child("Android").setValue("Kotlin", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                if ( databaseError == null ) {
                    Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
