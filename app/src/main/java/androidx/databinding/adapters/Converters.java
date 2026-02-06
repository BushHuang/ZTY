package androidx.databinding.adapters;

import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;

public class Converters {
    public static ColorStateList convertColorToColorStateList(int i) {
        return ColorStateList.valueOf(i);
    }

    public static ColorDrawable convertColorToDrawable(int i) {
        return new ColorDrawable(i);
    }
}
