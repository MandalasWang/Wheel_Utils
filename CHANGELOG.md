[[_TOC_]]
### 添加日志格式说明——原则上遵循==大修小改小修详改==策略

1.   修改提交的类比较多的情况下可以只记录修改类名。

```
例如：
##### 2020/12/8 15:07 wyy提交 增加annotation包
- 修改说明： wyy提交 增加annotation包 增加了增加DateValid、EnumValid类

- 修改内容：第一次提交
增加annotation包，增加了增加DateValid、EnumValid类和xxx类
```

2.   只修改了单个类内容，小范围的修改需要详细到修改的方法名方便在帮助文档查询。

```
例如 
##### 2020/12/8 15:07 wyy提交 修改了EasyExcelReadUtil类
- 修改说明： wyy提交 修改了EasyExcelReadUtil类 增加repeatedReadToAllSheet方法 修改了complexHeaderRead方法

- 修改内容：第一次提交
修改了EasyExcelReadUtil类 增加repeatedReadToAllSheet方法，修改了complexHeaderRead方法，以及complexHeaderRead的返回值修改
```

3.   第一次提交、大的版本提交记录版本号提交即可 。
```
例如 
##### 2020/12/8 15:07 第一版本提交
- 修改说明： 第一次项目提交

- 修改内容：第一次提交
```
<br>
___

#### 版本更新记录
##### 2020/12/8 15:07 第一版本提交
- 修改说明： 第一次项目提交

- 修改内容：第一次提交


##### 2020/1/27 09:12 wyy提交 修改easyExcel代码结构
- 修改说明： wyy提交

- 修改内容：
easyExcel增加自定义数据处理，利用装饰器和适配器模式修改代码结构

##### 2020/1/27 13:32 wyy 提交 增加@ListNotEmpty注解
- 修改说明： wyy提交 增加list集合校验@ListNotEmpty 注解

- 修改内容：
增加@ListNotEmpty注解，增加校验集合不能为空，并且集合里面的内容不能为空或者"" 空字符串否则报错逻辑


##### 2020/1/28 09:37 wyy 提交 修改CommonUtil类
- 修改说明： wyy提交 common包增加获取当前时间戳方法 getCurrentTimeStamp

- 修改内容：
增加一个获取当前时间戳的方法getCurrentTimeStamp()

##### 2020/1/28 10:37 wyy 提交 更新jar版本
- 修改说明： wyy提交 将版本提升至1.1.0

- 修改内容：
将版本提升至1.1.0

##### 2020/1/28 15:37 wyy 提交 增加IP工具类以及PhoneFormatCheck、EmailFormatCheck注解
- 修改说明： wyy提交 增加IP工具类以及PhoneFormatCheck、EmailFormatCheck注解

- 修改内容：
增加IP工具类以及PhoneFormatCheck、EmailFormatCheck注解

##### 2020/1/29 13:37 wyy 提交 增加金额校验自定义注解 @MoneyFormatCheck
- 修改说明： wyy提交 增加@MoneyFormatCheck注解

- 修改内容：
增加@MoneyFormatCheck注解，保留两位小数

##### 2020/1/29 14:37 wyy 提交 修改金额校验自定义注解 @MoneyFormatCheck
- 修改说明： wyy提交 修改@MoneyFormatCheck注解

- 修改内容：
修改@MoneyFormatCheck注解，保留两位小数  最大支持百万位