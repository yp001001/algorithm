package test.responsibility.redis;


import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class Client {
    private static final String SEPARATOR = "\r\n";

    public static void main(String[] args) {
        socketConnect();
    }

    private static void socketConnect() {
        Socket socket;
        try {
            socket = new Socket("192.168.200.132", 6379);
        } catch (IOException e) {
            System.err.println("创建连接失败~");
            return;
        }
        try {
            // 设置超时，避免命令错误或者别的原因长时间等待
            socket.setSoTimeout(3000);
        } catch (SocketException e) {
            System.err.println("设置超时时间失败~");
            return;
        }
        StringBuilder cmd = new StringBuilder();
        // 命令加参数个数
        cmd.append("*2").append(SEPARATOR);
        // 当前命令长度
        cmd.append("$3").append(SEPARATOR);
        cmd.append("get").append(SEPARATOR);
        // 命令参数长度
        cmd.append("$4").append(SEPARATOR);
        cmd.append("name").append(SEPARATOR);

        byte[] bytes;
        int read;
        // 需要捕获异常，如果命令错误将会进入死等待，可以响应设置的超时时间
        try {
            // 发送命令
            socket.getOutputStream().write(cmd.toString().getBytes(StandardCharsets.UTF_8));
            // 响应内容
            InputStream stream = socket.getInputStream();
            bytes = new byte[Short.MAX_VALUE];
            read = stream.read(bytes);
            stream.close();
        } catch (Exception e) {
            System.err.println("执行命令超时~");
            return;
        }
        String response = new String(bytes, 0, read, StandardCharsets.UTF_8);
        System.out.println("执行命令：\r\n" + cmd.toString());
        System.out.println("===========================");
        System.out.println("response = " + response);
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("关闭连接异常~");
        }
    }
}
