package org.sample;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.ZL_Result;

import static org.openjdk.jcstress.annotations.Expect.ACCEPTABLE;
import static org.openjdk.jcstress.annotations.Expect.FORBIDDEN;

@State
@JCStressTest
@Outcome(id = "true, String", expect = ACCEPTABLE, desc = "Boolean value was flushed")
@Outcome(id = "false, String", expect = FORBIDDEN, desc = "Boolean value was not flushed")
public class Concurrency1Test {
  Value value = new Value();

  @Actor
  public void actor1(ZL_Result r) {
    r.r1 = value.hasParameter();
    r.r2 = value.param;
  }

  @Actor
  public void actor2(ZL_Result r) {
    r.r1 = value.hasParameter();
    r.r2 = value.param;
  }

  static class Value {

    volatile boolean hasParam;
    volatile String param;

    boolean hasParameter() {
      if (param == null) {
        getParam();
      }
      return hasParam;
    }

    String getParam() {
      String tmp = param;

      if (tmp == null) {
        tmp = "String";
        hasParam = true;
        param = tmp;
      }

      return tmp;
    }
  }
}