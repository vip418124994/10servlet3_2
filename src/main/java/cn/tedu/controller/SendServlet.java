package cn.tedu.controller;

import cn.tedu.dao.ImageDao;
import cn.tedu.entity.Image;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "SendServlet",urlPatterns = "/send")
public class SendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取上传的标题
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");


        Part filePart = request.getPart("pic");
        //得到文件描述信息
        String info = filePart.getHeader("content-disposition");
        //得到文件后缀名
        String suffix = info.substring(info.lastIndexOf("."),info.length()-1);
        //得到唯一的文件名
        String fileName = UUID.randomUUID()+suffix;
        //得到Tomcat管辖范围路径
        String path = request.getServletContext().getRealPath("images/");
        //创建文件夹
        new File(path).mkdirs();
        //把文件保存在上面文件夹中
        filePart.write(path+fileName);
        System.out.println("保存完成!");
        //把接收到的标题 图片路径 和发布时间封装到实体类中
        //System.currentTimeMillis()获取当前系统时间
        Image image = new Image(0,title,
                "images/"+fileName,System.currentTimeMillis());
        ImageDao dao = new ImageDao();
        dao.insert(image);
        System.out.println("发布完成");
        response.sendRedirect("/findall");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
