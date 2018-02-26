import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Created by jason_moo on 2018/1/15.
 */
public class UDPClient {

    public static void main(String[] args)throws IOException{
        DatagramSocket client = new DatagramSocket();
        Scanner sc=new Scanner(System.in);

        while (true){
            String str="客户端：";
            str += sc.nextLine();
            byte[] sendBuf;
            sendBuf = str.getBytes();
            InetAddress addr = InetAddress.getByName("127.0.0.1");
            int port = 5050;
            DatagramPacket sendPacket
                    = new DatagramPacket(sendBuf ,sendBuf.length , addr , port);
            client.send(sendPacket);
            byte[] recvBuf = new byte[100];
            DatagramPacket recvPacket
                    = new DatagramPacket(recvBuf , recvBuf.length);
            client.receive(recvPacket);
            String recvStr = new String(recvPacket.getData() , 0 ,recvPacket.getLength());
            System.out.println(recvStr);
        }

    }
}
