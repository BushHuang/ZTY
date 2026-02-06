package butterknife.internal;

import android.view.View;

public abstract class DebouncingOnClickListener implements View.OnClickListener {
    private static final Runnable ENABLE_AGAIN = new Runnable() {
        @Override
        public void run() {
            boolean unused = DebouncingOnClickListener.enabled = true;
        }
    };
    private static boolean enabled = true;

    public abstract void doClick(View view);

    @Override
    public final void onClick(View view) {
        if (enabled) {
            enabled = false;
            view.post(ENABLE_AGAIN);
            doClick(view);
        }
    }
}
