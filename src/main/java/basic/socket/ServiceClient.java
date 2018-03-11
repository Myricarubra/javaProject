package basic.socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by ZhangDong on 2018/3/10.
 */
public class ServiceClient {


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8899);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        PrintWriter pw = new PrintWriter(outputStream);
        pw.println("hello");
        pw.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String result = br.readLine();
        System.out.println(result);

        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
