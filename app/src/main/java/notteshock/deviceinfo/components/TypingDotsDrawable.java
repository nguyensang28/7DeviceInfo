/*
 * This is the source code of 7 Device Info.
 * It is licensed under the The GNU General Public License v3.0.
 * You should have received a copy of the license in this repo (see LICENSE).
 *
 * Copyright lahds13, 2021.
 */

package notteshock.deviceinfo.components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.animation.DecelerateInterpolator;

import notteshock.deviceinfo.utilities.InfoUtilities;
import notteshock.deviceinfo.utilities.NotificationCenter;
import notteshock.deviceinfo.components.actionbar.Theme;

public class TypingDotsDrawable extends StatusDrawable {

    private final int currentAccount = 1;
    private boolean isChat = false;
    private final float[] scales = new float[3];
    private final float[] startTimes = new float[] {0, 150, 300};
    private final float[] elapsedTimes = new float[] {0, 0, 0};
    private long lastUpdateTime = 0;
    private boolean started = false;
    private final DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator();

    private Paint currentPaint;

    public TypingDotsDrawable(boolean createPaint) {
        if (createPaint) {
            currentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
    }

    @Override
    public void setColor(int color) {
        if (currentPaint != null) {
            currentPaint.setColor(color);
        }
    }

    public void setIsChat(boolean value) {
        isChat = value;
    }

    private void update() {
        long newTime = System.currentTimeMillis();
        long dt = newTime - lastUpdateTime;
        lastUpdateTime = newTime;
        if (dt > 50) {
            dt = 50;
        }

        for (int a = 0; a < 3; a++) {
            elapsedTimes[a] += dt;
            float timeSinceStart = elapsedTimes[a] - startTimes[a];
            if (timeSinceStart > 0) {
                if (timeSinceStart <= 320) {
                    float diff = decelerateInterpolator.getInterpolation(timeSinceStart / 320.0f);
                    scales[a] = 1.33f + diff;
                } else if (timeSinceStart <= 640) {
                    float diff = decelerateInterpolator.getInterpolation((timeSinceStart - 320.0f) / 320.0f);
                    scales[a] = 1.33f + (1 - diff);
                } else if (timeSinceStart >= 800) {
                    elapsedTimes[a] = 0;
                    startTimes[a] = 0;
                    scales[a] = 1.33f;
                } else {
                    scales[a] = 1.33f;
                }
            } else {
                scales[a] = 1.33f;
            }
        }

        invalidateSelf();
    }

    public void start() {
        lastUpdateTime = System.currentTimeMillis();
        started = true;
        invalidateSelf();
    }

    public void stop() {
        for (int a = 0; a < 3; a++) {
            elapsedTimes[a] = 0;
            scales[a] = 1.33f;
        }
        startTimes[0] = 0;
        startTimes[1] = 150;
        startTimes[2] = 300;
        started = false;
    }

    @Override
    public void draw(Canvas canvas) {
        int y;
        if (isChat) {
            y = InfoUtilities.dp(8.5f) + getBounds().top;
        } else {
            y = InfoUtilities.dp(9.3f) + getBounds().top;
        }
        Paint paint;
        if (currentPaint == null) {
            paint = Theme.chat_statusPaint;
            paint.setAlpha(255);
            paint.setColor(Color.parseColor("#ffffff"));
            paint.setColorFilter(new PorterDuffColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.MULTIPLY));
        } else {
            paint = currentPaint;
        }

        canvas.drawCircle(InfoUtilities.dp(3), y, scales[0] * InfoUtilities.density, paint);
        canvas.drawCircle(InfoUtilities.dp(9), y, scales[1] * InfoUtilities.density, paint);
        canvas.drawCircle(InfoUtilities.dp(15), y, scales[2] * InfoUtilities.density, paint);
        checkUpdate();
    }

    private void checkUpdate() {
        if (started) {
            if (!NotificationCenter.getInstance().isAnimationInProgress()) {
                update();
            } else {
                InfoUtilities.runOnUIThread(this::checkUpdate, 100);
            }
        }
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return InfoUtilities.dp(18);
    }

    @Override
    public int getIntrinsicHeight() {
        return InfoUtilities.dp(18);
    }
}

