package UDP多人聊天室;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SendThread extends Thread{

    private DatagramSocket ds ;
    private String clientid;
    public SendThread (DatagramSocket ds,String clientid){
        this.ds=ds;
        this.clientid=clientid;
    }
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String text = sc.nextLine();
                String newtext = clientid +":"+ text;
                byte[] bytes = newtext.getBytes();

                DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("localhost"), 8888);
                ds.send(dp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
