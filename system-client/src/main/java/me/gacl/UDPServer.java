package me.gacl;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Created by jason_moo on 2018/1/15.
 */
public class UDPServer {

    public static void main(String[] args)throws IOException{
        DatagramSocket server = new DatagramSocket(5050);
        byte[] recvBuf = new byte[100];
        DatagramPacket recvPacket
                = new DatagramPacket(recvBuf , recvBuf.length);
        Scanner sc= new Scanner(System.in);
        while (true){
            server.receive(recvPacket);
            String recvStr = new String(recvPacket.getData() , 0 , recvPacket.getLength());
            System.out.println(recvStr);

            int port = recvPacket.getPort();
            InetAddress addr = recvPacket.getAddress();

            //获取用户输入的字符串
            String str="服务端：";
            str += sc.nextLine();
            byte[] sendBuf;
            sendBuf = str.getBytes();
            DatagramPacket sendPacket
                    = new DatagramPacket(sendBuf , sendBuf.length , addr , port );
            server.send(sendPacket);
        }
    }

}
