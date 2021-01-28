[[_TOC_]]

### 克隆须知
> 1、本项目是工具包，包含所有生产使用的工具以及未使用到待使用工具类包<br>
> 2、克隆本项目必须经过项目管理员同意划分权限<br>
> 3、修改本项目工具类必须增加版本以及上报项目管理员，再由项目管理员进行确认是否打包<br>
> 4、上传的方法必须经过单元测试、性能测试(保证性能)后方可上传<br>
> 5、修改或者增加类、接口需要修改本文档，进行注明年月日 某某 动作 内容<br>
> 6、涉及版本的修改必须同步CHANGELOG 文档包<br>
> 7、author：wyy包<br>


___

### 一、common包
#### 1. MD5加密

- 功能说明： 获取消息列表

- 方法主体：

```
   /**
       * md5 加密算法
       * @param text 输入文本
       * @return 返回密文
       * @author wyy
       */
      public static String md5(String text)
  ```
  
- 调用示例：

```
  String md5Str = CommonUtil.md5("hello world");
```

  

#### 2. 根据URL地址获取文件输入流

- 功能说明： 根据URL获取文件输入流inputstream

- 方法主体：

```
   /**
     * 获取URL地址获取文件输入流
     *
     * @param destUrl 输入URL地址
     * @return 返回输入流
     * @author wyy
     */
    public static InputStream saveToFile(String destUrl)
  ```
  
- 调用示例：

```
  InputStream ins = CommonUtil.saveToFile("172.168.2.231:8080/img/mo/demo.xlsx");
 ```
#### 3、MultipartFile 转 File
- 功能说明： MultipartFile 转 File

- 方法主体：

```
  /**
     * MultipartFile 转 File
     *
     * @param file boot file文件
     * @return 返回普通文件类型
     * @throws IOException 读写异常类抛出
     */
    public static File changeMutiFileToFile(MultipartFile file)
  ```
  
- 调用示例：

```
  File ins = CommonUtil.changeMutiFileToFile(MultipartFile file);
  ```

#### 4、File 转 MultipartFile
- 功能说明： 将file 转 MultipartFile

- 方法主体：

```
 /**
     * 将file 转 MultipartFile
     *
     * @param file 文件file
     * @return 返回一个boot file类型
     * 注:file.getName取值须是传参值  例如上传参数名file 则需要设置
     * @throws IOException 读写异常类抛出
     */
    public static MultipartFile changeFileToMultipartFile(File file) 
  ```
  
- 调用示例：

```
  File ins = CommonUtil.changeFileToMultipartFile(File file);
  ```
#### 5、创建线程池
- 功能说明： 创建一个安全高效的线程池

- 方法主体：

```
/**
     * 建议：如何区分类型 看程序计算量
     * 创建线程池—分CPU密集型（主要计算） IO密集型（主IO）
     * 建议最大线程数和阻塞队列不要使用无限或者默认数据容易造成OOM
     * @param poolEnum 线程池枚举类型
     */
    public static ExecutorService getThreadPool(ThreadPoolEnum poolEnum)
```
  
- 调用示例：

```
  //创建一个CPU密集型的线程池
  ExecutorService excutorService = CommonUtil.getThreadPool(ThreadPoolEnum.POOL_FOR_CPU_TYPE); 
   //创建一个IO密集型的线程池
  ExecutorService excutorService = CommonUtil.getThreadPool(ThreadPoolEnum.POOL_FOR_IO_TYPE); 
 ```
#### 6、获取当前时间戳
- 功能说明： 获取当前时间戳  分别是13位和10位

- 方法主体：

```
/**
     * 生成当前时间的时间戳
     * @return
     */
    public static String getCurrentTimeStamp(TimeStampType stampType)
```
  
- 调用示例：

```
  String timeStamp = CommonUtil.getCurrentTimeStamp(TimeStampType.MILLIS_TIME_STAMP); 
 ```
___
### 二、easyExcel包工具示例

**报表读取**

#### 1、读取表头 headerRead
- 功能说明： 读取报表的表头 返回map

- 方法主体：



```
/**
     * 读取表头数据
     *
     * @author wyy
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link }
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ReadExcelListener}
     * <p>
     * 3. 直接读即可
     */
    public static <T> List<Map<Integer, String>> headerRead(InputStream inputStream, Class<T> clazz)
```

    

  
- 调用示例：


