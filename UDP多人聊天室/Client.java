package UDP多人聊天室;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        String clientid = "Administrator";
        System.out.println("请输入开启的端口号");
        Scanner sc = new Scanner(System.in);
        int port = Integer.parseInt(sc.nextLine());
        DatagramSocket ds = new DatagramSocket(port);
        String text = clientid+"加入聊天室";
        byte[] bs = text.getBytes();
        DatagramPacket dp = new DatagramPacket(bs,bs.length, InetAddress.getByName("localhost"),8888);
        ds.send(dp);

        SendThread st =new SendThread(ds,clientid);
        ReceivedThread rt = new ReceivedThread(ds);
        st.start();
        rt.start();
    }
}
