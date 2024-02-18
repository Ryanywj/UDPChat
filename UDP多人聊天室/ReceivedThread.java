package UDP多人聊天室;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceivedThread  extends Thread{
    private DatagramSocket ds ;
    public ReceivedThread(DatagramSocket ds){
        this.ds= ds;
    }
    @Override
    public void run() {
        while (true){
            try {
                byte[] bytes = new byte[1024];
                DatagramPacket dp = new DatagramPacket(bytes,1024);
                ds.receive(dp);
                String message = new String(bytes, 0, dp.getLength()).trim();
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