```
@Data
@NoArgsConstructor
@AllArgsConstructor
@ColumnWidth(value = 20)
@ContentRowHeight(value = 14)
public class DataDemo {


    //如果
    @ExcelProperty(value = "年龄", index = 0)
    private int age;

    @ExcelProperty(value = "名字", index = 1)
    private String name;


    @ExcelProperty(value = "出生日期", index = 2)
    private String date;
    
  
}
```

  ```
        List<Map<Integer, String>> map = EasyExcelReadUtil.headerRead(new FileInputStream("D://demo.xlsx"),DataDemo.Class);
  ```
  
#### 2、简单读取单sheet
- 功能说明： 读取默认.xlsx 第一个sheet simpleReadFirstSheet

- 方法主体：

```
/**
     * 简单的读 只读单sheet默认第一个sheet
     *
     * @param inputStream 文件流
     * @param clazz       实体类
     * @return 数据源list
     * @author wyy
     */
    public static <T> List<T> simpleReadFirstSheet(InputStream inputStream, Class<T> clazz)
  ```
  
- 调用示例：

```
  List<DataDemo> list = EasyExcelReadUtil.simpleReadFirstSheet(new FileInputStream("D://demo.xlsx"),DataDemo.Class);
```

#### 3、一次性读取所有sheet
- 功能说明： 一次性读取所有sheet 不指定sheet repeatedReadToAllSheet

- 方法主体：

```
/**
     * 读全部sheet,这里注意一个sheet不能读取多次，多次读取需要重新读取文件
     *
     * @author wyy
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link Class}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ReadExcelListener}
     * <p>
     * 3. 直接读即可
     */
    public static <T> List<T> repeatedReadToAllSheet(InputStream inputStream, Class clazz)
  ```
  
- 调用示例：

```
  List<DataDemo> list = EasyExcelReadUtil.repeatedReadToAllSheet(new FileInputStream("D://demo.xlsx"),DataDemo.Class);
```
  
  #### 4、读取指定的sheetNO
- 功能说明：指定sheetNO 读取   repeatedReadBySheetNos

- 方法主体：

```
 /**
     * 读全部sheet,这里注意一个sheet不能读取多次，多次读取需要重新读取文件
     * 指定sheet读取 传入0、1、2分别读取的sheet是Excel从左到右
     *
     * @param sheetNos 输入需要读取的sheet数量 想要读取多少个就输入多少
     * @author wyy
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link Class}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ReadExcelListener}
     * <p>
     * 3. 直接读即可
     */
    public static <T> List<T> repeatedReadBySheetNos(InputStream inputStream, Class<T> clazz, int headRowNumber, Integer... sheetNos)
  ```
  
- 调用示例：

 ```
  //从第2行开始读取，读取指定sheetNO 为2的数据
  List<DataDemo> list = EasyExcelReadUtil.repeatedReadBySheetNos(new FileInputStream("D://demo.xlsx"),DataDemo.Class,2,2);
  ```
  
  
#### 5、多行头 表头读取
- 功能说明：多行复杂头读取，读取多行表头返回表头数据信息

- 方法主体：

```
 /**
     * 多行头 表头读取
     *
     * @author wyy
     * <p>1. 创建excel对应的实体对象 参照{@link Class}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ReadExcelListener}
     * <p>3. headRowNumber  这里设置 想要读取的表头行数 例如 默认返回1行  如果有2行表头就返回2
     */
    public static <T> List<Map<Integer, String>> complexHeaderRead(InputStream inputStream, Class<T> clazz, int headRowNumber) 
  ```
  
- 调用示例：

```
  //获取两行的表头数据
 InputStream inputStream = new FileInputStream("D:\\work\\excel\\complex.xlsx");
        List<Map<Integer, String>> maps = EasyExcelReadUtil.complexHeaderRead(inputStream, ComplexHeadDemo.class, 2);
  ```
  
  #### 6、无模板读取数据输出map集合
- 功能说明：无需定制模板实体  直接返回map格式的数据

- 方法主体：

```
  /**
     * 简单的读 不需要对象模板读取出来是map类型
     *
     * @param inputStream 文件流
     * @return 数据源list
     * @author wyy
     */
    public static List<Map<T, T>> noModelRead(InputStream inputStream) 
  ```
  
