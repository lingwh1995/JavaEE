package com.dragonsoft;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author ronin
 * @version V1.0
 * @description
 * @class QuickStartServer
 * @date 2019/6/25 8:57
 */
public class QuickStartServer {
    public static void main(String[] args) {
        // 服务器的监听端口
        Server server = new Server(8080);
        // 关联一个已经存在的上下文
        WebAppContext context = new WebAppContext();
        // 设置描述符位置
        context.setDescriptor("spring-mvc/src/main/webapp/WEB-INF/web.xml");
        // 设置Web内容上下文路径
        context.setResourceBase("spring-mvc/src/main/webapp");
        Configuration.ClassList classlist = Configuration.ClassList.setServerDefault(server);
        classlist.clear();
        classlist.add("org.eclipse.jetty.webapp.WebXmlConfiguration");
        classlist.add("org.eclipse.jetty.annotations.AnnotationConfiguration");
        // 设置上下文路径
        context.setContextPath("/");
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // server.join();
        System.err.println("Jetty-8.2.0 Server war started");
        System.err.println("请访问<a>http://localhost:8080</a>");
    }

}
