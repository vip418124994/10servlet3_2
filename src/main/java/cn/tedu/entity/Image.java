package cn.tedu.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Image {
    private int id;
    private String title;
    private String url;
    private long created;

    public String getCreatedStr(){
        //创建日期格式化对象
        SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月DD日 HH时mm分ss秒");
        //创建日期对象
        Date date = new Date(this.created);
        //将日期对象按照指定的格式转换成字符串
        return  f.format(date);
    }
    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", created=" + created +
                '}';
    }

    public Image(int id, String title, String url, long created) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
