package running.device.topband.com.citydatalib;

import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlUtil {
    public static void getData() {
        new Thread() {
            @Override
            public void run() {
                List<Data> list = new ArrayList<>(0);
                String url = "http://www.mca.gov.cn/article/sj/xzqh/2018/201804-12/20180810101641.html";
                Document doc = null;
                FileWriter fw = null;
                try {
                    doc = Jsoup.connect(url).timeout(60 * 1000).maxBodySize(0).get();
                    Elements result = doc.getElementsByClass("xl7023736");
                    List<Element> temp = new ArrayList<>();
                    if (null != result) {
                        //有空数据
                        for (int i = 0; i < result.size(); i++) {
                            if (result.get(i).text() != null && !"".equals(result.get(i).text())) {
                                temp.add(result.get(i));
                            }
                        }
                        int count = temp.size();
                        for (int i = 0; i < count; i += 2) {
                            Data data = new Data();
                            String code = temp.get(i).text();
                            String name = temp.get(i + 1).text();
                            data.setProvince(code.substring(0, 2));
                            data.setCity(code.substring(2, 4));
                            data.setCounty(code.substring(4, 6));
                            data.setName(name);
                            data.setCode(code);
                            list.add(data);
                            System.out.println(data);
                        }
                        Gson gson = new Gson();
                        String data = gson.toJson(list);
                        File file = new File("d:/citys.txt");
                        if (file.exists()) {
                            file.delete();
                        } else {
                            file.createNewFile();
                        }
                        fw = new FileWriter(file);
                        fw.write(data);
                        fw.flush();
                    }
                } catch (IOException mE) {
                    mE.printStackTrace();
                } finally {
                    close(fw);
                }
            }
        }.start();
    }

    public static void close(Closeable... cs) {
        for (Closeable c : cs) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException mE) {
                    mE.printStackTrace();
                }
            }
        }
    }
}