- 调用示例：

```
  //默认从第一行后开始读取数据  所以如果存在多行表头可能会存在问题
InputStream inputStream = new FileInputStream("D:\\work\\excel\\report.xlsx");
        List<Map<T, T>> maps = EasyExcelReadUtil.noModelRead(inputStream);
  ```
 
   #### 7、自定义读取数据并处理数据
- 功能说明：实现数据处理加强器BaseDataProcessor接口 或者继承适配器BaseDataProcessorAdapter（推荐）自定义处理数据的逻辑

- 方法主体：

```
   /**
     * 无模板自定义数据处理必须实现BaseDataProcessor 加工类数据处理saveNoModelData()方法
     *
     * @param onceReadMaxCount  最大一次读取并处理行数
     * @param inputStream       文件流
     * @param baseDataProcessor 数据加工类基类
     * @author wyy
     */
    public static List<Map<T, T>> customerProcessRead(InputStream inputStream, int onceReadMaxCount, BaseDataProcessor baseDataProcessor);
  ```
  
- 调用示例：

1. 方式一、实现加强器/加工类（BaseDataProcessor）

```
public class BaseDataProcessorAdapter<T> implements BaseDataProcessor<T> {

    @Override
    public void saveNoModelData(List<Map<T, T>> list) {
        System.out.println("实现加强器 自定义加工类  数据集" );
    }

    @Override
    public void saveData(List<T> list) {
        System.out.println("实现加强器 自定义加工类  数据集" );
    }
}
```

2. 方式二、继承适配器（BaseDataProcessorAdapter）
```
class dataProcess extends BaseDataProcessorAdapter{


        @Override
        public void saveNoModelData(List list) {
            System.out.println("实现适配器 自定义加工类");
        }
    }
```

 
测试代码   ==onceReadMaxCount 必须小于等于总的行数==

```
//注：此时onceReadMaxCount 必须小于等于总的行数 不然自定义数据处理方法没有效果
  InputStream inputStream = new FileInputStream("D:\\work\\excel\\report.xlsx");
        BaseDataProcessorAdapter dataProcessor = new dataProcess();
        List<Map<T, T>> maps = EasyExcelReadUtil.customerProcessRead(inputStream,1,dataProcessor);
  ``` 
___
**报表导出**
 #### 1、简单的导出返回response
- 功能说明：简单的导出Excel报表

- 方法主体：

 ```
  /**
     * 导出 Excel ：一个 sheet，带表头.只有一个sheet 并以response流输出
     *
     * @param response  HttpServletResponse
     * @param data      数据 list，每个元素为一个 BaseRowModel
     * @param fileName  导出的文件名
     * @param sheetName 导入文件的 sheet 名
     * @param model     映射实体类，Excel 模型
     * @throws Exception 异常
     */
    public static <T> void writeExcelByResponse(HttpServletResponse response, List<T> data,
                                                String fileName, String sheetName, Class model) 
  ```
  
- 调用示例：


```
List<DataDemo> list = new CopyOnWriteArrayList();
        list.add( new DataDemo().setAge(1).setDate("2020-01-21").setName("小明"));
        list.add( new DataDemo().setAge(2).setDate("2020-02-21").setName("小红"));
        list.add( new DataDemo().setAge(3).setDate("2020-03-21").setName("小花"));
EasyExcelWriteUtil.writeExcelByResponse(response,list,"","",DataDemo.class);
```

 #### 2、多sheet导出 给定数据源
- 功能说明：给定的数据源进行导出多sheet 导出的sheet数据顺序匹配并以response流返回

- 方法主体：

```
  /**
     * 导出 Excel ：一个 sheet，带表头.指定两个sheet名称 直接作为response流输出
     *
     * @param response HttpServletResponse
     * @param data2    数据 list，每个元素为一个 BaseRowModel
     * @param fileName 导出的文件名
     * @param sheetName1 导入文件的 sheet 名
     * @param model    映射实体类，Excel 模型
     * @throws Exception 异常
     */
    public static <T> void writeExcelComplexSheetByResponse(HttpServletResponse response, List<T> data1, List<T> data2,
                                                            String fileName, String sheetName1, String sheetName2, Class model)
  ```
  
