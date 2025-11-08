package com.exemplo.nfe;

import java.util.concurrent.*;

public abstract class BaseValidadorComTimeout implements Validador {
    private final long timeout;
    protected BaseValidadorComTimeout(long timeoutMs){ this.timeout = timeoutMs; }
    @Override public long timeoutMs(){ return timeout; }

    protected <T> T executarComTimeout(Callable<T> tarefa) throws Exception {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        Future<T> f = pool.submit(tarefa);
        try { return f.get(timeout, TimeUnit.MILLISECONDS); }
        catch (TimeoutException e){ throw new RuntimeException("Timeout de " + timeout + "ms em " + getClass().getSimpleName()); }
        finally { pool.shutdownNow(); }
    }
}
