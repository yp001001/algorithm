package test2;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Test19 {
    static Socket socket;
    static PrintWriter writer;
    static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        String host = "192.168.200.132";
        int port = 6379;
        // 1. 建立连接
        try {
            socket = new Socket(host, port);
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            // 发出请求
            sendRequest();
            // 接收响应
            Object obj = handleResponse();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
            writer.close();
            socket.close();
        }
    }

    private static Object handleResponse() throws IOException {
        int prefix = reader.read();
        switch (prefix) {
            case '+':
                return reader.readLine();
            case '-':
                throw new RuntimeException(reader.readLine());
            case ':':
                return Long.parseLong(reader.readLine());
            case '$':   // 多行字符串
                int len = Integer.parseInt(reader.readLine());
                if(len==-1){
                    return null;
                }
                if(len==0){
                    return "";
                }
                break;
            case '*':
                break;
            default:
                throw new RuntimeException("错误的数据格式");
        }
        return null;
    }

    private static void sendRequest() {
        writer.println("*3");
        writer.println("$3");
        writer.println("set");
        writer.println("$4");
        writer.println("name");
        writer.println("$6");
        writer.println("虎哥");
        writer.flush();
    }
}
