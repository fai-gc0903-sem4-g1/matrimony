/**
 * 
 */
package other;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author SON
 *
 */
public class JsoupExample {
	public static void main(String[] args) {
		StringBuilder data = new StringBuilder();
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.home")
					+ "/Desktop/new  2.txt"));
			while ((line = br.readLine()) != null) {
				data.append(line);
				data.append("\n");
			}
			br.close();
			System.out.println("read");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = Jsoup.parse(data.toString());
//		Elements imgs = doc.select("img");
//		for (Element img : imgs) {
//			String link = img.attr("src");
//			String name = img.attr("alt");
//			 downloadFile(link, new
//			 File(System.getProperty("user.home")+"/Desktop/emoticons/"+name+".gif"));
//
//		}
		
		Elements kbds=doc.select("kbd");
		Elements spans=doc.select("span");
		for(int i=0;i<kbds.size();i++){
			System.out.println("emoticons[\""+kbds.get(i).text()+"\"] = \""+spans.get(i).text()+"\";");
		}
	}

	public static void downloadFile(String url, File file) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		FileOutputStream fos = new FileOutputStream(file);
		int buffer;
		while ((buffer = conn.getInputStream().read()) != -1) {
			fos.write(buffer);
		}
		fos.flush();
		fos.close();
	}
}
