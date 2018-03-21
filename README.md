# kotlin音影
使用kotlin实现的手机音影，主要为了熟练一下kotlin语法，以及一些书写习惯。下面是一些自己在项目中使用到的知识点，这些知识点在大多数项目中都能用到

# ![image](https://github.com/Mao-x-w/KotlinPlayer/blob/HEAD/introduce/1.gif) ![image](https://github.com/Mao-x-w/KotlinPlayer/blob/HEAD/introduce/2.gif)
# ![image](https://github.com/Mao-x-w/KotlinPlayer/blob/HEAD/introduce/3.gif) ![image](https://github.com/Mao-x-w/KotlinPlayer/blob/HEAD/introduce/4.gif)

## reified关键字
reified的意思是具体化，就是具体化一个东西。使用：

```
inline fun <reified T> getName():String?{
    return T::class.simpleName
}

class N{

}

// 调用,最后返回的就是类N的类名
getName<N>()
```
总结：以reified修饰类型后，我们就能够在函数内部使用相关类型了。这样我们就可以做一些java中不可能实现的事情

## object关键字
既可用来声明单例类，也可包装数据，还能充当匿名内部类

### 申明单例类

```
object JumpHelper {

    fun jumpMain(context:Context){
        context.startActivity<MainActivity>()
    }
}
```
这种实现方式是饿汉式

### 申明匿名内部类

```
val thread = object : Thread() {
  override fun run() {
    println("Hello World")
  }
}
thread.start()
```

## companion object
companion object类似于java中的static，可以用来实现懒汉式单例模式，可以给companion object命名，也可以不给名字，不给名字的话它有个默认名字Companion。


```
class LazySingleton private constructor(){
    companion object {
        val instance: LazySingleton by lazy { LazySingleton() }
    }
}

调用：LazySingleton.instance.xxx()

这种是没有给名字，给名字的话可以在companion object后面加个，调用的时候可以带名字也可以不用带

实现步骤：
显式声明构造方法为private
companion object用来在class内部声明一个对象
LazySingleton的实例instance 通过lazy来实现懒汉式加载
lazy默认情况下是线程安全的，这就可以避免多个线程同时访问生成多个实例的问题

```

## kotlin获取Type

### 获取泛型Type

```
var type = (javaClass.getGenericSuperclass() as ParameterizedType).getActualTypeArguments()[0]
gson.fromJson<R>(result,type)

```

### 获取具体类的Type

```
val turnsType = object : TypeToken<List<Turns>>() {}.type
val turns = Gson().fromJson<List<Turns>>(pref.turns, turnsType) //解析
```

## 实现回调监听


```
var listener:((List<RES>,Int)->Unit)?=null

fun setOnItemClickListener(listener:(List<RES>,position:Int)->Unit){
    this.listener=listener
}
```
声明一个listener是一个函数（单方法接口），入参为一个集合，一个Int值，无返回值,这里和java有差别，通过一个方法即可实现，不用定义接口


## 可以使用伴随对象定义静态变量供其他类使用


```
class AudioService : Service() {

    companion object {
        val TAG="tag"
    }
}

// 调用
AudioService.TAG
```

## this
在java中可以通过AudioService.this调用，在kotlin中是通过this@AudioService

## 异步加载
doAsync{

}


