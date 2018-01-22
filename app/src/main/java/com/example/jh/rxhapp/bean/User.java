package com.example.jh.rxhapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xiaohui on 2018/1/3.
 */

public class User implements Parcelable{

    public String id;
    public String name;
    //从序列化对象中创建原始对象
    protected User(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            //从序列化对象中创建原始对象
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            //创建指定长度的原始对象数组
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        //返回当前对象的内容的描述，如果含有文件描述返回1，否则返回0，一般都返回0
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //将当前对象写入序列化结构中，其中flag有俩种值，0或1，为1时标识当前对象需要作为返回值返回，不能
        //立即释放资源，一般为0
        dest.writeString(id);
        dest.writeString(name);
    }
}
