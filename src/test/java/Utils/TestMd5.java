package Utils;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @ClassName TestMd5
 * @Description TODO
 * @Author wk
 * @Date 2020/6/12 0012 16:57
 * @Version 1.0
 */
public class TestMd5 {


    @Test
    public void getMd5String(){
        String input = new String("admin");
        String mm = Md5andBase64Utils.encode(input);
        System.out.println(mm);
    }

    @Test
    public void FileMd5(){


        File f= new File("C:\\Users\\admin\\Desktop\\新建文本文档.txt");
        try {
            InputStream in = new FileInputStream(f);
            String mm = Md5andBase64Utils.encode(in);
            System.out.println(mm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void encryptBASE64(){
        String mm = Md5andBase64Utils.encode("123456");
        String ms = Md5andBase64Utils.encryptBASE64(mm);
        System.out.println(ms);
    }

    @Test
    public void decryptBASE64(){
        String mm = "ZTEwYWRjMzk0OWJhNTlhYmJlNTZlMDU3ZjIwZjg4M2U=";
        String ms  = Md5andBase64Utils.decryptBASE64(mm);
        System.out.println(ms);

    }

    @Test
    public void fileencryptBASE64(){
        File f= new File("C:\\Users\\admin\\Desktop\\新建文本文档 (2).txt");
        try {
            InputStream in = new FileInputStream(f);
            String mm = Md5andBase64Utils.fileencryptBASE64(in);
            String ms = Md5andBase64Utils.decryptBASE64(mm);
            System.out.println(ms);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
