package cn.tedu.dao;

import cn.tedu.entity.Image;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImageDao {
    public void insert(Image image) {
        try (Connection conn = DBUtils.getConn()){
            String sql = "insert into image values(null,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,image.getTitle());
            ps.setString(2,image.getUrl());
            ps.setLong(3,image.getCreated());
            ps.executeUpdate();
            System.out.println("保存完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Image> findAll() {
        ArrayList<Image> list = new ArrayList<>();
        try (Connection conn = DBUtils.getConn()){
            String sql = "select * from image order by created desc";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String url = rs.getString(3);
                long created = rs.getLong(4);
                list.add(new Image(id,title,url,created));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteById(String id) {
        try (Connection conn = DBUtils.getConn()){
              String sql = "delete from image where id=?";
              PreparedStatement ps = conn.prepareStatement(sql);
              ps.setInt(1, Integer.parseInt(id));
              ps.executeUpdate();
              System.out.println("删除完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