- 调用示例：


```
List<DataDemo> list = new CopyOnWriteArrayList();
        list.add( new DataDemo().setAge(1).setDate("2020-01-21").setName("小明"));
        list.add( new DataDemo().setAge(2).setDate("2020-02-21").setName("小红"));
        list.add( new DataDemo().setAge(3).setDate("2020-03-21").setName("小花"));
List<DataDemo> list1 = new CopyOnWriteArrayList();
        list1.add( new DataDemo().setAge(1).setDate("2020-01-21").setName("小明"));
        list1.add( new DataDemo().setAge(2).setDate("2020-02-21").setName("小红"));
        list1.add( new DataDemo().setAge(3).setDate("2020-03-21").setName("小花"));
EasyExcelWriteUtil.writeExcelComplexSheetByResponse(response,list,list1,"","1","2",DataDemo.class);
```


 #### 3、多sheet导出 给定数据源
- 功能说明：给定的数据源进行导出多sheet 导出的sheet数据顺序匹配并以response流返回

- 方法主体：

```
  /**
     * 导出 Excel ：一个 sheet，带表头.指定两个sheet名称 直接作为response流输出
     *
     * @param outputStream HttpServletResponse
     * @param data1    数据 list，每个元素为一个 BaseRowModel
     * @param sheetName2 导出的文件名
     * @param sheetName1 导入文件的 sheet 名
     * @param model    映射实体类，Excel 模型
     *
     */
    public static <T> void writeExcelComplexSheet(OutputStream outputStream, List<T> data1, List<T> data2,
                                                  String sheetName1, String sheetName2, Class model) 
  ```
  
- 调用示例：


```
OutputStram out = new FileOutputStream("xxxx");
List<DataDemo> list = new CopyOnWriteArrayList();
        list.add( new DataDemo().setAge(1).setDate("2020-01-21").setName("小明"));
        list.add( new DataDemo().setAge(2).setDate("2020-02-21").setName("小红"));
        list.add( new DataDemo().setAge(3).setDate("2020-03-21").setName("小花"));
List<DataDemo> list1 = new CopyOnWriteArrayList();
        list1.add( new DataDemo().setAge(1).setDate("2020-01-21").setName("小明"));
        list1.add( new DataDemo().setAge(2).setDate("2020-02-21").setName("小红"));
        list1.add( new DataDemo().setAge(3).setDate("2020-03-21").setName("小花"));
EasyExcelWriteUtil.writeExcelComplexSheet(out,list,list1,"","1","2",DataDemo.class);
```

 #### 4、单sheet导出 可以写入inputstream
- 功能说明：给定的数据源进行导出多sheet 导出的sheet数据顺序匹配并以response流返回

- 方法主体：

```
  /**
     * 导出 Excel ：一个 sheet，带表头. 输出流 简单的写入流
     *
     * @param outputStream OutputStream
     * @param data         数据 list，每个元素为一个 BaseRowModel
     * @param sheetName    导入文件的 sheet 名
     * @param model        映射实体类，Excel 模型
     */
    public static <T> void writeExcelIn(OutputStream outputStream, List<T> data,
                                        String sheetName, Class model) 
  ```
  
- 调用示例：


```
OutputStram out = new FileOutputStream("xxxx");
List<DataDemo> list = new CopyOnWriteArrayList();
        list.add( new DataDemo().setAge(1).setDate("2020-01-21").setName("小明"));
        list.add( new DataDemo().setAge(2).setDate("2020-02-21").setName("小红"));
        list.add( new DataDemo().setAge(3).setDate("2020-03-21").setName("小花"));

EasyExcelWriteUtil.writeExcelIn(out,list,"",DataDemo.class);
```

#### 5、重复写入多个sheetNo 并导出
- 功能说明： 单个数据源集合重复写入多个sheetNo 并导出

- 方法主体：

```
  /**
     * 重复写入多个sheetNo 并导出
     *
     * @param outputStream 输出流
     * @param data         数据源list
     * @param sheetName    sheet名
     * @param model        导出模板类
     * @param sheetNo      想要导出多少个sheet
     * @param <T> 入参泛型
     *
     */
    public static <T> void repeatedWrite(OutputStream outputStream, List<T> data,
                                         String sheetName, Class model, Integer sheetNo)
  ```
  
