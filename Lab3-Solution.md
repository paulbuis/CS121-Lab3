# Lab3 Solution: `class StopWatch`

The simplistic solution looks like:
```java
class StopWatch {
    private long startTime = 0;
    private long stopTime = 0;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        stopTime = System.nanoTime();
    }

    public double elapsed() {
        return (stopTime - startTime) * 1e-9;
    }
}
```

This works fine if the methods are invoked
in the expected order `start()`, then `stop()`, then `elapsed()`.

However, it isn't clear if the behavior is correct if
the order is:
* `start()`, `start()`, `stop()`, `elapsed()`
* `start()`, `stop()`, `stop()`, `elapsed()`
* `start()`, `stop()`, `start()`, `elapsed()`
* `start()`, `elapsed()`, `elapsed()`
* `elapsed()`, `start()`, ...

So, the rule I made was that the value returned
by an earlier invocation of `elapsed()` and a
later invocation should never be decreasing.
I also decided I didn't want `start()` to "restart"
the timing.

One way of dealing with
method invocations that are
not in the expected order would
be to throw an `IllegalStateException`
and crash the program if something
unexpected like this happens:

```java
class StopWatch {
    private long startTime = 0;
    private long stopTime = 0;

    public void start() {
        if (startTime != 0) {
            throw new IllegalStateException("start() called twice");
        }
        startTime = System.nanoTime();
    }

    public void stop() {
        if (startTime == 0) {
            throw new IllegalStateException("stop() called before start()");
        }
        if (stopTime != 0) {
            throw new IllegalStateException("stop() called twice");
        }
        stopTime = System.nanoTime();
    }

    public double elapsed() {
        if (startTime == 0) {
            throw new IllegalStateException("elapsed() called before start()");
        }
        if (stopTime == 0) {
            throw new IllegalStateException("elapsed{} called before stop()");
        }
        return (stopTime - startTime) * 1e-9;
    }
}
```

A different approach would be to just "do nothing" if the
method returns `void` and "make something reasonable up" if
the method needs to return a value:

```java
class StopWatch {
    private long startTime = 0;
    private long stopTime = 0;

    public void start() {
        if (startTime != 0) {
            return; // start called twice
        }
        startTime = System.nanoTime();
    }

    public void stop() {
        if (startTime == 0) {
            startTime = stopTime = 1; // maybe ???
            return; // stop() called before start()
        }
        if (stopTime != 0) {
            return; // stop() called twice
        }
        stopTime = System.nanoTime();
    }

    public double elapsed() {
        if (startTime == 0) {
            // elapsed() called before start()
            return 0.0;
        }
        if (stopTime == 0) {
            // elapsed() called before stop()
            return (System.nanoTime() - startTime) * 1e-9;
        }
        return (stopTime - startTime) * 1e-9;
    }
}
```

The specifications said to do something equivalent to
this final code.

The instructions said to use booleans `started` and `stopped`
that would be set from `false` to `true` at the same time the
as `startTime` or `stopTime` were set to a non-zero value. In
retrospect this seems unnecessarily complex when a check for
`startTime != 0` would be equivalent to checking `started`.