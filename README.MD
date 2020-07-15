# JVM实战高手



## 1. 类加载

### 1.1 类加载器

JVM 想要执行一个类，首先要加载类，在加载类之前，需要先编译成字节码class 文件。 然后就执行类的加载过程，JVM 加载类的话，需要类加载器

![](./images/jvm-01.png)





### 1.2 类加载过程

类加载的过程加载，验证，准备，解析，初始化。 

+ 加载: 类加载器去加载类
+ 验证: 主要是验证加载的字节码是否符合JVM规范，不然随便瞎写JVM也执行不了
+ 准备: 给对象申请内存，然后给变量设置初始值，该设置0的设置0，该设置null的设置null
+ 解析: 给符号引用变成直接引用
+ 初始化：
  + 给变量赋值，准备阶段只是设置了初始值.
  + 这个是核心阶段，执行类的初始化，如果发现这个类的父类没有初始化，会先暂停，然后去初始化父类

```java
public class Kafka {
	public static void main() {
		ReplicaManager rm = new RplicaManage();
	}
}
```



![](./images/jvm-02.png)







### 1.3 双亲委托机制

类加载器是分层级的，遵循双亲委派机制

+ Bootstrap ClassLoder，加载java的核心类库，加载java安装目录下的lib目录的class文件
+ Ext ClassLoder，加载一些java的其他类库，加载java安装目录下的lib/ext目录下的class
+ Application ClassLoder ，应该程序类加载器，这个类加载器是加载我们写的类
+ 自定义类加载器

![](./images/jvm-03.png)



类加载器遵循双亲委派机制，就是说，如果要加载一个类，先去为他的父类能不能加载，如果父类上面还有父类，就继续问，直到顶层。然后顶层说加载不了，就下派到子类，如果所有父类都加载不了，那就自己加载。这么做的好处是，不会重复加载一个类




### 1.4 Tomcat的类加载机制

+ Tomcat 自定义了Common，Catalina,  Shared 等类加载器，加载自己的核心内库

+ 每个webapp有一个对应的webapp类加载器

+ 每个jsp准备一个jsp类加载器

![](./images/jvm-05.png)



我们自己打包的war中的class 不会传到的上层类加载器中











## 2. 内存区域

**方法区**（jdk1.8以后，这块区域叫元数据空间）：存放类似常量池的东西和各种类相关的信息

**程序计数器(Program Counter)**：记录当前执行的字节码指令的位置，**多线程每个线程有自己的程序计数器**

**Java虚拟机栈(Stack)**：存放方法创建时的栈帧，栈帧有方法的**局部变量表、操作数栈、动态链接、方法出口**等， **每个线程有自己的虚拟机栈**

**堆内存(Heap)**：存放代码创建的各种对象

**本地方法栈(Native Method Stack)**：存放各种native方法的局部变量表之类的信息

![](./images/jvm-04.png)

```java
public class Kafka {
	public static void main() {
		ReplicaManager rm = new RplicaManage();
    rm.loadReplicasFromDisk();
	}
}


public class ReplicaManager {
 	public void loadReplicasFromDisk() {
    	Boolean hasFinished = false;
      if (isLocalDataCorrupt()) {}
  }
  
  public Boolean isLocalDataCorrupt() {
  	return isCorrupt;  
  }
}
```





## 3. 垃圾回收

后台自动运行的线程，不断检查jvm堆中各个实例对象

![](./images/jvm-07.png)



![](./images/jvm-08.png)



如果某个实例对象没有被任何一个方法的局部变量，类的静态变量，常量引用，那个这个对象实列就应该被回收掉，让他不再占用任何资源。JVM中的垃圾会被定期的后台垃圾回收线程清理掉，不断释放



