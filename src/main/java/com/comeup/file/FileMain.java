package com.comeup.file;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年9月9日 星期一
 * @version: 1.0
 */
public class FileMain {

    public static void main(String[] args) {
        String data = "UPDATE | publishProductSpecId: 125770 | publishProductStoreId: 0    | websiteName: null           | sku: sku_79d2b6fe42a3       | sellPrice: 0.00   | price: 0.00       | weight: 0.00      | volumeWeight: 0.00 | packageHeight: 0.00 | packageLong: 0.00 | packageWide: 0.00 | status: PUBLISHED | publishStatus: publishStatus_9cba4c35820e | createDate: 2024-08-31 16:09:59  | updateDate: 2024-09-04 16:47:47  | createBy:                   | updateBy: PublishPlatformJob | version: 35   \r\n";
        String filePath = "D:\\1.txt";  // Windows系统中的文件路径

        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < 100000; i++) {
                writer.write(data);
            }
            System.out.println("Data has been written to the file successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred:");
            e.printStackTrace();
        }
    }

}
