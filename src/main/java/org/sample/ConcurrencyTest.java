package org.sample;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.Z_Result;

import java.lang.reflect.Executable;

import static org.openjdk.jcstress.annotations.Expect.ACCEPTABLE;
import static org.openjdk.jcstress.annotations.Expect.FORBIDDEN;

@State
@JCStressTest
@Outcome(id = "true", expect = ACCEPTABLE, desc = "Boolean value is guarded")
@Outcome(id = "false", expect = FORBIDDEN, desc = "Boolean value is not guarded")
public class ConcurrencyTest {

  ConcurrencyTest.Value value = new ConcurrencyTest.Value();

  @Actor
  public void actor1(Z_Result r) {
//    r.r1 = value.method.hasRealParameterData();
  }

  @Actor
  public void actor2(Z_Result r) {
//    r.r1 = value.method.hasRealParameterData();
  }

  @Actor
  public void actor3(Z_Result r) {
//    r.r1 = value.method.hasRealParameterData();
  }

  static class Value {
    final Executable method;

    public Value() {
      try {
        method = getClass().getMethod("foo", int.class);
      } catch (NoSuchMethodException e) {
        throw new RuntimeException(e);
      }
    }

    public void foo(int parameter) {
    }
  }
}
