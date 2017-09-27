package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jh.rxhapp.R;

import java.util.ArrayList;

public class PDFActivity extends BaseActivity implements View.OnClickListener {

    private Button mDoc;
    private Button mTxt;
    private Button mExcel;
    private Button mPpt;
    private Button mPdf;
    private ArrayList<String> mPath;

    @Override
    public int setMainView() {
        return R.layout.activity_pdf;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setToobarTitle("显示pdf等文件");
    }

    private void initView() {
        mDoc = (Button) findViewById(R.id.button_doc);
        mTxt = (Button) findViewById(R.id.button_txt);
        mExcel = (Button) findViewById(R.id.button_excel);
        mPpt = (Button) findViewById(R.id.button_ppt);
        mPdf = (Button) findViewById(R.id.button_pdf);
        mDoc.setOnClickListener(this);
        mTxt.setOnClickListener(this);
        mExcel.setOnClickListener(this);
        mPdf.setOnClickListener(this);
        mPpt.setOnClickListener(this);
        mPath = new ArrayList<>();
        mPath.add("/storage/emulated/0/test.docx");
        mPath.add("/storage/emulated/0/test.txt");
        mPath.add("/storage/emulated/0/test.xlsx");
        mPath.add("/storage/emulated/0/test.pptx");
        mPath.add("/storage/emulated/0/test.pdf");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_doc:
                ShowPDFActivity.show(this, mPath.get(0));
                break;
            case R.id.button_txt:
                ShowPDFActivity.show(this, mPath.get(1));
                Toast.makeText(this, "txt", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_excel:
                ShowPDFActivity.show(this, mPath.get(2));
                Toast.makeText(this, "excel", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_ppt:
                ShowPDFActivity.show(this, mPath.get(3));
                Toast.makeText(this, "ppt", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_pdf:
                ShowPDFActivity.show(this, mPath.get(4));
                Toast.makeText(this, "pdf", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}
