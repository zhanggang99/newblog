package com.zhanggang.blog.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class jvmOOM {
    public static void main(String[] args) {
        System.out.println("begin vm option = -Xms8m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError");
        List<String> list = new ArrayList<>();
        for(int i=0;i<1000000;i++){
            String str="";
            for(int j=0;j<1000;j++){
                str += UUID.randomUUID().toString();
            }
            list.add(str);
        }
        System.out.println("OK");

//        begin
//        java.lang.OutOfMemoryError: Java heap space
//        Dumping heap to java_pid4323.hprof ...
//        Heap dump file created [9476277 bytes in 0.058 secs]
//        Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//        at java.base/java.util.Arrays.copyOfRange(Arrays.java:4030)
//        at java.base/java.lang.StringLatin1.newString(StringLatin1.java:715)
//        at java.base/java.lang.StringBuilder.toString(StringBuilder.java:448)
//        at com.zhanggang.blog.test.jvmOOM.main(jvmOOM.java:14)
//
//        Process finished with exit code 1



        String str = System.getProperty("str");
        if(str==null){
            System.out.println("str is null ");
        }else{
            System.out.println("str is "+str);
        }

        // bash-3.2$ /Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/bin/java -agentlib:jdwp=transport=dt_socket,server=n,suspend=y,address=localhost:50122 -Dfile.encoding=UTF-8 -cp "/Users/zhanggang/Library/Application Support/Code/User/workspaceStorage/8a67b4dadfba5baa6e3abc4b0277570f/redhat.java/jdt_ws/jvm_ebba07a4/bin" TestJVM
// str is null
// bash-3.2$ ls
// TestJVM.java
// bash-3.2$ javac TestJVM.java
// bash-3.2$ ls
// TestJVM.class   TestJVM.java
// bash-3.2$ java -Dstr=hello TestJVM
// str is hello
// bash-3.2$


// //运行模式
// bash-3.2$ /Library/Java/JavaVirtualMachines/jdk1.8.0_231.jdk/Contents/Home/bin/java -agentlib:jdwp=transport=dt_socket,server=n,suspend=y,address=localhost:50122 -Dfile.encoding=UTF-8 -cp "/Users/zhanggang/Library/Application Support/Code/User/workspaceStorage/8a67b4dadfba5baa6e3abc4b0277570f/redhat.java/jdt_ws/jvm_ebba07a4/bin" TestJVM
// str is null
// bash-3.2$ ls
// TestJVM.java
// bash-3.2$ java-c TestJVM.java
// bash: java-c: command not found
// bash-3.2$ ll
// bash-3.2$ java -cline -showversion TestJVM
// Unrecognized option: -cline
// Error: Could not create the Java Virtual Machine.
// Error: A fatal exception has occurred. Program will exit.
// bash-3.2$ ls
// TestJVM.class   TestJVM.java
// bash-3.2$ java -client -showversion TestJVM
// java version "1.8.0_231"
// Java(TM) SE Runtime Environment (build 1.8.0_231-b11)
// Java HotSpot(TM) 64-Bit Server VM (build 25.231-b11, mixed mode)

// str is null
// bash-3.2$ java -X
//     -Xmixed           混合模式执行 (默认)
//     -Xint             仅解释模式执行

// bash-3.2$ java -showversion -Xint TestJVM
// java version "1.8.0_231"
// Java(TM) SE Runtime Environment (build 1.8.0_231-b11)
// Java HotSpot(TM) 64-Bit Server VM (build 25.231-b11, interpreted mode)

// str is null
// bash-3.2$ java -showversion -Xcomp TestJVM
// java version "1.8.0_231"
// Java(TM) SE Runtime Environment (build 1.8.0_231-b11)
// Java HotSpot(TM) 64-Bit Server VM (build 25.231-b11, compiled mode)

// str is null
// bash-3.2$ java -showversion TestJVM
// java version "1.8.0_231"
// Java(TM) SE Runtime Environment (build 1.8.0_231-b11)
// Java HotSpot(TM) 64-Bit Server VM (build 25.231-b11, mixed mode)

// str is null
// bash-3.2$

//运行 java 命令时打印参数：
//java -XX:+PrintFlagsFinal TestJVM

//查看正在运行时 java 运行的参数jinfo

    }
}
