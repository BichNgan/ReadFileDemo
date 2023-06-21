package android.readfiledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ReadJson extends AppCompatActivity {

    Button btnReadJson;
    TextView tvKq;
    ArrayList<NhanVien> arrayListNhanVien = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_json);

    }
    public void addControls()
    {
        btnReadJson = (Button) findViewById(R.id.btnReadJson);
        tvKq=(TextView) findViewById(R.id.tvKq);
    }
    public void addEvent()
    {
        btnReadJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    arrayListNhanVien = readDSSNJson("dsnvjs");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public ArrayList<NhanVien> readDSSNJson(String filename) throws IOException, JSONException {
        ArrayList<NhanVien> arrayListNV=new ArrayList<>();
        //Đọc file đưa nội dung vào String
        InputStream inputStream=getResources().getAssets().open(filename);
        int size = inputStream.available();
        byte[]data=new byte[size];
        String st = new String(data,"UTF-8");
        //Tách các thông tin trong String vừa đọc lên đưa vào ds các đối tượng
        JSONObject jsonObject = new JSONObject(st);
        JSONArray jsonArray = jsonObject.getJSONArray("dsnv");
        for (int i=0;i<jsonArray.length();i++ ) {
            JSONObject nv = jsonArray.getJSONObject(i);
            String ten = nv.getString("ten");
            int ns = nv.getInt("ns");
            NhanVien nhanVien = new NhanVien(ten,ns);
            arrayListNV.add(nhanVien);
        }
        return  arrayListNV;
    }
}