package android.readfiledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ReadXmlCasi extends AppCompatActivity {
    Button btnRead, btnWrite;
    TextView tvCasi;
    ArrayList<CS> arrayListCS=new ArrayList<CS>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_xml_casi);
        addControl();
        addEvent();

    }
    public  void addControl()
    {
        btnRead=(Button) findViewById(R.id.btnRead) ;
        btnWrite=(Button) findViewById(R.id.btnWrite) ;
        tvCasi=(TextView) findViewById(R.id.tvCasi);
    }
    public  void addEvent()
    {
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kq="";
                try {
                    readDSCS();
                    for (CS cs:arrayListCS) {
                        kq=kq+cs.toString();
                    }
                     tvCasi.setText(kq);

                } catch (XmlPullParserException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //Hàm đọc xlm: đọc và gán dữ liệu vào cho arrayListCS của lơp
    public void readDSCS() throws XmlPullParserException, IOException {
        XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
        XmlPullParser parser    = fc.newPullParser();

        InputStream inputStream=getResources().getAssets().open("dscasi.xml");
        parser.setInput(inputStream,"UTF-8");

        int tagType = -1;
        String nodeName="";
        while(tagType!=XmlPullParser.END_DOCUMENT)
        {
            tagType=parser.next(); //đọc thẻ tiếp theo
            switch (tagType)
            {
                case XmlPullParser.END_DOCUMENT: break;
                case XmlPullParser.START_DOCUMENT:break;
                case XmlPullParser.START_TAG:
                    nodeName=parser.getName();
                    if(nodeName.equals("cs"))
                    {
                        CS cs = new CS();
                        while(tagType!=XmlPullParser.END_TAG)
                        {
                            tagType=parser.next();
                            nodeName=parser.getName();
                            if(tagType==XmlPullParser.START_TAG && nodeName.equals("hten"))
                            {
                                cs.setHten(parser.nextText());
                            }
                            else
                            if(tagType==XmlPullParser.START_TAG && nodeName.equals("qgia"))
                            {
                                cs.setQgia(parser.nextText());
                            }
                            else
                            if(tagType==XmlPullParser.START_TAG && nodeName.equals("loai")) {
                                cs.setLoai(parser.nextText());
                            }
                        }
                        arrayListCS.add(cs);
                    }
            }
        }
        inputStream.close();
    }
}