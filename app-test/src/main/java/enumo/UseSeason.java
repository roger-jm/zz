package enumo;

public class UseSeason {
	 /** 
     * 将英文的季节转换成中文季节 
     * @param season 
     * @return 
     */  
    private String getChineseSeason(Season season){  
        String result = "";  
        switch(season){  
            case SPRING :  
                result = appendSeason("春天", season);  
                break;  
            case AUTUMN :  
                result = appendSeason("秋天", season);  
                break;  
            case SUMMER :   
                result = appendSeason("夏天", season);  
                break;  
            case WINTER :  
                result = appendSeason("冬天", season);  
                break;  
            default :  
                result = appendSeason("地球没有的季节", season);  
                break;  
        }  
        return result.toString();  
    }  
      
    private String appendSeason(String chineseName, Season season){  
        StringBuffer result = new StringBuffer();  
        result.append("[中文：" + season.getChineseName() + "，枚举常量:" + season.name() + "，数据:" + season.getCode() + "]");  
        return result.toString();  
    }  
      
    public void doSomething(){  
        for(Season s : Season.values()){  
            System.out.println(getChineseSeason(s));//这是正常的场景  
        }  
        //System.out.println(getChineseSeason(5));  
        //此处已经是编译不通过了，这就保证了类型安全  
    }  
      
    public static void main(String[] arg){  
        UseSeason useSeason = new UseSeason();  
        useSeason.doSomething();  
        
    }  
}
