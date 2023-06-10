package android.readfiledemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;


public class ReadXMLFile extends AppCompatActivity {
    Button btnReadXml, btnWriteXml;
    TextView tvContent2;
    ArrayList<NV> arrayListNV=new ArrayList<>();
    ArrayList<SV> arrayListSV=new ArrayList<>();

    ArrayList<SP>arrayListSP = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_xmlfile);
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
//                try {
//                    arrayListSP=readDSSP("dssp.xml");
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                } catch (XmlPullParserException e) {
//                    throw new RuntimeException(e);
//                }
//                String kq="";
//                for (SP sp: arrayListSP) {
//                    kq=kq+sp.printSP();
//                }

                readDSNV();
                String kq="";
                for (NV nv: arrayListNV) {
                    kq=kq+nv.printNV();
                }
                tvContent2.setText(kq);
            }
        });

        btnWriteXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            writeDSNV();

            }
        });
    }

    public ArrayList<SP> readDSSP(String fileName) throws IOException, XmlPullParserException {
        ArrayList<SP> arrayListSP = new ArrayList<>();

        //InputStream inputStream = getResources().getAssets().open(fileName);
        InputStream inputStream=openFileInput("dssp.xml")





        XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = xmlPullParserFactory.newPullParser();
        parser.setInput(inputStream, "UTF-8");

        int tagType = -1;
        String nodeName;

        while (tagType != XmlPullParser.END_DOCUMENT)//chưa kết thúc file
        {
            tagType = parser.next();
            switch (tagType)
            {
                case XmlPullParser.START_DOCUMENT:
                    break;
                case XmlPullParser.END_DOCUMENT:
                    break;
                case XmlPullParser.START_TAG: //thẻ mở cho 1 dữ liệu
                    nodeName=parser.getName();
                    if(nodeName.equals("sp")) { //kiểm tra đúng tag mình cần xử lý không?
                        SP sp=new SP();
                        while(tagType!=XmlPullParser.END_TAG)
                        {
                            tagType=parser.next();
                            nodeName=parser.getName();
                            if(tagType==XmlPullParser.START_TAG && nodeName.equals("msp")){
                                sp.setMsp(parser.nextText());
                            }
                            else
                            if(tagType==XmlPullParser.START_TAG && nodeName.equals("tensp")){
                                sp.setTensp(parser.nextText());
                            }
                            else
                            if(tagType==XmlPullParser.START_TAG && nodeName.equals("dgia")){
                                sp.setDgia(Integer.parseInt(parser.nextText()));
                            }
                        }
                        arrayListSP.add(sp);
                    }
            }

            inputStream.close();


        }
        return arrayListSP;
    }

