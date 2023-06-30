package android.readfiledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ReadJsonCS extends AppCompatActivity {
    Button btnReadJsCS;
    ListView lvCS;
    ArrayList<String> arrayListLV=new ArrayList<>();
    ArrayAdapter<String>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_json_cs);
        addControl();
        addEvent();
    }
    public void addControl()
    {
        btnReadJsCS=(Button) findViewById(R.id.btnLoadJsCS);
        lvCS=(ListView) findViewById(R.id.lvCS);

    }
    public void addEvent()
    {
        btnReadJsCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    arrayListLV=loadJsonCS();
                    adapter=new ArrayAdapter<String>(getApplicationContext(),
                            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arrayListLV);
                    lvCS.setAdapter(adapter);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public ArrayList<String> loadJsonCS() throws IOException, JSONException {
        ArrayList<String> arrayListCS = new ArrayList<>();
        //Đọc file bằng inputStream
        InputStream inputStream=getResources().getAssets().open("dscasi.json");
        int size = inputStream.available();
        byte[]data=new byte[size];
        inputStream.read(data);
        inputStream.close();
        String kqDoc = new String(data,"UTF-8");
        //Tách dữ liệu từng Object trong chuỗi kqDoc đưa về ArrayList
        JSONObject jsonObject= new JSONObject(kqDoc);
        JSONArray jsonArray = jsonObject.getJSONArray("dscs");
        for (int i=0; i<jsonArray.length();i++)
        {
            JSONObject ob=jsonArray.getJSONObject(i);
            String ht = ob.getString("hten");
            String qg = ob.getString("qgia");
            String loai = ob.getString("loai");
            String st = ht +"--" + qg + "--" +loai;
            arrayListCS.add(st);
        }

        return arrayListCS;
    }
}