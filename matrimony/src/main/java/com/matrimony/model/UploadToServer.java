/**
 * 
 */
package com.matrimony.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author SON
 *
 */
public class UploadToServer {
	public static void upFile(String filePath, InputStream is) throws IOException{
			FileOutputStream fos=new FileOutputStream(filePath);
			int buffer;
			while((buffer=is.read())!=-1){
				fos.write(buffer);
			}
			fos.flush();
			fos.close();
	}
}
