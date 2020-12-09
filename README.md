# 使用前须知<br>


### 1、本项目是工具包，包含所有生产使用的工具以及未使用到待使用工具类<br>
### 2、本项目是闲暇之余写的工具类，对于性能方面有部分是没有进行验证的


## 方法

<br>
common包<br>

```
 /**************************************
     * 基础工具类 包含以下方法
     * 1、MD5加密
     * 2、根据URL地址获取文件输入流
     * 3、MultipartFile 转 File
     * 4、File 转 MultipartFile
     * ...
     * *************************************
     */
```

easyExcel包<br>

```
  /*************************
     * 报表读取工具类 包含以下方法
     * 1、读取表头 headerRead
     * 2、简单读取单sheet 默认第一个sheet simpleReadFirstSheet
     * 3、一次性读取所有sheet 不指定sheet repeatedReadToAllSheet
     * 4、指定sheetNO 读取   repeatedReadBySheetNos
     * 5、多行表头复杂表头读取  complexHeaderRead
     * 6、无模板读取数据输出map集合 noModelRead
     * 7、自定义读取数据并处理数据 customerProcessRead   
     * ...
     *
     */
```

```
    /***************************
       * 报表导出
       * 1、报表导出通过response流 单sheet writeExcelByResponse
       * 2、多sheet导出 并指定导出的sheet名称通过response流输出 writeExcelComplexSheetByResponse
       * 3、多sheet导出 并指定导出的sheet名称 writeExcelComplexSheet
       * 4、单sheet导出 不通过response流 writeExcelIn
       * 5、单数据集重复导出 repeatedWrite
       * 6、多数据集重复导出 writeSheetByData
       * 7、模板写入并导出 writeExcelInSheetNo
       * 8、动态表头数据报表导出 dynamicNoModelWrite
       */
```

枚举包

```
   传入枚举根据code获取信息
   
```

HTTP包

```
   http请求第三方接口方法
   1、doget方法
   2、post方法
   
```

图片包

```
   图片相关方法
   1、根据输入文字输出相应的水印图片
   2、根据水印图片输出对应的添加水印图片的图片
   3、生成二维码图片
   
```

注解包

```
    自定义注解相关：
    1.时间格式注解
    2.枚举注解
```