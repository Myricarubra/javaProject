package basic.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ZhangDong on 2018/3/10.
 */
public class ServiceServer {

    public static void main(String[] args) throws IOException {
        // 创建一个serversocket绑定IP端口
        ServerSocket serviceServer = new ServerSocket();
        serviceServer.bind(new InetSocketAddress("localhost", 8899));

        // 接受客户端连接请求;accept()是一个阻塞方法，会一直等待，知道客户端有连接才返回
        while (true) {
            Socket socket = serviceServer.accept();
            new Thread(new ServiceServerTask(socket)).start();
        }
    }
}
