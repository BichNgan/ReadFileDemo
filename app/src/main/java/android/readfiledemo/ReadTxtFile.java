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
                //ĐỌc dữ liệu từ assets
                try {
                   // InputStream inputStream=getResources().getAssets().open("giaoduc.txt");
                  //Đọc file từ Device File Explorer
                    InputStream inputStream=openFileInput("dstruyen.txt");

                    int size=inputStream.available();
                    byte[] data=new byte[size];
                    inputStream.read(data);
                    inputStream.close();
                    String  stKq=new String(data);
                    tvContent.setText(stKq);
                    //thêm scrollbar


                    String []dsTruyen = stKq.split("====");
                    for(int i =0; i<dsTruyen.length; i++)
                    {
                        String []truyen = dsTruyen[i].split(";;")    ;
                        System.out.println("Tên truyện : "+ truyen[0] + " Nội dung truyện: " + truyen[1]);
                    }

                    tvContent.setMovementMethod(new ScrollingMovementMethod());



                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


//                try {
//                    //Đọc file từ assets
//                    //InputStream inputStream = getResources().getAssets().open("truyenvui.txt");
//                    //Đọc file từ internal storage của thiết bị
//                    InputStream inputStream=openFileInput("dstruyen.txt");
//                    int size=inputStream.available();
//                    byte[]data=new byte[size];
//                    inputStream.read(data);
//                    inputStream.close();
//
//                    String stKqDoc=new String(data);
//                    String[]dsTruyen=stKqDoc.split("====");
//                    for(int i=0;i<dsTruyen.length; i++)
//                    {
//                        //Tách tên và nội dung
//                        String [] thongTinMotTruyen = dsTruyen[i].split(";;");
//                        System.out.println("Tên truyện: " + thongTinMotTruyen[0] + " Nội dung truyen: " + thongTinMotTruyen[1]);
//                    }
//                    tvContent.setText(stKqDoc);
//                    tvContent.setMovementMethod(new ScrollingMovementMethod());
//
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }



            }
        });
        btnWriteTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OutputStream outputStream=openFileOutput("test.txt",MODE_APPEND);//ghi nối vào file nếu file có rồi
                    String st=tvContent.getText().toString();
                    outputStream.write(st.getBytes());
                    outputStream.close();

                    Toast.makeText(getApplicationContext(),"ghi thành công",Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


//                try {
//                    OutputStream op=openFileOutput("danhsachsv.txt",MODE_APPEND);
//                    String st="Ngân; Lan; Hoa; Lý; Xuân";
//                    op.write(st.getBytes());
//
//                    op.close();
//                    Toast.makeText(getApplicationContext(),
//                            "thành công",Toast.LENGTH_LONG).show();
//
//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException(e);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }

            }
        });
    }
}