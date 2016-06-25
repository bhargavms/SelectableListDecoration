package com.bhargavmogra.selectablelistdecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * An item decoration for the selected hero in the hero list.
 */
public class SelectedItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDrawable;
    private Paint mStrokePaint;
    private Path mStrokePath;

    public SelectedItemDecoration(@ColorInt int color, float strokeWidth) {
        init();
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setColor(color);
        mStrokePaint.setStrokeWidth(strokeWidth);

//        this.mDrawable = c.getResources().getDrawable(R.drawable.stroke_only);
    }

    private void init() {
        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStrokePath = new Path();
    }

    public SelectedItemDecoration(Context c, @ColorRes int color, @DimenRes int strokeWidth) {
        init();
        float widthOfStroke = c.getResources().getDimension(strokeWidth);
        int colorOfStroke = c.getResources().getColor(color);
        mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setColor(colorOfStroke);
        mStrokePaint.setStrokeWidth(widthOfStroke);
    }

    public SelectedItemDecoration(Drawable drawable) {
        mDrawable = drawable;
    }

    public SelectedItemDecoration(Context c, @DrawableRes int drawableId) {
        this.mDrawable = c.getResources().getDrawable(drawableId);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.Adapter adapter = parent.getAdapter();
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            int position = parent.getChildViewHolder(child).getAdapterPosition();
            if (adapter instanceof SelectableAdapter) {
                if (((SelectableAdapter) adapter).isSelected(position)) {
                    int left = child.getLeft();
                    int top = child.getTop();
                    int right = child.getRight();
                    int bottom = child.getBottom();
                    if (mDrawable != null) {
                        mDrawable.setBounds(left, top, right, bottom);
                        mDrawable.draw(c);
                    } else {
                        mStrokePath.reset();
                        mStrokePath.moveTo(left, top);
                        mStrokePath.lineTo(right, top);
                        mStrokePath.lineTo(right, bottom);
                        mStrokePath.lineTo(left, bottom);
                        mStrokePath.lineTo(left, top);
                        c.drawPath(mStrokePath, mStrokePaint);
                    }
                }
            } else {
                throw new RuntimeException("Please implement SelectableViewHolder in your " +
                        "recyclerview's viewholder class");
            }
        }
    }

    public interface SelectableAdapter {
        boolean isSelected(int position);
    }
}
