package appddi.ma_project;

/**
 * Created by Cho on 2016-03-01.
 */
public class Item02 { // 공지 사항 아이템
    private String noticeText;
    private String noticeDay;
    private String noticeTitle;

    public Item02(String t, String c, String a){
        this.noticeTitle = t;
        this.noticeText = c;
        this.noticeDay = a;

    }

    public String getNoticeText(){return noticeText;}
    public String getNoticeDay(){return noticeDay;}
    public String getNoticeTitle(){return noticeTitle;}
}
