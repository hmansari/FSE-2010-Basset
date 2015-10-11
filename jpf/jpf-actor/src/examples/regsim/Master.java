package regsim;

import gov.nasa.jpf.actor.core.PlatformUtil;

import java.util.List;

import osl.manager.Actor;
import osl.manager.ActorName;
import osl.manager.annotations.message;

public class Master extends Actor {

  private final ActorName[] children;
  int response = 0;
  int Num = 0;

  public Master(Integer N) {
    this.children = new ActorName[N];
    this.Num = N;
  }

  @message
  public void boot() {
    for (int i = 0; i < children.length; i++) {
      // children[i] = PlatformUtil.createActor(Worker2.class, Num,
      // self(),"result" );
      children[i] = PlatformUtil.createActor(Worker.class);
    }

    send(children[0], "result");
    // send (children[0], "result");
    // send (children[0], "result");
    for (int i = 1; i < Num; i++) {
      send(children[i], "boot", children[0], "result");
    }
  }

  @message
  public void result() {
    if (response < Num)
      response++;
    else
      System.err.println("ERROR!!!");
  }
}
