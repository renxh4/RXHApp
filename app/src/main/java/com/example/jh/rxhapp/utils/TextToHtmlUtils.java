package com.example.jh.rxhapp.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.TextView;

import com.example.jh.rxhapp.Constants;

import org.xml.sax.XMLReader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import okhttp3.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TextToHtmlUtils {

    private Context mContext;
    private String TAG = "mmmTextToHtmlUtils";
    private static TextToHtmlUtils mInstance;
    private static HashMap<String, Bitmap> sPostDetialHashMap;
    private static HashMap<String, HashMap<String, Bitmap>> sHashMapHashMap;
    public static String TEAM_NEWS = "team_news";
    public static String POST_DETIAL = "post_detial";
    private static HashMap<String, Bitmap> sTeamNewsHashMap;

    private TextToHtmlUtils() {
    }

    public static TextToHtmlUtils getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpManager.class) {
                if (mInstance == null) {
                    mInstance = new TextToHtmlUtils();
                    sHashMapHashMap = new HashMap<>();
                    sPostDetialHashMap = new HashMap<>();
                    sTeamNewsHashMap = new HashMap<>();
                }
            }
        }
        return mInstance;
    }

    public void getBitmap(final ToHtml compressBitmap, final String url, final Context context,
                          final TextView textView, final String type) {
        this.mContext = context;
        Observable.create(new Observable.OnSubscribe<CharSequence>() {
            @Override
            public void call(Subscriber<? super CharSequence> subscriber) {
                //这里去加载图片
                textView.setText("loading");
                CharSequence getbitmap = getbitmap(url, type);
                subscriber.onNext(getbitmap);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onNext(CharSequence bitmap) {
                        compressBitmap.toFile(bitmap);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    private CharSequence getbitmap(String url, final String type) {
        Html.ImageGetter imageGetter = new Html.ImageGetter() {

            @Override
            public Drawable getDrawable(String source) {
                Drawable drawable = null;
                try {
                    String filename = checkUrl(source);
                    String saveFilePaht = Constants.IMAGE + filename;
                    File file = new File(saveFilePaht);
                    if (!file.exists()) {
                        Response response = OkHttpManager.getInstanceSimaple(mContext).getAsyn(source);
                        byte[] bytes = response.body().bytes();
                        BitmapFactory.Options opts = new BitmapFactory.Options();
                        opts.inJustDecodeBounds = true;
                        //BitmapFactory.decodeStream(inputStream, null, opts);
                        BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
                        int i = computeSampleSize(opts, -1, 1000 * 1000);
                        opts.inSampleSize = i;
                        opts.inJustDecodeBounds = false;
                        opts.inInputShareable = true;
                        opts.inPurgeable = true;
                        opts.inPreferredConfig = Bitmap.Config.ARGB_4444;
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, opts);
                        drawable = new BitmapDrawable(mContext.getResources(), bitmap);
                        if (type.equals(POST_DETIAL)) {
                            sPostDetialHashMap.put(filename, bitmap);
                            sHashMapHashMap.put(type, sPostDetialHashMap);
                        } else if (type.equals(TEAM_NEWS)) {
                            sTeamNewsHashMap.put(filename, bitmap);
                            sHashMapHashMap.put(type, sTeamNewsHashMap);
                        }
                    }
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    //matchDrawable(drawable);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return drawable;
            }
        };
        return Html.fromHtml(url, imageGetter, new MyTagHandler());
    }

    private boolean isRecycle(String filename, String type) {
        if (sHashMapHashMap.containsKey(type)) {
            HashMap<String, Bitmap> stringBitmapHashMap = sHashMapHashMap.get(type);
            if (stringBitmapHashMap.containsKey(filename)) {
                Bitmap bitmap1 = stringBitmapHashMap.get(filename);
                return bitmap1.isRecycled();
            }
        }
        return true;
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

    public static abstract class ToHtml {
        public abstract void toFile(CharSequence bitmap);
    }

    /**
     * 用来通知当解析器遇到无法识别的标签时该作出何种处理
     */
    class MyTagHandler implements Html.TagHandler {

        /**
         * 参数：
         * opening：为true时表示某个标签开始解析,为false时表示该标签解析完
         * tag:当前解析的标签
         * output:文本中的内容
         * xmlReader:xml解析器
         */
        @Override
        public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {

            if (tag.toLowerCase().equals("img")) {//解析<img/>标签（注意标签格式不是<img></img>）
                //  Logger.e("opening-->", opening + "");
                int len = output.length();
                ImageSpan[] images = output.getSpans(len - 1, len, ImageSpan.class);
                String imgURL = images[0].getSource();
                //添加点击事件
                output.setSpan(new ImageClickSpan(mContext, imgURL), len - 1, len, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            } else if (tag.equalsIgnoreCase("strike")) {//自定义解析<strike></strike>标签
                int len = output.length();
                if (opening) {//开始解析该标签，打一个标记
                    output.setSpan(new StrikethroughSpan(), len, len, Spannable.SPAN_MARK_MARK);
                } else {//解析结束，读出所有标记，取最后一个标记为当前解析的标签的标记（因为解析方式是便读便解析）
                    StrikethroughSpan[] spans = output.getSpans(0, len, StrikethroughSpan.class);
                    if (spans.length > 0) {
                        for (int i = spans.length - 1; i >= 0; i--) {
                            if (output.getSpanFlags(spans[i]) == Spannable.SPAN_MARK_MARK) {
                                int start = output.getSpanStart(spans[i]);
                                output.removeSpan(spans[i]);
                                if (start != len) {
                                    output.setSpan(new StrikethroughSpan(), start, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    class ImageClickSpan extends ClickableSpan {

        private Context context;
        private String url;

        public ImageClickSpan(Context context, String url) {
            this.context = context;
            this.url = url;
        }

        @Override
        public void onClick(View widget) {
        }

    }

    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    public static int computeInitialSampleSize(BitmapFactory.Options options,
                                               int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 :
                (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 :
                (int) Math.min(Math.floor(w / minSideLength),
                        Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) &&
                (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    //定义一个根据图片url获取InputStream的方法
    public byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024]; // 用数据装
        int len;
        while ((len = is.read(buffer)) != -1) {
            outstream.write(buffer, 0, len);
        }
        outstream.close();
        // 关闭流一定要记得。
        return outstream.toByteArray();
    }

    public void clearBitmap(String type) {
        for (String type1 : sHashMapHashMap.keySet()) {
            if (type1.equals(type)) {
                HashMap<String, Bitmap> stringBitmapHashMap = sHashMapHashMap.get(type1);
                for (String path : stringBitmapHashMap.keySet()) {
                    Bitmap bitmap = stringBitmapHashMap.get(path);
                    if (!bitmap.isRecycled() && bitmap != null) {
                        bitmap.recycle();
                    }
                }
            }
        }
    }
}
