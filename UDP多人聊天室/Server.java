package UDP多人聊天室;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(8888);
        List<HashMap<String, String>> Clientlist = new ArrayList<>(); //客户端列表,ip,port
        while (true) {
            // 接收数据
            byte[] bytes = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bytes, 1024);
            ds.receive(dp);

            String ip = dp.getAddress().getHostAddress();
            String port = dp.getPort() + "";
            boolean key = true;
            for (HashMap<String, String> map : Clientlist) { //添加到客户端列表中
                if (map.get("ip").equals(ip) && map.get("port").equals(port)) {
                    key = false;
                    break;
                }
            }
            if (key == true) {
                HashMap<String, String> map = new HashMap<>();
                map.put("ip", ip);
                map.put("port", port);
                Clientlist.add(map);
            }

            for (HashMap<String, String> map : Clientlist) { //
                int targetport = Integer.parseInt(map.get("port"));
                InetAddress targetAddress = InetAddress.getByName(map.get("ip"));
                DatagramPacket dp1 = new DatagramPacket(bytes, 0, bytes.length,targetAddress,targetport);
                ds.send(dp1);

            }
        }
    }
}
