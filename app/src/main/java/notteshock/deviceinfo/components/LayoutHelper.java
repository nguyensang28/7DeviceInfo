/*
 * This is the source code of 7 Device Info.
 * It is licensed under the The GNU General Public License v3.0.
 * You should have received a copy of the license in this repo (see LICENSE).
 *
 * Copyright lahds13, 2021.
 */

package notteshock.deviceinfo.components;

import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import notteshock.deviceinfo.utilities.InfoUtilities;


public class LayoutHelper {

    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;

    private static int getSize(float size) {
        return (int) (size < 0 ? size : InfoUtilities.dp(size));
    }

    public static FrameLayout.LayoutParams createScroll(int width, int height, int gravity) {
        return new ScrollView.LayoutParams(getSize(width), getSize(height), gravity);
    }

    public static FrameLayout.LayoutParams createFrame(int width, float height, int gravity,
                                                       float leftMargin, float topMargin, float rightMargin, float bottomMargin) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(getSize(width),
                getSize(height), gravity);
        layoutParams.setMargins(InfoUtilities.dp(leftMargin), InfoUtilities.dp(topMargin),
                InfoUtilities.dp(rightMargin), InfoUtilities.dp(bottomMargin));
        return layoutParams;
    }

    public static FrameLayout.LayoutParams createFrame(int width, int height, int gravity) {
        return new FrameLayout.LayoutParams(getSize(width), getSize(height), gravity);
    }

    public static FrameLayout.LayoutParams createFrame(int width, float height) {
        return new FrameLayout.LayoutParams(getSize(width), getSize(height));
    }

    public static RelativeLayout.LayoutParams createRelative(float width, float height,
                                                             int leftMargin, int topMargin, int rightMargin, int bottomMargin, int alignParent,
                                                             int alignRelative, int anchorRelative) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(getSize(width),
                getSize(height));
        if (alignParent >= 0) {
            layoutParams.addRule(alignParent);
        }
        if (alignRelative >= 0 && anchorRelative >= 0) {
            layoutParams.addRule(alignRelative, anchorRelative);
        }
        layoutParams.leftMargin = InfoUtilities.dp(leftMargin);
        layoutParams.topMargin = InfoUtilities.dp(topMargin);
        layoutParams.rightMargin = InfoUtilities.dp(rightMargin);
        layoutParams.bottomMargin = InfoUtilities.dp(bottomMargin);
        return layoutParams;
    }

    public static RelativeLayout.LayoutParams createRelative(int width, int height, int leftMargin,
                                                             int topMargin, int rightMargin, int bottomMargin) {
        return createRelative(width, height, leftMargin, topMargin, rightMargin, bottomMargin, -1,
                -1, -1);
    }

    public static RelativeLayout.LayoutParams createRelative(int width, int height, int leftMargin,
                                                             int topMargin, int rightMargin, int bottomMargin, int alignParent) {
        return createRelative(width, height, leftMargin, topMargin, rightMargin, bottomMargin,
                alignParent, -1, -1);
    }

    public static RelativeLayout.LayoutParams createRelative(float width, float height,
                                                             int leftMargin, int topMargin, int rightMargin, int bottomMargin, int alignRelative,
                                                             int anchorRelative) {
        return createRelative(width, height, leftMargin, topMargin, rightMargin, bottomMargin, -1,
                alignRelative, anchorRelative);
    }

    public static RelativeLayout.LayoutParams createRelative(int width, int height, int alignParent,
                                                             int alignRelative, int anchorRelative) {
        return createRelative(width, height, 0, 0, 0, 0, alignParent, alignRelative,
                anchorRelative);
    }

    public static RelativeLayout.LayoutParams createRelative(int width, int height) {
        return createRelative(width, height, 0, 0, 0, 0, -1, -1, -1);
    }

    public static RelativeLayout.LayoutParams createRelative(int width, int height,
                                                             int alignParent) {
        return createRelative(width, height, 0, 0, 0, 0, alignParent, -1, -1);
    }

    public static RelativeLayout.LayoutParams createRelative(int width, int height,
                                                             int alignRelative, int anchorRelative) {
        return createRelative(width, height, 0, 0, 0, 0, -1, alignRelative, anchorRelative);
    }

    public static LinearLayout.LayoutParams createLinear(int width, int height, float weight,
                                                         int gravity, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getSize(width),
                getSize(height), weight);
        layoutParams.setMargins(InfoUtilities.dp(leftMargin), InfoUtilities.dp(topMargin),
                InfoUtilities.dp(rightMargin), InfoUtilities.dp(bottomMargin));
        layoutParams.gravity = gravity;
        return layoutParams;
    }

    public static LinearLayout.LayoutParams createLinear(int width, int height, float weight,
                                                         int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getSize(width),
                getSize(height), weight);
        layoutParams.setMargins(InfoUtilities.dp(leftMargin), InfoUtilities.dp(topMargin),
                InfoUtilities.dp(rightMargin), InfoUtilities.dp(bottomMargin));
        return layoutParams;
    }

    public static LinearLayout.LayoutParams createLinear(int width, int height, int gravity,
                                                         int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getSize(width),
                getSize(height));
        layoutParams.setMargins(InfoUtilities.dp(leftMargin), InfoUtilities.dp(topMargin),
                InfoUtilities.dp(rightMargin), InfoUtilities.dp(bottomMargin));
        layoutParams.gravity = gravity;
        return layoutParams;
    }

    public static LinearLayout.LayoutParams createLinear(int width, int height, float leftMargin,
                                                         float topMargin, float rightMargin, float bottomMargin) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getSize(width),
                getSize(height));
        layoutParams.setMargins(InfoUtilities.dp(leftMargin), InfoUtilities.dp(topMargin),
                InfoUtilities.dp(rightMargin), InfoUtilities.dp(bottomMargin));
        return layoutParams;
    }

    public static LinearLayout.LayoutParams createLinear(int width, int height, float weight,
                                                         int gravity) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getSize(width),
                getSize(height), weight);
        layoutParams.gravity = gravity;
        return layoutParams;
    }

    public static LinearLayout.LayoutParams createLinear(int width, int height, int gravity) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getSize(width),
                getSize(height));
        layoutParams.gravity = gravity;
        return layoutParams;
    }

    public static LinearLayout.LayoutParams createLinear(int width, int height, float weight) {
        return new LinearLayout.LayoutParams(getSize(width), getSize(height), weight);
    }

    public static LinearLayout.LayoutParams createLinear(int width, int height) {
        return new LinearLayout.LayoutParams(getSize(width), getSize(height));
    }
}

