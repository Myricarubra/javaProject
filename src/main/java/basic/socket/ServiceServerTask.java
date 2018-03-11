package basic.socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by ZhangDong on 2018/3/11.
 */
public class ServiceServerTask implements Runnable {

    Socket socket;
    InputStream inputStream = null;
    OutputStream outputStream = null;

    public ServiceServerTask(Socket socket) {
        this.socket = socket;
    }

    // 业务逻辑
    @Override
    public void run() {
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String param = br.readLine();

            GetDataServiceImpl gds = new GetDataServiceImpl();
            String result = gds.gatData(param);
            System.out.println(result);
            PrintWriter pw = new PrintWriter(outputStream);
            pw.println(result);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
