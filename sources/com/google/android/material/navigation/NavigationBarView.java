package com.google.android.material.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuView;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class NavigationBarView extends FrameLayout {
    public static final int LABEL_VISIBILITY_AUTO = -1;
    public static final int LABEL_VISIBILITY_LABELED = 1;
    public static final int LABEL_VISIBILITY_SELECTED = 0;
    public static final int LABEL_VISIBILITY_UNLABELED = 2;
    private static final int MENU_PRESENTER_ID = 1;
    private final NavigationBarMenu menu;
    private MenuInflater menuInflater;
    private final NavigationBarMenuView menuView;
    private final NavigationBarPresenter presenter;
    /* access modifiers changed from: private */
    public OnItemReselectedListener reselectedListener;
    /* access modifiers changed from: private */
    public OnItemSelectedListener selectedListener;

    @Retention(RetentionPolicy.SOURCE)
    public @interface LabelVisibility {
    }

    public interface OnItemReselectedListener {
        void onNavigationItemReselected(MenuItem menuItem);
    }

    public interface OnItemSelectedListener {
        boolean onNavigationItemSelected(MenuItem menuItem);
    }

    /* access modifiers changed from: protected */
    public abstract NavigationBarMenuView createNavigationBarMenuView(Context context);

    public abstract int getMaxItemCount();

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NavigationBarView(android.content.Context r22, android.util.AttributeSet r23, int r24, int r25) {
        /*
            r21 = this;
            r0 = r21
            r7 = r23
            r8 = r24
            android.content.Context r1 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r22, r23, r24, r25)
            r0.<init>(r1, r7, r8)
            com.google.android.material.navigation.NavigationBarPresenter r9 = new com.google.android.material.navigation.NavigationBarPresenter
            r9.<init>()
            r0.presenter = r9
            android.content.Context r10 = r21.getContext()
            int[] r3 = com.google.android.material.R.styleable.NavigationBarView
            r1 = 2
            int[] r6 = new int[r1]
            int r1 = com.google.android.material.R.styleable.NavigationBarView_itemTextAppearanceInactive
            r11 = 0
            r6[r11] = r1
            int r1 = com.google.android.material.R.styleable.NavigationBarView_itemTextAppearanceActive
            r12 = 1
            r6[r12] = r1
            r1 = r10
            r2 = r23
            r4 = r24
            r5 = r25
            androidx.appcompat.widget.TintTypedArray r1 = com.google.android.material.internal.ThemeEnforcement.obtainTintedStyledAttributes(r1, r2, r3, r4, r5, r6)
            com.google.android.material.navigation.NavigationBarMenu r2 = new com.google.android.material.navigation.NavigationBarMenu
            java.lang.Class r3 = r21.getClass()
            int r4 = r21.getMaxItemCount()
            r2.<init>(r10, r3, r4)
            r0.menu = r2
            com.google.android.material.navigation.NavigationBarMenuView r3 = r0.createNavigationBarMenuView(r10)
            r0.menuView = r3
            r9.setMenuView(r3)
            r9.setId(r12)
            r3.setPresenter(r9)
            r2.addMenuPresenter(r9)
            android.content.Context r4 = r21.getContext()
            r9.initForMenu(r4, r2)
            int r4 = com.google.android.material.R.styleable.NavigationBarView_itemIconTint
            boolean r4 = r1.hasValue(r4)
            if (r4 == 0) goto L_0x006c
            int r4 = com.google.android.material.R.styleable.NavigationBarView_itemIconTint
            android.content.res.ColorStateList r4 = r1.getColorStateList(r4)
            r3.setIconTintList(r4)
            goto L_0x0077
        L_0x006c:
            r4 = 16842808(0x1010038, float:2.3693715E-38)
            android.content.res.ColorStateList r4 = r3.createDefaultColorStateList(r4)
            r3.setIconTintList(r4)
        L_0x0077:
            int r4 = com.google.android.material.R.styleable.NavigationBarView_itemIconSize
            android.content.res.Resources r5 = r21.getResources()
            int r6 = com.google.android.material.R.dimen.mtrl_navigation_bar_item_default_icon_size
            int r5 = r5.getDimensionPixelSize(r6)
            int r4 = r1.getDimensionPixelSize(r4, r5)
            r0.setItemIconSize(r4)
            int r4 = com.google.android.material.R.styleable.NavigationBarView_itemTextAppearanceInactive
            boolean r4 = r1.hasValue(r4)
            if (r4 == 0) goto L_0x009b
            int r4 = com.google.android.material.R.styleable.NavigationBarView_itemTextAppearanceInactive
            int r4 = r1.getResourceId(r4, r11)
            r0.setItemTextAppearanceInactive(r4)
        L_0x009b:
            int r4 = com.google.android.material.R.styleable.NavigationBarView_itemTextAppearanceActive
            boolean r4 = r1.hasValue(r4)
            if (r4 == 0) goto L_0x00ac
            int r4 = com.google.android.material.R.styleable.NavigationBarView_itemTextAppearanceActive
            int r4 = r1.getResourceId(r4, r11)
            r0.setItemTextAppearanceActive(r4)
        L_0x00ac:
            int r4 = com.google.android.material.R.styleable.NavigationBarView_itemTextAppearanceActiveBoldEnabled
            boolean r4 = r1.getBoolean(r4, r12)
            r0.setItemTextAppearanceActiveBoldEnabled(r4)
            int r5 = com.google.android.material.R.styleable.NavigationBarView_itemTextColor
            boolean r5 = r1.hasValue(r5)
            if (r5 == 0) goto L_0x00c6
            int r5 = com.google.android.material.R.styleable.NavigationBarView_itemTextColor
            android.content.res.ColorStateList r5 = r1.getColorStateList(r5)
            r0.setItemTextColor(r5)
        L_0x00c6:
            android.graphics.drawable.Drawable r5 = r21.getBackground()
            android.content.res.ColorStateList r6 = com.google.android.material.drawable.DrawableUtils.getColorStateListOrNull(r5)
            if (r5 == 0) goto L_0x00d6
            if (r6 == 0) goto L_0x00d3
            goto L_0x00d6
        L_0x00d3:
            r9 = r25
            goto L_0x00f1
        L_0x00d6:
            r9 = r25
            com.google.android.material.shape.ShapeAppearanceModel$Builder r13 = com.google.android.material.shape.ShapeAppearanceModel.builder((android.content.Context) r10, (android.util.AttributeSet) r7, (int) r8, (int) r9)
            com.google.android.material.shape.ShapeAppearanceModel r13 = r13.build()
            com.google.android.material.shape.MaterialShapeDrawable r14 = new com.google.android.material.shape.MaterialShapeDrawable
            r14.<init>((com.google.android.material.shape.ShapeAppearanceModel) r13)
            if (r6 == 0) goto L_0x00eb
            r14.setFillColor(r6)
        L_0x00eb:
            r14.initializeElevationOverlay(r10)
            androidx.core.view.ViewCompat.setBackground(r0, r14)
        L_0x00f1:
            int r13 = com.google.android.material.R.styleable.NavigationBarView_itemPaddingTop
            boolean r13 = r1.hasValue(r13)
            if (r13 == 0) goto L_0x0102
            int r13 = com.google.android.material.R.styleable.NavigationBarView_itemPaddingTop
            int r13 = r1.getDimensionPixelSize(r13, r11)
            r0.setItemPaddingTop(r13)
        L_0x0102:
            int r13 = com.google.android.material.R.styleable.NavigationBarView_itemPaddingBottom
            boolean r13 = r1.hasValue(r13)
            if (r13 == 0) goto L_0x0113
            int r13 = com.google.android.material.R.styleable.NavigationBarView_itemPaddingBottom
            int r13 = r1.getDimensionPixelSize(r13, r11)
            r0.setItemPaddingBottom(r13)
        L_0x0113:
            int r13 = com.google.android.material.R.styleable.NavigationBarView_activeIndicatorLabelPadding
            boolean r13 = r1.hasValue(r13)
            if (r13 == 0) goto L_0x0124
            int r13 = com.google.android.material.R.styleable.NavigationBarView_activeIndicatorLabelPadding
            int r13 = r1.getDimensionPixelSize(r13, r11)
            r0.setActiveIndicatorLabelPadding(r13)
        L_0x0124:
            int r13 = com.google.android.material.R.styleable.NavigationBarView_elevation
            boolean r13 = r1.hasValue(r13)
            if (r13 == 0) goto L_0x0136
            int r13 = com.google.android.material.R.styleable.NavigationBarView_elevation
            int r13 = r1.getDimensionPixelSize(r13, r11)
            float r13 = (float) r13
            r0.setElevation(r13)
        L_0x0136:
            int r13 = com.google.android.material.R.styleable.NavigationBarView_backgroundTint
            android.content.res.ColorStateList r13 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r10, (androidx.appcompat.widget.TintTypedArray) r1, (int) r13)
            android.graphics.drawable.Drawable r14 = r21.getBackground()
            android.graphics.drawable.Drawable r14 = r14.mutate()
            androidx.core.graphics.drawable.DrawableCompat.setTintList(r14, r13)
            int r14 = com.google.android.material.R.styleable.NavigationBarView_labelVisibilityMode
            r15 = -1
            int r14 = r1.getInteger(r14, r15)
            r0.setLabelVisibilityMode(r14)
            int r14 = com.google.android.material.R.styleable.NavigationBarView_itemBackground
            int r14 = r1.getResourceId(r14, r11)
            if (r14 == 0) goto L_0x015d
            r3.setItemBackgroundRes(r14)
            goto L_0x0166
        L_0x015d:
            int r15 = com.google.android.material.R.styleable.NavigationBarView_itemRippleColor
            android.content.res.ColorStateList r15 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r10, (androidx.appcompat.widget.TintTypedArray) r1, (int) r15)
            r0.setItemRippleColor(r15)
        L_0x0166:
            int r15 = com.google.android.material.R.styleable.NavigationBarView_itemActiveIndicatorStyle
            int r15 = r1.getResourceId(r15, r11)
            if (r15 == 0) goto L_0x01bc
            r0.setItemActiveIndicatorEnabled(r12)
            int[] r12 = com.google.android.material.R.styleable.NavigationBarActiveIndicator
            android.content.res.TypedArray r12 = r10.obtainStyledAttributes(r15, r12)
            r22 = r4
            int r4 = com.google.android.material.R.styleable.NavigationBarActiveIndicator_android_width
            int r4 = r12.getDimensionPixelSize(r4, r11)
            r0.setItemActiveIndicatorWidth(r4)
            r16 = r4
            int r4 = com.google.android.material.R.styleable.NavigationBarActiveIndicator_android_height
            int r4 = r12.getDimensionPixelSize(r4, r11)
            r0.setItemActiveIndicatorHeight(r4)
            r17 = r4
            int r4 = com.google.android.material.R.styleable.NavigationBarActiveIndicator_marginHorizontal
            int r4 = r12.getDimensionPixelOffset(r4, r11)
            r0.setItemActiveIndicatorMarginHorizontal(r4)
            int r11 = com.google.android.material.R.styleable.NavigationBarActiveIndicator_android_color
            android.content.res.ColorStateList r11 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r10, (android.content.res.TypedArray) r12, (int) r11)
            r0.setItemActiveIndicatorColor(r11)
            r18 = r4
            int r4 = com.google.android.material.R.styleable.NavigationBarActiveIndicator_shapeAppearance
            r19 = r5
            r5 = 0
            int r4 = r12.getResourceId(r4, r5)
            com.google.android.material.shape.ShapeAppearanceModel$Builder r20 = com.google.android.material.shape.ShapeAppearanceModel.builder(r10, r4, r5)
            com.google.android.material.shape.ShapeAppearanceModel r5 = r20.build()
            r0.setItemActiveIndicatorShapeAppearance(r5)
            r12.recycle()
            goto L_0x01c0
        L_0x01bc:
            r22 = r4
            r19 = r5
        L_0x01c0:
            int r4 = com.google.android.material.R.styleable.NavigationBarView_menu
            boolean r4 = r1.hasValue(r4)
            if (r4 == 0) goto L_0x01d2
            int r4 = com.google.android.material.R.styleable.NavigationBarView_menu
            r5 = 0
            int r4 = r1.getResourceId(r4, r5)
            r0.inflateMenu(r4)
        L_0x01d2:
            r1.recycle()
            r0.addView(r3)
            com.google.android.material.navigation.NavigationBarView$1 r3 = new com.google.android.material.navigation.NavigationBarView$1
            r3.<init>()
            r2.setCallback(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationBarView.<init>(android.content.Context, android.util.AttributeSet, int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    public void setElevation(float elevation) {
        super.setElevation(elevation);
        MaterialShapeUtils.setElevation(this, elevation);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        this.selectedListener = listener;
    }

    public void setOnItemReselectedListener(OnItemReselectedListener listener) {
        this.reselectedListener = listener;
    }

    public Menu getMenu() {
        return this.menu;
    }

    public MenuView getMenuView() {
        return this.menuView;
    }

    public void inflateMenu(int resId) {
        this.presenter.setUpdateSuspended(true);
        getMenuInflater().inflate(resId, this.menu);
        this.presenter.setUpdateSuspended(false);
        this.presenter.updateMenuView(true);
    }

    public ColorStateList getItemIconTintList() {
        return this.menuView.getIconTintList();
    }

    public void setItemIconTintList(ColorStateList tint) {
        this.menuView.setIconTintList(tint);
    }

    public void setItemIconSize(int iconSize) {
        this.menuView.setItemIconSize(iconSize);
    }

    public void setItemIconSizeRes(int iconSizeRes) {
        setItemIconSize(getResources().getDimensionPixelSize(iconSizeRes));
    }

    public int getItemIconSize() {
        return this.menuView.getItemIconSize();
    }

    public ColorStateList getItemTextColor() {
        return this.menuView.getItemTextColor();
    }

    public void setItemTextColor(ColorStateList textColor) {
        this.menuView.setItemTextColor(textColor);
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.menuView.getItemBackgroundRes();
    }

    public void setItemBackgroundResource(int resId) {
        this.menuView.setItemBackgroundRes(resId);
    }

    public Drawable getItemBackground() {
        return this.menuView.getItemBackground();
    }

    public void setItemBackground(Drawable background) {
        this.menuView.setItemBackground(background);
    }

    public ColorStateList getItemRippleColor() {
        return this.menuView.getItemRippleColor();
    }

    public void setItemRippleColor(ColorStateList itemRippleColor) {
        this.menuView.setItemRippleColor(itemRippleColor);
    }

    public int getItemPaddingTop() {
        return this.menuView.getItemPaddingTop();
    }

    public void setItemPaddingTop(int paddingTop) {
        this.menuView.setItemPaddingTop(paddingTop);
    }

    public int getItemPaddingBottom() {
        return this.menuView.getItemPaddingBottom();
    }

    public void setItemPaddingBottom(int paddingBottom) {
        this.menuView.setItemPaddingBottom(paddingBottom);
    }

    public void setActiveIndicatorLabelPadding(int activeIndicatorLabelPadding) {
        this.menuView.setActiveIndicatorLabelPadding(activeIndicatorLabelPadding);
    }

    public int getActiveIndicatorLabelPadding() {
        return this.menuView.getActiveIndicatorLabelPadding();
    }

    public boolean isItemActiveIndicatorEnabled() {
        return this.menuView.getItemActiveIndicatorEnabled();
    }

    public void setItemActiveIndicatorEnabled(boolean enabled) {
        this.menuView.setItemActiveIndicatorEnabled(enabled);
    }

    public int getItemActiveIndicatorWidth() {
        return this.menuView.getItemActiveIndicatorWidth();
    }

    public void setItemActiveIndicatorWidth(int width) {
        this.menuView.setItemActiveIndicatorWidth(width);
    }

    public int getItemActiveIndicatorHeight() {
        return this.menuView.getItemActiveIndicatorHeight();
    }

    public void setItemActiveIndicatorHeight(int height) {
        this.menuView.setItemActiveIndicatorHeight(height);
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.menuView.getItemActiveIndicatorMarginHorizontal();
    }

    public void setItemActiveIndicatorMarginHorizontal(int horizontalMargin) {
        this.menuView.setItemActiveIndicatorMarginHorizontal(horizontalMargin);
    }

    public ShapeAppearanceModel getItemActiveIndicatorShapeAppearance() {
        return this.menuView.getItemActiveIndicatorShapeAppearance();
    }

    public void setItemActiveIndicatorShapeAppearance(ShapeAppearanceModel shapeAppearance) {
        this.menuView.setItemActiveIndicatorShapeAppearance(shapeAppearance);
    }

    public ColorStateList getItemActiveIndicatorColor() {
        return this.menuView.getItemActiveIndicatorColor();
    }

    public void setItemActiveIndicatorColor(ColorStateList csl) {
        this.menuView.setItemActiveIndicatorColor(csl);
    }

    public int getSelectedItemId() {
        return this.menuView.getSelectedItemId();
    }

    public void setSelectedItemId(int itemId) {
        MenuItem item = this.menu.findItem(itemId);
        if (item != null && !this.menu.performItemAction(item, this.presenter, 0)) {
            item.setChecked(true);
        }
    }

    public void setLabelVisibilityMode(int labelVisibilityMode) {
        if (this.menuView.getLabelVisibilityMode() != labelVisibilityMode) {
            this.menuView.setLabelVisibilityMode(labelVisibilityMode);
            this.presenter.updateMenuView(false);
        }
    }

    public int getLabelVisibilityMode() {
        return this.menuView.getLabelVisibilityMode();
    }

    public void setItemTextAppearanceInactive(int textAppearanceRes) {
        this.menuView.setItemTextAppearanceInactive(textAppearanceRes);
    }

    public int getItemTextAppearanceInactive() {
        return this.menuView.getItemTextAppearanceInactive();
    }

    public void setItemTextAppearanceActive(int textAppearanceRes) {
        this.menuView.setItemTextAppearanceActive(textAppearanceRes);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean isBold) {
        this.menuView.setItemTextAppearanceActiveBoldEnabled(isBold);
    }

    public int getItemTextAppearanceActive() {
        return this.menuView.getItemTextAppearanceActive();
    }

    public void setItemOnTouchListener(int menuItemId, View.OnTouchListener onTouchListener) {
        this.menuView.setItemOnTouchListener(menuItemId, onTouchListener);
    }

    public BadgeDrawable getBadge(int menuItemId) {
        return this.menuView.getBadge(menuItemId);
    }

    public BadgeDrawable getOrCreateBadge(int menuItemId) {
        return this.menuView.getOrCreateBadge(menuItemId);
    }

    public void removeBadge(int menuItemId) {
        this.menuView.removeBadge(menuItemId);
    }

    private MenuInflater getMenuInflater() {
        if (this.menuInflater == null) {
            this.menuInflater = new SupportMenuInflater(getContext());
        }
        return this.menuInflater;
    }

    public NavigationBarPresenter getPresenter() {
        return this.presenter;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.menuPresenterState = new Bundle();
        this.menu.savePresenterStates(savedState.menuPresenterState);
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.menu.restorePresenterStates(savedState.menuPresenterState);
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                return new SavedState(in, loader);
            }

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in, (ClassLoader) null);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        Bundle menuPresenterState;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel source, ClassLoader loader) {
            super(source, loader);
            readFromParcel(source, loader == null ? getClass().getClassLoader() : loader);
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeBundle(this.menuPresenterState);
        }

        private void readFromParcel(Parcel in, ClassLoader loader) {
            this.menuPresenterState = in.readBundle(loader);
        }
    }
}
