package me.gacl;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 5050));
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector,
                SelectionKey.OP_READ);

        while (true){
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            SelectionKey selectionKey ;
            while (keyIterator.hasNext()){
                selectionKey = keyIterator.next();
                selectionKey.readyOps();
            }
        }
    }
}
