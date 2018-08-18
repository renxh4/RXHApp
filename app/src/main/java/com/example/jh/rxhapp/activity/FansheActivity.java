package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jh.rxhapp.R;
import com.example.jh.rxhapp.fanshe.Student;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Logger;

public class FansheActivity extends BaseActivity {

    @Override
    public int setMainView() {
        return R.layout.activity_fanshe;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fanshe();
        //getclass();
        //getGouZao();
        // getFiled();
        getFangfa();
    }


    /**
     * 获取一个class对象的方式
     * 1.调用Object类的getClass():任何类都会继承此方法；
     * 2.任何的数据类型(包括基本类型)都有一个：静态的class属性：
     * Student.class;
     * int.class
     * 3.调用Class类的静态方法forName(String 全名限定的类名):(常用)
     * <p>
     * 通过这个Class对象，可以获取Student类内部的成员属性、构造方法、成员方法的一些信息，
     * 并能够调用它们；
     */
    private void getclass() {
        //1
        Student student = new Student();
        Class<? extends Student> aClass1 = student.getClass();

        //2
        Class<Student> aClass2 = Student.class;

        //3
        Class<?> aClass3 = null;
        try {
            aClass3 = Class.forName("com.example.jh.rxhapp.fanshe.Student");
        } catch (ClassNotFoundException e) {

        }

        Log.d("mmm", String.valueOf(aClass1 == aClass2));
        Log.d("mmm", String.valueOf(aClass1 == aClass3));
    }


    /**
     * 获取构造方法，并调用：
     * <p>
     * Class类：
     * --批量的：
     * Constructor[] getConstructors() ：获取所有的"公有构造方法"
     * Constructor[] getDeclaredConstructors() :获取全部(包括私有)的构造方法；
     * --获取某个构造方法
     * Constructor getConstructor(Class ... parameterTypes) ：获取指定的"公有构造方法"；
     * Constructor getDeclaredConstructor(Class ... parameterTypes) ：获取指定的构造方法(包括私有的)；
     * <p>
     * --调用某个构造方法：
     * Constructor的 Object newInstance(Object... initargs) :调用指定构造方法，并实例化此类的对象；
     * <p>
     * --暴力访问：如果私有成员，需要设置暴力访问；
     * Constructor的setAccessible(true):不进行权限检查；
     */
    private void getGouZao() {
        //获取class对象
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.example.jh.rxhapp.fanshe.Student1");
            System.out.println("获取所有公有构造方法");
            Constructor<?>[] constructors = aClass.getConstructors();
            for (Constructor c : constructors) {
                System.out.println(c);
            }

            System.out.println("获取所有构造方法(包括私有)");
            Constructor<?>[] declaredConstructors = aClass.getDeclaredConstructors();
            for (Constructor c : declaredConstructors) {
                System.out.println(c);
            }

            System.out.println("获取指定的公有构造方法");
            //获取参数为char的公有构造方法
            Constructor<?> constructor = aClass.getConstructor(char.class);
            //调用该构造方法
            Object o = constructor.newInstance('a');


            System.out.println("获取指定的构造方法(包括私有)");
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class, int.class);
            //设置暴力访问
            declaredConstructor.setAccessible(true);
            Object hahah = declaredConstructor.newInstance("hahah", 11);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取成员属性，并调用：
     * <p>
     * Class类的方法：
     * --批量的：
     * Field[] getFields()：获取所有"公有属性"；
     * Field[] getDeclaredFields() :获取所有成员属性(包括私有)：
     * --获取单个成员属性
     * Field getField(String name) :获取指定的"公有属性"；
     * Field getDeclaredField(String name) :获取指定的属性，包括私有的；
     * <p>
     * --为成员属性赋值：(注意：1.一定要先实例化此类对象；2.访问私有属性前，要设置暴力访问)
     * Field的 void set(Object obj, Object value)
     */
    private void getFiled() {
        //首先获取class对象
        try {
            Class<?> aClass = Class.forName("com.example.jh.rxhapp.fanshe.Student3");
            System.out.println("获取所有的公有成员属性");
            Field[] fields = aClass.getFields();
            for (Field f : fields) {
                System.out.println(f);
            }

            System.out.println("获取所有的成员属性(包括私有)");
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field f : declaredFields) {
                System.out.println(f);
            }

            //获取对象
            Object o = aClass.getConstructor().newInstance();
            System.out.println("获取公有属性并调用");
            Field name = aClass.getField("name");
            System.out.println("赋值前打印" + o);
            name.set(o, "刘德华");
            System.out.println("赋值后打印" + o);

            System.out.println("获取所有属性并调用");
            Field age = aClass.getDeclaredField("age");
            //设置暴力访问
            age.setAccessible(true);
            //给属性赋值
            age.set(o, 99);
            System.out.println("赋值后打印" + o);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取成员方法：
     * <p>
     * Class类的方法：
     * --批量的：
     * Method[] getMethods()：获取所有"公有方法"(包括继承的)
     * Method[] getDeclaredMethods() :获取所有成员方法(包括私有)：
     * --获取单个成员方法
     * Method getMethod(String name, Class... parameterTypes) :获取指定的"公有方法"；
     * Method getDeclaredMethod(String name, Class... parameterTypes) :获取指定的方法，包括私有的；
     * <p>
     * --调用方法：(注意：1.要先创建此类的对象;2.调用私有方法前，要先设置暴力访问)
     * Method:
     * Object invoke(Object obj, Object... args) :调用Method所代表的方法：
     * 返回值：此Method对象调用所代表的方法，所获取的返回值；
     * 形参：obj:方法所属对象；
     * args:Method所代表的那个方法的实参列表；
     */
    private void getFangfa() {
        //获取class对象
        try {
            Class<?> aClass = Class.forName("com.example.jh.rxhapp.fanshe.Student2");

            System.out.println("获取所有的公有方法(包括继承)");
            Method[] methods = aClass.getMethods();
            for (Method m : methods) {
                System.out.println(m);
            }

            System.out.println("获取所有的成员方法（包括私有）");
            Method[] declaredMethods = aClass.getDeclaredMethods();
            for (Method m : declaredMethods) {
                System.out.println(m);
            }

            //获取对象
            Object o = aClass.getConstructor().newInstance();
            System.out.println("获取公有成员方法，带参，有返回值");
            Method show = aClass.getMethod("show", String.class, int.class);
            //调用show方法
            Object result = show.invoke(o, "刘德华", 22);
            System.out.println("调用的返回值" + result);


            System.out.println("获取私有成员方法，无参，无返回值");
            Method show3 = aClass.getDeclaredMethod("show3");
            //设置暴力访问
            show3.setAccessible(true);
            show3.invoke(o);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
