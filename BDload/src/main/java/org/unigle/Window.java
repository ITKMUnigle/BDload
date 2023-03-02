package org.unigle;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Window extends JFrame{

    public void MainWindow(){
        this.setSize(800,600);
        this.setLocation(200,150);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.setTitle("网盘解析助手");
        //地址栏

        JLabel wpUrl = new JLabel("网盘链接：");
        final JTextField wpUrl_addresss = new JTextField();
        wpUrl.setBounds(100,80,100,30);
        wpUrl_addresss.setBounds(210,80,400,30);
        wpUrl.setHorizontalAlignment(JLabel.CENTER);
        this.add(wpUrl);
        this.add(wpUrl_addresss);

        //提取码

        JLabel wppwd = new JLabel("提取码：");
        final JTextField wppwd_value = new JTextField();
        wppwd.setBounds(100,150,100,30);
        wppwd_value.setBounds(210,150,400,30);
        wppwd.setHorizontalAlignment(JLabel.CENTER);
        this.add(wppwd);
        this.add(wppwd_value);

//        密码

        JLabel pwd = new JLabel("密码：");
        final JTextField pwd_value = new JTextField();
        pwd.setBounds(100,220,100,30);
        pwd_value.setBounds(210,220,400,30);
        pwd.setHorizontalAlignment(JLabel.CENTER);
        this.add(pwd);
        this.add(pwd_value);

//        按钮
        JButton jButton1 = new JButton("解析");
        JButton jButton2 = new JButton("退出");

        jButton1.setBounds(300,350,80,30);
        jButton2.setBounds(400,350,80,30);

        this.add(jButton1);
        this.add(jButton2);

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                String URL,PWD,pdm;
                String setpwd_value="以防爆破";
                pdm=pwd_value.getText();
                if(pdm.contains(setpwd_value)){
                    URL=wpUrl_addresss.getText();
                    PWD=wppwd_value.getText();
                    Window window = new Window();
                    window.FileListWindow(URL,PWD);
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(null,"!!!您输入的密码错误!!!\n!!!请联系管理员!!!");
                }
            }
        });
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void FileListWindow(String URL,String PWD){
//      整体设置
        this.setSize(600,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocation(200,150);
        this.setVisible(true);
        this.setTitle("文件列表  (Api接口由 **自己的解析地址** 提供解析)");

        //调用Baiduapi
        String recept_url=URL;
        String recept_pwd=PWD;
        String tmp;
        Map<Integer, String> map = new HashMap<Integer, String>();
        BaiduApi BD=new BaiduApi();
        map.putAll(BD.BaiduApi(recept_url,recept_pwd));

        //下载链接
        JLabel FileURL = new JLabel("文件下载链接");
        JTextArea FileURL_show = new JTextArea();
        JScrollPane Filepan=new JScrollPane(FileURL_show);
        JButton b1=new JButton("返回");
        FileURL.setBounds(230,25,100,30);
        Filepan.setBounds(100,75,400,300);
        FileURL_show.setLineWrap(true);
        FileURL_show.setWrapStyleWord(true);
        FileURL.setHorizontalAlignment(JLabel.CENTER);
        Filepan.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        Set<Integer> set = map.keySet();
        for (Integer s : set){
            tmp=map.get(s);
            tmp=tmp.replace("href=","").replace("\"","").
                    replace(">","\n");
            FileURL_show.append("下载地址为 : "+tmp+"\n");
        }

        this.add(FileURL);
        this.add(Filepan);
        b1.setBounds(250,400,80,30);
        this.add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = new Window();
                window.MainWindow();
                dispose();
            }
        });

    }

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,"！！！推荐使用IDM下载！！！\nIDM下载链接:https://unigle.lanzous.com/iwyTmflxi9g 密码:ba4k\n！！！如果遇到显示无内容请拖动一下边框！！！");
        Window window = new Window();
        window.MainWindow();
    }

}
