package com.huawei.hms.framework.common;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExecutorsEnhance {

    private static class DelegatedExecutorService extends AbstractExecutorService {
        private final ExecutorService executorService;

        DelegatedExecutorService(ExecutorService executorService) {
            this.executorService = executorService;
        }

        @Override
        public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.executorService.awaitTermination(j, timeUnit);
        }

        @Override
        public void execute(Runnable runnable) {
            this.executorService.execute(runnable);
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
            return this.executorService.invokeAll(collection);
        }

        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
            return this.executorService.invokeAll(collection, j, timeUnit);
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws ExecutionException, InterruptedException {
            return (T) this.executorService.invokeAny(collection);
        }

        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
            return (T) this.executorService.invokeAny(collection, j, timeUnit);
        }

        @Override
        public boolean isShutdown() {
            return this.executorService.isShutdown();
        }

        @Override
        public boolean isTerminated() {
            return this.executorService.isTerminated();
        }

        @Override
        public void shutdown() {
            this.executorService.shutdown();
        }

        @Override
        public List<Runnable> shutdownNow() {
            return this.executorService.shutdownNow();
        }

        @Override
        public Future<?> submit(Runnable runnable) {
            return this.executorService.submit(runnable);
        }

        @Override
        public <T> Future<T> submit(Runnable runnable, T t) {
            return this.executorService.submit(runnable, t);
        }

        @Override
        public <T> Future<T> submit(Callable<T> callable) {
            return this.executorService.submit(callable);
        }
    }

    private static class FinalizableDelegatedExecutorService extends DelegatedExecutorService {
        FinalizableDelegatedExecutorService(ExecutorService executorService) {
            super(executorService);
        }

        protected void finalize() {
            super.shutdown();
        }
    }

    public static ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory) {
        return new FinalizableDelegatedExecutorService(new ThreadPoolExcutorEnhance(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), threadFactory));
    }
}
