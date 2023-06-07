package android.readfiledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadTxtFile extends AppCompatActivity {
    Button btnReadTxt, btnWriteTxt;
    TextView tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_txt_file);
        Intent intent=getIntent();
        //Gắn id cho các controls
        addControls();
        //Xử lý các sự kiên
        addEvents();
    }
    private void addControls()
    {
        btnReadTxt=(Button) findViewById(R.id.btnReadTxt);
        btnWriteTxt=(Button) findViewById(R.id.btnWriteTxt);
        tvContent=(TextView) findViewById(R.id.tvContent);
    }
    private void addEvents() {
        btnReadTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                   // InputStream in=getResources().getAssets().open("truyen_cuoi.txt");
                    InputStream in=openFileInput("dstruyen.txt");
                    int size = in.available();
                    byte[] data=new byte[size];
                    in.read(data);
                    in.close();
                    String st=new String(data); //chuyển mảng ký tự trong byte về thành chuỗi.
                    String[]dstruyen = st.split("====");

                    String chuoi = "Kết quả sau khi tách các phần tử truyện: \n";
                    for (String s:dstruyen ) {
                        chuoi=chuoi+ s + "\n";
                    }

                    tvContent.setText(chuoi);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                tvContent.setMovementMethod(new ScrollingMovementMethod());

//                try {
//                    InputStream in= getResources().getAssets().open("giaoduc.txt");
//                    int size=in.available();
//                    byte[]data=new byte[size];
//                    in.read(data);
//                    in.close();
//                    String st=new String(data);
//                    String[]pt=st.split("; ");
//                    tvContent.setText(Arrays.toString(pt));
//
//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException(e);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }


                //Đọc từ assets, không được ghi vào đây
//                try {
////                    InputStream ip=openFileInput("giaoduc.txt");
//                    byte[] data;
//                    try (InputStream ip = getResources().getAssets().open("giaoduc.txt")) {
//                        int sizeFile = ip.available();
//                        data = new byte[sizeFile];
//                        ip.read(data);
//                        ip.close();
//                    }
//
//                    String st = new String(data);
//                    tvContent.setText(st);
//
//
//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException(e);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }

                //-------------------------------------------
                //Đọc từ file internal
//                try {
//                    InputStream in=openFileInput("giaoduc.txt");
//                    int size=in.available();
//                    byte[]buffer=new byte[size];
//                    in.read(buffer);
//                    in.close();
//                    tvContent.setText(new String(buffer));
//
//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException(e);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }

            }
        });
        btnWriteTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OutputStream op=openFileOutput("danhsachsv.txt",MODE_APPEND);
                    String st="Ngân; Lan; Hoa; Lý; Xuân";
                    op.write(st.getBytes());

                    op.close();
                    Toast.makeText(getApplicationContext(),
                            "thành công",Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}