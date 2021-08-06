package ink.boyuan.wheels.easyexcel.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.OutputStream;
import java.util.List;

/**
 * @author dxl
 * @version 1.0
 * @Classname AbstractWriteExcelLoop
 * @date 2020/12/17 10:49
 * @description 批量分页导出接口实现后使用
 **/
public abstract class AbstractWriteExcelLoop<T> {


    private final static int PAGE_SIZE = 1000;
    private Class<T> rowModelClass;

    /**
     * 有模板分页导出
     * @param rowModelClass
     */
    public AbstractWriteExcelLoop(Class<T> rowModelClass) {
        this.rowModelClass = rowModelClass;
    }


    /**
     * 有模板分页导出
     * @throws Exception
     */
    public void exportLoopExcel() throws Exception {
        String fileName = getFileName();
        ExcelWriter excelWriter = null;
        try (OutputStream outputStream = getOutputStream(fileName)) {
            // 头的策略
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            // 内容的策略
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
            HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                    new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
            // 这里 指定文件 写入输出流
            excelWriter = EasyExcel.write(outputStream, rowModelClass).
                    registerWriteHandler(horizontalCellStyleStrategy).build();
            long count = getTotal();
            long times = count / PAGE_SIZE;
            for (int i = 1; i <= times + 1; i++) {
                List<T> rowModels = pageRowModels(i, PAGE_SIZE);
                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class 实际上可以一直变
                WriteSheet writeSheet = EasyExcel.writerSheet(i, String.valueOf(i)).head(rowModelClass).build();
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                excelWriter.write(rowModels, writeSheet);
            }

        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }

    }


    /**
     * 无模板分页导出
     * @throws Exception
     */
    public void exportNoModelLoopExcel() throws Exception {
        String fileName = getFileName();
        ExcelWriter excelWriter = null;
        try (OutputStream outputStream = getOutputStream(fileName)) {
            // 头的策略
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            // 内容的策略
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
            // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
            HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                    new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
            // 这里 指定文件 写入输出流
            excelWriter = EasyExcel.write(outputStream).excelType(ExcelTypeEnum.XLSX).head(getHead()).
                    registerWriteHandler(horizontalCellStyleStrategy).build();
            long count = getTotal();
            long times = count / PAGE_SIZE;
            for (int i = 1; i <= times + 1; i++) {
                List<T> rowModels = pageRowModels(i, PAGE_SIZE);
                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class 实际上可以一直变
                WriteSheet writeSheet = EasyExcel.writerSheet(i, String.valueOf(i)).head(rowModelClass).build();
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                excelWriter.write(rowModels, writeSheet);
            }

        } finally {
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }

    }


    /**
     * 生成并返回表头
     * @return
     */
    protected abstract List<List<String>> getHead();

    /**
     * 获取文件名
     *
     * @return 返回文件名
     */
    protected abstract String getFileName();


    /**
     * 获取输出流
     *
     * @param fileName 文件名
     * @return 输出流
     * @throws Exception
     */
    protected abstract OutputStream getOutputStream(String fileName) throws Exception;

    /**
     * 获取数据总量
     *
     * @return
     */
    protected abstract long getTotal();

    /**
     * 分页数据
     *
     * @param pageNo   第几页
     * @param pageSize 每页数量
     * @return
     */
    protected abstract List<T> pageRowModels(Integer pageNo, Integer pageSize);
}