- 调用示例：


```
//重复2个sheet
OutputStram out = new FileOutputStream("xxxx");
List<DataDemo> list = new CopyOnWriteArrayList();
        list.add( new DataDemo().setAge(1).setDate("2020-01-21").setName("小明"));
        list.add( new DataDemo().setAge(2).setDate("2020-02-21").setName("小红"));
        list.add( new DataDemo().setAge(3).setDate("2020-03-21").setName("小花"));

EasyExcelWriteUtil.repeatedWrite(out,list,"",DataDemo.class,2);
```

#### 6、重复写入多个sheetNo 并导出
- 功能说明： 提供多个数据源 写入到sheet中并导出

- 方法主体：

```
 /**
     * 重复写入多个sheetNo 并导出
     *
     * @param outputStream 输出流
     * @param datas        数据源list
     * @param model        导出模板类
     * @param <T>  泛型
     *
     */
    public static <T> void writeSheetByData(OutputStream outputStream, Class model, List<T>... datas)
  ```
  
- 调用示例：


```
//重复2个sheet
OutputStram out = new FileOutputStream("xxxx");
List<DataDemo> list = new CopyOnWriteArrayList();
        list.add( new DataDemo().setAge(1).setDate("2020-01-21").setName("小明"));
        list.add( new DataDemo().setAge(2).setDate("2020-02-21").setName("小红"));
        list.add( new DataDemo().setAge(3).setDate("2020-03-21").setName("小花"));
List<DataDemo> list1 = new CopyOnWriteArrayList();
        list1.add( new DataDemo().setAge(1).setDate("2020-01-21").setName("小明"));
        list1.add( new DataDemo().setAge(2).setDate("2020-02-21").setName("小红"));
        list1.add( new DataDemo().setAge(3).setDate("2020-03-21").setName("小花"));
EasyExcelWriteUtil.writeSheetByData(out,DataDemo.class,list,list1);
```

#### 7、模板导出
- 功能说明： 写入模板并将模板一起导出

- 方法主体：

```
 /**
     * 导出 Excel ：一个 sheet，带表头.模板写入指定sheet并导出 不用浏览器的outputStream
     * 指定写入到哪个sheet中
     *
     * @param outputStream OutputStream 输出流
     * @param data         数据 list，每个元素为一个 BaseRowModel
     * @param in           输入流  模板
     * @param sheetNo      导入文件的 sheet  指定输出在哪一个sheet中，角标从0开始
     * @param model        映射实体类，Excel 模型
     */
    public static <T> void writeExcelInSheetNo(OutputStream outputStream, List<T> data,
                                               InputStream in, String sheetName, Class model, Integer sheetNo)
  ```
  
- 调用示例：


```
InputStream ins = new FileInputStream("xxx.xlsx");
OutputStram out = new FileOutputStream("xxxx");

List<DataDemo> list1 = new CopyOnWriteArrayList();
        list1.add( new DataDemo().setAge(1).setDate("2020-01-21").setName("小明"));
        list1.add( new DataDemo().setAge(2).setDate("2020-02-21").setName("小红"));
        list1.add( new DataDemo().setAge(3).setDate("2020-03-21").setName("小花"));
EasyExcelWriteUtil.writeExcelInSheetNo(out,list,ins,"",DataDemo.class,1);
```


#### 8、动态表头数据报表导出
- 功能说明： 表头没有固定实体可以接受只能使用map格式

- 方法主体：

 ```
  /**
     * 不需要对象model的导出  动态表头数据导出
     *
     * @param response  流
     * @param head      头数据 一个list表示一列
     * @param data      数据集合 一个list标识一行数据
     * @param fileName  文件名
     * @param sheetName sheet名称
     * @throws Exception
     */
    public static void dynamicNoModelWrite(HttpServletResponse response, List<List<String>> head, List<List<Object>> data,
                                           String fileName, String sheetName)
  ```
  
- 调用示例：


