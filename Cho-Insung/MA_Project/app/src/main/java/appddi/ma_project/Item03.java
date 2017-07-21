package appddi.ma_project;

/**
 * Created by Cho on 2016-03-01.
 */
public class Item03 {   // 이벤트 아이템
    private String title;
    private String day;
    private String content;


    public Item03(String title, String day, String content){

        this.title = title;
        this.day = day;
        this.content = content;
    }

    public String getTitle(){return title;}
    public String getDay(){return day;}
    public String getContent(){return content;}
}
