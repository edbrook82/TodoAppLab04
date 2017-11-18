package uk.co.dekoorb.c3469162.todoapplab04.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.Date;
import java.util.UUID;

import uk.co.dekoorb.c3469162.todoapplab04.BR;

/**
 * Created by c3469162 on 10/11/2017.
 */

public class Todo extends BaseObservable {
    private UUID mId;
    private String mTitle;
    private String mDetail;
    private Date mDate;
    private boolean mIsComplete;

    public Todo() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    @Bindable
    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        this.mId = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        this.mDetail = detail;
        notifyPropertyChanged(BR.detail);
    }

    @Bindable
    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
        notifyPropertyChanged(BR.date);
    }

    @Bindable
    public boolean isComplete() {
        return mIsComplete;
    }

    public void setComplete(boolean isComplete) {
        this.mIsComplete = isComplete;
        notifyPropertyChanged(BR.complete);
    }
}
