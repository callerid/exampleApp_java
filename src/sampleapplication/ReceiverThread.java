/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sampleapplication;

/**
 *
 * @author Grant
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
/**
 *
 * @author Grant
 */
class ReceiverThread extends Thread {
  
  private boolean stopped = false;
  private DatagramSocket socket;
  
  public ReceiverThread(int port) throws Exception {
    
    this.socket = new DatagramSocket(port);
  }
    
  public ReceiverThread(DatagramSocket ds) throws SocketException {
    this.socket = ds;
  }

  public void halt() {
    this.stopped = true;
  }

  public void run() {
    byte[] buffer = new byte[65507];
    while (true) {
      if (stopped)
        return;
      DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
      try {
        socket.receive(dp);
        String s = new String(dp.getData(), 0, dp.getLength());
        
        // Send reception data to handling method
        System.out.print(s);
        frmMain.handleData(s);;
        
        Thread.yield();
      } catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }
}

