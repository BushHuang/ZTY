package androidx.lifecycle;

public interface DefaultLifecycleObserver extends FullLifecycleObserver {

    public final class CC {
        public static void $default$onCreate(DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }

        public static void $default$onDestroy(DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }

        public static void $default$onPause(DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }

        public static void $default$onResume(DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }

        public static void $default$onStart(DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }

        public static void $default$onStop(DefaultLifecycleObserver defaultLifecycleObserver, LifecycleOwner lifecycleOwner) {
        }
    }

    @Override
    void onCreate(LifecycleOwner lifecycleOwner);

    @Override
    void onDestroy(LifecycleOwner lifecycleOwner);

    @Override
    void onPause(LifecycleOwner lifecycleOwner);

    @Override
    void onResume(LifecycleOwner lifecycleOwner);

    @Override
    void onStart(LifecycleOwner lifecycleOwner);

    @Override
    void onStop(LifecycleOwner lifecycleOwner);
}
