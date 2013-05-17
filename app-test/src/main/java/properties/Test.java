package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();//���Լ��϶��� 
        FileInputStream fis = new FileInputStream("prop.properties");//�����ļ������� 
        prop.load(fis);//�������ļ���װ�ص�Properties������ 
        fis.close();//�ر��� 
         
        //��ȡ����ֵ��sitename�����ļ��ж��� 
        System.out.println("��ȡ����ֵ��sitename=" + prop.getProperty("sitename")); 
        //��ȡ����ֵ��countryδ���ļ��ж��壬���ڴ˳����з���һ��Ĭ��ֵ���������޸������ļ� 
        System.out.println("��ȡ����ֵ��country=" + prop.getProperty("country", "�й�")); 
         
        //�޸�sitename������ֵ 
        prop.setProperty("sitename", "Boxcode"); 
        //���һ���µ�����studio 
        prop.setProperty("studio", "Boxcode Studio"); 
        
        //prop.setProperty("CN=tongtech, OU=tongweb, O=tongweb, L=beijing, ST=beijing, C=cn", "");
        System.out.println("��ȡ����ֵ��kong=" + prop.getProperty("CN=tongtech, OU=tongweb, O=tongweb, L=beijing, ST=beijing, C=cn")); 
        
        if(prop.getProperty("kong").equals("")){
        	System.out.println("kong");
        }
        //�ļ������ 
        FileOutputStream fos = new FileOutputStream("prop.properties"); 
        //��Properties���ϱ��浽���� 
        prop.store(fos, "Copyright (c) Boxcode Studio"); 
        fos.close();//�ر���    
	}

}
