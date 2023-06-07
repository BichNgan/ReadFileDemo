package android.readfiledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class ReadXMLFile extends AppCompatActivity {
    Button btnReadXml, btnWriteXml;
    TextView tvContent2;
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
        btnReadXml=(Button) findViewById(R.id.btnReadXml);
        btnWriteXml=(Button) findViewById(R.id.btnWriteXml);
        tvContent2=(TextView) findViewById(R.id.tvContent2);
    }
    private void addEvents() {
        btnReadXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Đọc từ file internal, dùng kỹ thuật SAX (Simple API for XML , scan the document)
                XmlPullParserFactory fc= null;
                try {
                    fc = XmlPullParserFactory.newInstance();
                } catch (XmlPullParserException e) {
                    throw new RuntimeException(e);
                }
                try {
                    XmlPullParser parser=fc.newPullParser();
                } catch (XmlPullParserException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        btnWriteXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
}