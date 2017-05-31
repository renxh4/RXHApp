package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.utils.HtmlBody;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

public class HtmlToTextActivity extends BaseActivity {

    private HtmlTextView mHtmlTextView;
    private com.example.jh.rxhapp.utils.HtmlTextView mLocalhtmlTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        addMainView(R.layout.activity_html_to_text);
        mHtmlTextView = (HtmlTextView) findViewById(R.id.html_textview);
        mLocalhtmlTextView = (com.example.jh.rxhapp.utils.HtmlTextView) findViewById(R.id.local_html);
        mHtmlTextView.setHtml(HtmlBody.Html, new HtmlHttpImageGetter(mHtmlTextView));
        mLocalhtmlTextView.setHtmlText(HtmlBody.Html);
    }
}
