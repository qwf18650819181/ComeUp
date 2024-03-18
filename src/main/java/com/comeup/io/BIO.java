package com.comeup.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;

/**
 * 功能描述:
 *
 * @author: qiu wanzi
 * @date: 2024年3月18日 0018
 * @version: 1.0
 */
public class BIO {


    public static void copy(Path sourceResource, Path destinationResource) throws Exception {
        File sourceFile = new File(sourceResource.toUri());
        File destinationFile = new File(destinationResource.toUri());
        FileInputStream sourceFileInputStream = new FileInputStream(sourceFile);
        FileOutputStream destinationFileOutputSteam = new FileOutputStream(destinationFile);
        byte[] bytes = new byte[1024];
        int read;
        while ((read = sourceFileInputStream.read(bytes)) > 0) {
            destinationFileOutputSteam.write(bytes, 0, read);
        }
        destinationFileOutputSteam.flush();
        sourceFileInputStream.close();
        destinationFileOutputSteam.close();
    }
}
