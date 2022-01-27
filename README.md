# Lab 3 `class StopWatch` specifications

## Interface

A `StopWatch` object should support the
following public methods:
```java
public void start()
public void stop()
public double elapsed()
```

### `void start()`
The `start()` method should

* do nothing if the method has previously
  been called on this instance of the StopWatch,
* otherwise,
  * record the fact that `start()` has been called
    by setting a `private boolean` instance variable
    called `started` to `true` (it should initially
    be `false`)
  * record the result of `System.nanoTime()` in
    an instance field called `startTime`.

### `void stop()`

The `stop()` method should:
* do nothing if the watch is `!running()` (see below)
* otherwise,
   * record the fact that `stop()` has been called by 
     setting a `private boolean` instance variable
     called `stopped` to `true`
   * record the result of `System.nanoTime()` in
     an instance field called `stopTime`.

Note: calling `stop()` before `start()` should have
no effect.
 
### `double elapsed()`

Should return an elapsed time in *seconds*.

* If the `StopWatch` is `!running()`, it should
  compute the difference `stopTime-startTime`
  and multiply by `1e-9` to convert from nanoseconds
  to seconds.
* Otherwise, it should compute the difference
  `System.nanoTime()-startTime` and again multiply
  by `1e-9` to convert from nanoseconds to seconds.

The latter is done just in case `elapsed()` gets
called after `start()` but before `stop()`. If
`elapsed()` is called before `start()` it should
return zero.

## private methods

### `private boolean running()`

Should examine the `started` and `stopped` flags
to see if the StopWatch has been started,
but not yet stopped.