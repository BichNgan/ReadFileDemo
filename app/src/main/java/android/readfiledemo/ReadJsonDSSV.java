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

public class ReadJsonDSSV extends AppCompatActivity {

    Button btnLoadData;
    TextView tvDSSV;
    ArrayList<SinhVien>arrayListSinhVien=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_json_dssv);
        addControls();
        addEvent();

    }
    public void addControls()
    {
        btnLoadData=(Button) findViewById(R.id.btnLoadData);
        tvDSSV=(TextView) findViewById(R.id.tvDSSV);
    }
    public void addEvent()
    {
        btnLoadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ĐỌc dữ liệu từ file Json đưa lên String
                String kq="";
                InputStream inputStream= null;
                try {
                    inputStream = getResources().getAssets().open("dssv_json.json");
                    int size = inputStream.available();
                    byte[]data=new byte[size];
                    inputStream.read(data);
                    inputStream.close();
                    kq=new String(data,"UTF-8");

                    //Tách các object trong chuỗi kq (nội dung của json) về từng đối tượng
                    JSONObject jsonObject = new JSONObject(kq);
                    JSONArray jsonArray= jsonObject.getJSONArray("dssv");
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject ob=jsonArray.getJSONObject(i);
                        String mssv=ob.getString("mssv");
                        String hten = ob.getString("hten");
                        String ns = ob.getString("ns");
                        SinhVien sv= new SinhVien(mssv,hten,ns);
                        arrayListSinhVien.add(sv);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                String kqtv="";
                for (SinhVien s:arrayListSinhVien ) {
                    kqtv = kqtv + s.toString();
                }
                tvDSSV.setText(kqtv);

            }
        });
    }
}