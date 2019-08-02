package com.qixiafei.book.jvm.zzm.c7.s4;

import java.io.IOException;
import java.io.InputStream;

/**
 * <P>Description: 类加载器简单例子. </P>
 * <P>CALLED BY:   齐霞飞 </P>
 * <P>UPDATE BY:   齐霞飞 </P>
 * <P>CREATE AT: 2019/2/28 20:30</P>
 * <P>UPDATE AT: 2019/2/28 20:30</P>
 *
 * @author 齐霞飞
 * @version 1.0
 * @since java 1.8.0
 */
public class ClassLoaderTest {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myClassLoader = new ClassLoader() {

            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {

                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[is.available()];
                    is.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException();
                }
            }
        };

        Object obj = myClassLoader.loadClass("com.qixiafei.book.jvm.zzm.c7.s4.ClassLoaderTest").newInstance();

        System.out.println(obj.getClass());
        System.out.println(obj.getClass().getClassLoader());
        System.out.println(obj.getClass().getClassLoader().getParent());
        System.out.println(obj.getClass().getClassLoader().getParent().getParent());
        System.out.println(obj.getClass().getClassLoader().getParent().getParent().getParent());
        System.out.println(obj instanceof com.qixiafei.book.jvm.zzm.BookJvmZzmApplication);

    }


}