//    public ArrayList<SV> readDSSV(String fileNameXml) throws IOException, XmlPullParserException {
//        ArrayList<SV> dssv = new ArrayList<>();
//
//        //Đọc file từ assest
//        //InputStream inputStream=getResources().getAssets().open(fileNameXml);
//
//        //Đọc file từ Device File Explore
//        InputStream inputStream=openFileInput(fileNameXml);
//
//        XmlPullParserFactory xmlPullParserFactory=XmlPullParserFactory.newInstance();
//        XmlPullParser parser = xmlPullParserFactory.newPullParser();
//
//        parser.setInput(inputStream,"UTF-8");
//
//        int tagType=-1;
//        String nodeName;
//
//        while(tagType!=XmlPullParser.END_DOCUMENT)
//        {
//            tagType=parser.next(); //đọc từng dòng
//            switch (tagType)
//            {
//                case XmlPullParser.START_DOCUMENT:
//                    break;
//                case XmlPullParser.END_DOCUMENT:
//                    break;
//                case XmlPullParser.START_TAG:
//                {
//                    nodeName=parser.getName(); //lấy tên của thẻ
//                    if(nodeName.equals("sv"))
//                    {
//                        SV sv=new SV();
//                        while(tagType!= XmlPullParser.END_TAG) //chưa kết thúc 1 sv
//                        {
//                            tagType=parser.next();
//                            nodeName=parser.getName();
//                            if(nodeName.equals("mssv"))
//                                sv.setMssv(parser.getText());
//                            else
//                                if(nodeName.equals("hten"))
//                                    sv.setHten(parser.getText());
//                                else
//                                    if(nodeName.equals("nsinh"))
//                                        sv.setNsinh(Integer.parseInt(parser.getText()));
//                        }
//                        arrayListSV.add(sv);
//                    }
//                }
//            }
//        }
//        return arrayListSV;
//    }

    //Hàm đọc dữ liệu từ file xml đưa vào   arrayListNV
    public void readDSNV()
    {
        try {
            XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
            XmlPullParser parser=fc.newPullParser();

            //Lấy đường dẫn từ file external trong SDCard
            //String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            //String xmlFile = filePath + "/MyFiles/dsnv.xml";

           // InputStream inputStream = getResources().getAssets().open("dsnv.xml");
            InputStream inputStream=openFileInput("dsnv.xml");
            parser.setInput(inputStream,"UTF-8");

            int tagType=-1;
            String nodeName;

            //đọc file
            while(tagType!=XmlPullParser.END_DOCUMENT)//chưa kết thúc file
            {
                tagType=parser.next(); //duyệt từng dòng trong file
                switch (tagType)
                {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG: //thẻ mở cho 1 dữ liệu
                        nodeName=parser.getName();
                        if(nodeName.equals("nv")) { //kiểm tra đúng tag mình cần xử lý không?
                            NV nv=new NV();
                            while(tagType!=XmlPullParser.END_TAG)
                            {
                                tagType=parser.next();
                                nodeName=parser.getName();
                                if(tagType==XmlPullParser.START_TAG && nodeName.equals("msnv")){
                                    nv.setMsnv(parser.nextText());
                                }
                                else
                                if(tagType==XmlPullParser.START_TAG && nodeName.equals("ten")){
                                    nv.setTen(parser.nextText());
                                }
                                else
                                if(tagType==XmlPullParser.START_TAG && nodeName.equals("sdt")){
                                    nv.setSdt(parser.nextText());
                                }
                                else
                                if(tagType==XmlPullParser.START_TAG && nodeName.equals("chucvu")) {
                                    nv.setChucvu(parser.nextText());
                                }
                            }
                            arrayListNV.add(nv);
                        }
                }
            }
            inputStream.close();
        } catch (XmlPullParserException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Hàm ghi ds nhân viên xuống file xml
    public void writeDSNV()
    {
        try {
            OutputStream outputStream=openFileOutput("dsnv2.xml",MODE_PRIVATE);

            XmlSerializer xmlSerializer= Xml.newSerializer();
            StringWriter stringWriter=new StringWriter();
            xmlSerializer.setOutput(stringWriter);
            xmlSerializer.startDocument("UTF-8",true);

            xmlSerializer.startTag(null,"dsnv");
            for(int i=0;i<arrayListNV.size();i++)
            {
                NV nv=arrayListNV.get(i);
                xmlSerializer.startTag(null,"nv");

                xmlSerializer.startTag(null,"msnv");
                xmlSerializer.text(nv.getMsnv());
                xmlSerializer.endTag(null,"msnv");

                xmlSerializer.startTag(null,"ten");
                xmlSerializer.text(nv.getTen());
                xmlSerializer.endTag(null,"ten");

                xmlSerializer.startTag(null,"sdt");
                xmlSerializer.text(nv.getSdt());
                xmlSerializer.endTag(null,"sdt");

                xmlSerializer.startTag(null,"chucvu");
                xmlSerializer.text(nv.getChucvu());
                xmlSerializer.endTag(null,"chucvu");

                xmlSerializer.endTag(null,"nv");
            }
            xmlSerializer.endTag(null,"dsnv");
            xmlSerializer.endDocument();
            xmlSerializer.flush();

            String dataWrite = stringWriter.toString();
            outputStream.write(dataWrite.getBytes());
            outputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}