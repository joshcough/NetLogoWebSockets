package websocket;

import org.eclipse.jetty.server.Server;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultCommand;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;

public class InitCommand extends DefaultCommand {

  private boolean going = false;

  @Override
  public void perform(Argument[] arg0, Context arg1)
      throws ExtensionException, LogoException {
    if (!going) {
      CommandThread ct = new CommandThread();
      ct.start();
      going = true;
    } else {
      HelloWorld.setMessage(null);
    }
  }


  public class CommandThread extends Thread {
    @Override
    public void run() {
      try {
        WebSockServer server = new WebSockServer(8080);
        server.start();
        server.join();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
