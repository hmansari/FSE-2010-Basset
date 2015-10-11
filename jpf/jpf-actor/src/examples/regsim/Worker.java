package regsim;

import osl.manager.Actor;
import osl.manager.ActorName;
import osl.manager.annotations.message;

public class Worker extends Actor {

  @message
  public void boot(ActorName server, String meth) {
    send(server, meth);
  }

  @message
  public void result() {
  }
}
