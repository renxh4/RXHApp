package com.example.jh.rxhapp.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import org.xml.sax.XMLReader;

import java.io.File;
import java.io.IOException;

import okhttp3.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HtmlTextView extends TextView {
    private String TAG = "HtmlTextView";
    private static boolean isDoCancel;
    private Context mContext;
    public static String TEAM_NEWS = "team_news";
    public static String POST_DETIAL = "post_detial";

    public HtmlTextView(Context context) {
        this(context, null);
    }

    public HtmlTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HtmlTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void setHtmlText(@NonNull final String body) {
        Spanned html;
        if (body.contains("img")) {
            setBody(body);
        } else {
            html = Html.fromHtml(body);
            setText(html);
        }
    }

    private void setBody(final String body) {
        Observable.create(new Observable.OnSubscribe<CharSequence>() {
            @Override
            public void call(Subscriber<? super CharSequence> subscriber) {
                setText("Loading...");
                CharSequence html = asyncSetBody(body);
                subscriber.onNext(html);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onNext(CharSequence html) {
                        setText(html);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    private CharSequence asyncSetBody(String url) {
        Html.ImageGetter imageGetter = new Html.ImageGetter() {

            @Override
            public Drawable getDrawable(String source) {
                Drawable drawable = null;
                try {
                    Response response = OkHttpManager.getInstanceSimaple(mContext).getAsyn(source);
                    byte[] bytes = response.body().bytes();
                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    opts.inJustDecodeBounds = true;
                    //BitmapFactory.decodeStream(inputStream, null, opts);
                    BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
                    int i = Utils.computeSampleSize(opts, -1, 1000 * 1000);
                    opts.inSampleSize = i;
                    opts.inJustDecodeBounds = false;
                    opts.inInputShareable = true;
                    opts.inPurgeable = true;
                    opts.inPreferredConfig = Bitmap.Config.ARGB_4444;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
                    //BitmapUtils.compressAndSaveBitmapToSDCard(bitmap, filename, 80);
                    drawable = new BitmapDrawable(mContext.getResources(), bitmap);

                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return drawable;
            }
        };
        return Html.fromHtml(url, imageGetter, new MyTagHandler());
    }

    private String checkUrl(String source) {
        String[] split = source.split("/");
        for (String sp : split) {
            if (sp.equals("attachments")) {
                String s = split[split.length - 1];
                return s;
            }
        }
        String s = split[split.length - 3];
        return s;
    }

    class MyTagHandler implements Html.TagHandler {

        @Override
        public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {

            if (tag.toLowerCase().equals("img")) {
                int len = output.length();
                ImageSpan[] images = output.getSpans(len - 1, len, ImageSpan.class);
                String imgURL = images[0].getSource();
                output.setSpan(new ImageClickSpan(mContext, imgURL), len - 1, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

        }
    }

    class ImageClickSpan extends ClickableSpan {

        private Context context;
        private String url;

        private ImageClickSpan(Context context, String url) {
            this.context = context;
            this.url = url;
        }

        @Override
        public void onClick(View widget) {

        }

    }
}
