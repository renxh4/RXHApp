package com.example.jh.rxhapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.example.jh.rxhapp.Constants;
import com.example.jh.rxhapp.bean.CompressBean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import cn.finalteam.galleryfinal.model.PhotoInfo;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CompressBmpToFile {

    public static final String NOT_COMPRESS = "not_compress";
    public static final String KEY = "key";


    public static void getFile(final CompressBitmap compressBitmap, final List<PhotoInfo> list,
                               final String name, final Context context) {
        Observable<HashMap<String, CompressBean>> observable = Observable.create(new Observable.OnSubscribe<HashMap<String, CompressBean>>() {
            @Override
            public void call(Subscriber<? super HashMap<String, CompressBean>> subscriber) {
                HashMap<String, CompressBean> map = getFile1(list, name, context);
                subscriber.onNext(map);
                subscriber.onCompleted();
            }
        });
        observable.subscribeOn(Schedulers.io());
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<HashMap<String, CompressBean>>() {
            @Override
            public void onNext(HashMap<String, CompressBean> map) {
                compressBitmap.toFile(map);
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

    private static HashMap<String, CompressBean> getFile1(List<PhotoInfo> list, String name, Context context) {
        HashMap<String, CompressBean> map = new HashMap<>();
        File file1 = null;
        if (list.size() > 0) {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(list.get(0).getPhotoPath(), opts);
            int i = Utils.computeSampleSize(opts, -1, 1000 * 1000);
            opts.inSampleSize = i;
            opts.inJustDecodeBounds = false;
            opts.inInputShareable = true;
            opts.inPurgeable = true;
            opts.inPreferredConfig = Bitmap.Config.ARGB_4444;
            Bitmap bitmap = BitmapFactory.decodeFile(list.get(0).getPhotoPath(), opts);
            int byteCount = bitmap.getByteCount();
            File dir = new File(Constants.IMAGE);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            long l = System.currentTimeMillis();
            String time = String.valueOf(l);
            String path = Constants.IMAGE + time + name;
            file1 = new File(path);
            compressBmpToFile(bitmap, file1, context);
            CompressBean compressBean = new CompressBean(file1, path);
            map.put(KEY, compressBean);
        }
        return map;
    }

    private static void compressBmpToFile(final Bitmap bitmap, final File file1, final Context context) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int options = 80;
            bitmap.compress(Bitmap.CompressFormat.PNG, options, baos);
            while (baos.toByteArray().length / 1024 > 0.5 * 1024) {
                baos.reset();
                options -= 10;
                bitmap.compress(Bitmap.CompressFormat.PNG, options, baos);
            }
            FileOutputStream fos = new FileOutputStream(file1);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static abstract class CompressBitmap {
        public abstract void toFile(HashMap<String, CompressBean> map);
    }
}
