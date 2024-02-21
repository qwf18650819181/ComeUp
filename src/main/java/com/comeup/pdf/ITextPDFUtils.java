package com.comeup.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.RectangleReadOnly;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.core.JSONOutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * ITextPDFUtils
 * itext导出pdf通用类
 */
public class ITextPDFUtils {
    private static Logger logger = LoggerFactory.getLogger(ITextPDFUtils.class);
    private static Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
    private static String encoding = "UTF-8";

    static {
        // 指定的模板的加载目录
        configuration.setTemplateLoader(new ClassTemplateLoader(ITextPDFUtils.class, "/template"));
        // 指定编码
        configuration.setDefaultEncoding(encoding);
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setCacheStorage(NullCacheStorage.INSTANCE);
        configuration.setOutputFormat(JSONOutputFormat.INSTANCE);
    }

    /**
     * 生成pdf
     *
     * @param dataMap
     * @param templateFileName
     * @param generateFileName
     * @param docUrx
     * @param docUry
     * @param fileNameMap
     * @return
     */
    public static void processPdf(Map dataMap, String templateFileName, String generateFileName, Float docUrx, Float docUry, Map<String, String> fileNameMap) throws Exception {
        // 组装文件路径
        File file;
        Writer out = null;
        Document document;
        String outputFileName;
        PdfWriter writer = null;
        InputStream in = null;
        try {
            // 从指定的模板目录中加载对应的模板文件
            Template templateFile = configuration.getTemplate(templateFileName + ".ftl");
            // 生成HTML文件
            String fileName = generateFileName + ".html";
            logger.info("html fileName={}", fileName);
            file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileNameMap.put("Html", fileName);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
            templateFile.process(dataMap, out);
            // 生成PDF文件
            outputFileName = generateFileName + ".pdf";
            logger.info("pdf fileName={}", outputFileName);
            if (docUrx == null || docUry == null) {
                //默认设置
                document = new Document(PageSize.A4); // 横向打印
            } else {
                document = new Document(new RectangleReadOnly(docUrx, docUry));
            }
            fileNameMap.put("Pdf", outputFileName);
            writer = PdfWriter.getInstance(document, new FileOutputStream(outputFileName));
            document.open();
            in = new FileInputStream(fileName);
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, in, null, Charset.forName(encoding), new MyFontProvider());
            document.close();
        } finally {
            closeInputStream(in);
            closeWriter(out);
            closePdfWriter(writer);
        }
    }

    public static void closeInputStream(InputStream in) {
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public static void closeWriter(Writer out) {
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public static void closePdfWriter(PdfWriter writer) {
        if (writer != null) {
            writer.close();
        }
    }

}