```
 @Test
    void testdanumic() throws Exception {
        OutputStream inputStream = new FileOutputStream("D:\\work\\excel\\dynamic.xlsx");
        EasyExcelWriteUtil.dynamicNoModelWrite(inputStream,head(),dataList(),"","");
    }
    //创建表头数据
    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        head0.add("字符串" );
        List<String> head1 = new ArrayList<String>();
        head1.add("数字" );
        List<String> head2 = new ArrayList<String>();
        head2.add("日期" );
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }
    //创建数据
    private List<List<Object>> dataList() {
        List<List<Object>> list = new ArrayList<List<Object>>();
        for (int i = 0; i < 10; i++) {
            List<Object> data = new ArrayList<Object>();
            data.add("字符串" + i);
            data.add(new Date());
            data.add(0.56);
            list.add(data);
        }
        return list;
    }
```
___

### 枚举包

#### 1、传入枚举根据code获取msg信息

- 功能说明： 根据code值获取对应的值信息

- 方法主体：

```
  /**
     * 返回的对象实现CodeEnum接口
     */
    public static <T extends CodeEnum> T getByCode(Class<T> enumClass, String code)
  ```
  
- 调用示例：


```
String typemsg = EnumUtil.getByCode(Status.class,x.getStatus().getCode()).getType())
```
___
### HTTP包
#### 1、doget方法
- 功能说明：远程第三方api调用方式，一般用于对接第三方api使用

- 方法主体：


```
  /**
     * 带参的get请求
     * @param url 请求路径
     * @param token 权限或者签名
     * @param timeSpan  时间戳
     * @param o
     * @return
     */
    public static String doGet(String url,String token,String timeSpan,Object o) 
```
  
- 调用示例：

```
HttpClientUtil.doGet("http://youmeng.com/api/getUserById","token","时间戳",入参);
```

#### 2、dopost方法
- 功能说明：远程第三方api调用方式，一般用于对接第三方api使用

- 方法主体：


```
  /**
      * 封装HTTP POST方法
      *
      * @param   url 请求路径
      * @param （如JSON串）
      * @return
      * @throws Exception
      */
     public static String post(String url, String data)
```
  
- 调用示例：

```
HttpClientUtil.post("http://youmeng.com/api/getUserById",入参);
```
___
### 图片工具包
#### 1、生成水印

- 功能说明： 输入对应的文字信息 或者图片url获取对应的加工过的水印图片

- 方法主体：


```
/**
     * 给图片添加水印文字 不旋转
     * @author wyy
     * @param logoText   水印文字
     * @param srcImgPath 源图片路径
     * @param targetPath 目标图片路径
     */
    public static void markImageByText(String logoText, InputStream srcImgPath,
                                       OutputStream targetPath)
```
  
- 调用示例：

```
ImageRemarkUtil.markImageByText("测试复印无效",inputStream,"D:\\work\\img\\1new.png");
```



#### 2、对水印参数进行设置


```
ImageRemarkUtil.setRemarkData(0.5f,150,150,new Font("宋体", Font.BOLD, 77),Color.LIGHT_GRAY);
```

#### 3、生成二维码图片


- 功能说明： 输入对应的文字信息 或者图片url获取对应的加工过的水印图片

- 方法主体：


```
 /**
     * 生成二维码的方法
     * @param content 内容
     * @param width 宽度
     * @param height 高度
     * @param outputStream 输出流
     */
    public static void generateQRCodeImge(String content, int width, int height, String outputStream)
```


  
- 调用示例：

```
QrCodeUtil.generateQRCodeImge("www.chuyuan.ink",350,350,"D:\\work\\img\\MyBlogAddress.png");
```
___
### 注解包
#### 1、日期格式校验


- 功能说明： 对前端传入的日期参数进行格式校验

- 方法主体：


```
 @DateValid
```


  
- 调用示例：

```
 /**
      * 开始时间(精确到时分秒)
      * 注：线索创建时间
      */
     @DateValid(format = FormatEnum.DATE_TIME)
     private String createTimeStart;
```

#### 2、枚举校验


- 功能说明：对枚举进行校验

- 方法主体：


```
 @EnumValid
```


  
- 调用示例：

```
  /**
      * 线索类型（1：线索；2：线索池）
      */
     @EnumValid(target = ClueTypeEnum.class)
     private String clueType;
```

#### 3、集合校验


- 功能说明：对集合入参进行校验，判断是否为空，集合内部值是否为空

- 方法主体：


```
 @ListNotEmpty
```


  
- 调用示例：

```
/**
     * 流程id集合
     */
    @ListNotEmpty
    private List<String> ids;
```