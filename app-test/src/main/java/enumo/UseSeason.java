package enumo;

public class UseSeason {
	 /** 
     * ��Ӣ�ĵļ���ת�������ļ��� 
     * @param season 
     * @return 
     */  
    private String getChineseSeason(Season season){  
        String result = "";  
        switch(season){  
            case SPRING :  
                result = appendSeason("����", season);  
                break;  
            case AUTUMN :  
                result = appendSeason("����", season);  
                break;  
            case SUMMER :   
                result = appendSeason("����", season);  
                break;  
            case WINTER :  
                result = appendSeason("����", season);  
                break;  
            default :  
                result = appendSeason("����û�еļ���", season);  
                break;  
        }  
        return result.toString();  
    }  
      
    private String appendSeason(String chineseName, Season season){  
        StringBuffer result = new StringBuffer();  
        result.append("[���ģ�" + season.getChineseName() + "��ö�ٳ���:" + season.name() + "������:" + season.getCode() + "]");  
        return result.toString();  
    }  
      
    public void doSomething(){  
        for(Season s : Season.values()){  
            System.out.println(getChineseSeason(s));//���������ĳ���  
        }  
        //System.out.println(getChineseSeason(5));  
        //�˴��Ѿ��Ǳ��벻ͨ���ˣ���ͱ�֤�����Ͱ�ȫ  
    }  
      
    public static void main(String[] arg){  
        UseSeason useSeason = new UseSeason();  
        useSeason.doSomething();  
        
    }  
}
