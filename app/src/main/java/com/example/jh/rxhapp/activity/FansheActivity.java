package com.example.jh.rxhapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jh.rxhapp.R;

public class FansheActivity extends BaseActivity {

    @Override
    public int setMainView() {
        return R.layout.activity_fanshe;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fanshe();
    }

    private void fanshe() {
        /*
 * 获取Class对象的三种方式：
 *
 * 1.调用Object类的getClass():任何类都会继承此方法；
 * 2.任何的数据类型(包括基本类型)都有一个：静态的class属性：
 * 		Student.class;
 * 		int.class
 * 3.调用Class类的静态方法forName(String 全名限定的类名):(常用)
 *
 * 通过这个Class对象，可以获取Student类内部的成员属性、构造方法、成员方法的一些信息，
 * 并能够调用它们；
 */
        /*
        * 获取构造方法，并调用：
        *
        * Class类：
        * 	--批量的：
        * 		Constructor[] getConstructors() ：获取所有的"公有构造方法"
                * 		Constructor[] getDeclaredConstructors() :获取全部(包括私有)的构造方法；
        *  --获取某个构造方法
                *  	Constructor getConstructor(Class ... parameterTypes) ：获取指定的"公有构造方法"；
        *  	Constructor getDeclaredConstructor(Class ... parameterTypes) ：获取指定的构造方法(包括私有的)；
        *
        *  --调用某个构造方法：
        *  	Constructor的 Object newInstance(Object... initargs) :调用指定构造方法，并实例化此类的对象；
        *
        *  --暴力访问：如果私有成员，需要设置暴力访问；
        *  	Constructor的setAccessible(true):不进行权限检查；
        */

        /*
 * 获取成员属性，并调用：
 *
 * Class类的方法：
 * 		--批量的：
 * 			Field[] getFields()：获取所有"公有属性"；
 * 			Field[] getDeclaredFields() :获取所有成员属性(包括私有)：
 * 		--获取单个成员属性
 * 			Field getField(String name) :获取指定的"公有属性"；
 * 			Field getDeclaredField(String name) :获取指定的属性，包括私有的；
 *
 * 		--为成员属性赋值：(注意：1.一定要先实例化此类对象；2.访问私有属性前，要设置暴力访问)
 * 			Field的 void set(Object obj, Object value)

 */

        /*
 * 获取成员方法：
 *
 * Class类的方法：
 * 		--批量的：
 * 			Method[] getMethods()：获取所有"公有方法"(包括继承的)
 * 			Method[] getDeclaredMethods() :获取所有成员方法(包括私有)：
 * 		--获取单个成员方法
 * 			Method getMethod(String name, Class... parameterTypes) :获取指定的"公有方法"；
 * 			Method getDeclaredMethod(String name, Class... parameterTypes) :获取指定的方法，包括私有的；
 *
 * 		--调用方法：(注意：1.要先创建此类的对象;2.调用私有方法前，要先设置暴力访问)
 * 			Method:
 * 				Object invoke(Object obj, Object... args) :调用Method所代表的方法：
 * 				返回值：此Method对象调用所代表的方法，所获取的返回值；
 * 				形参：obj:方法所属对象；
 * 					args:Method所代表的那个方法的实参列表；
 *
 */
    }
}
