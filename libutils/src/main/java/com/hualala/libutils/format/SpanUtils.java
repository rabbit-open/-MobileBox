//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hualala.libutils.format;

import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import com.hualala.libutils.view.UIUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpanUtils {
    public SpanUtils() {
    }

    public static SpannableString MultiMatch(String content, @ColorRes int colorRes) {
        String rewardNote = content.replaceAll("<|>", "");
        SpannableString spannableString = new SpannableString(rewardNote);
        String regex = "<.*?>";
        Matcher matcher = Pattern.compile(regex).matcher(content);
        int groupCount = 0;

        while(matcher.find()) {
            ++groupCount;
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(UIUtils.getContext(), colorRes)), matcher.start() - 2 * (groupCount - 1), matcher.end() - 2 * groupCount, 33);
        }

        return spannableString;
    }

    public static SpannableString MultiMatch(String regex, String content, @ColorRes int colorRes) {
        SpannableString spannableString = new SpannableString(content);
        Matcher matcher = Pattern.compile(regex).matcher(content);

        while(matcher.find()) {
            spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(UIUtils.getContext(), colorRes)), matcher.start(), matcher.end(), 33);
        }

        return spannableString;
    }

    private static class SpanParams {
        int size;
        int color;
        int style;
        boolean deleteLine;
        Object span;

        private SpanParams() {
        }
    }

    public static class Builder {
        private CharSequence mText;
        private String mRegex;
        private int mStart;
        private int mEnd;
        private SpanUtils.SpanParams mParams;

        public Builder(CharSequence text, String regex) {
            this.mText = text;
            this.mRegex = regex;
            this.mParams = new SpanUtils.SpanParams();
        }

        public Builder(CharSequence text, int start) {
            this(text, start, text == null ? 0 : text.length());
        }

        public Builder(CharSequence text, int start, int end) {
            this.mText = text;
            this.mStart = start;
            this.mEnd = end;
            this.mParams = new SpanUtils.SpanParams();
        }

        public SpanUtils.Builder setSize(int size) {
            this.mParams.size = size;
            return this;
        }

        public SpanUtils.Builder setSize(int size, boolean dip) {
            this.mParams.size = dip ? UIUtils.dp2px((float)size) : size;
            return this;
        }

        public SpanUtils.Builder setSizeDip(int size) {
            return this.setSize(size, true);
        }

        public SpanUtils.Builder setSizeRes(int sizeRes) {
            this.mParams.size = UIUtils.getDimension(sizeRes);
            return this;
        }

        public SpanUtils.Builder setColor(int color) {
            this.mParams.color = color;
            return this;
        }

        public SpanUtils.Builder setColorRes(int colorRes) {
            this.mParams.color = UIUtils.getColor(colorRes);
            return this;
        }

        public SpanUtils.Builder setStyle(int style) {
            this.mParams.style = style;
            return this;
        }

        public SpanUtils.Builder withDeleteLine() {
            return this.withDeleteLine(true);
        }

        public SpanUtils.Builder withDeleteLine(boolean deleteLine) {
            this.mParams.deleteLine = deleteLine;
            return this;
        }

        public SpanUtils.Builder setSpan(Object span) {
            this.mParams.span = span;
            return this;
        }

        public SpannableString build() {
            if (this.mText == null) {
                return null;
            } else {
                SpannableString spanString = this.mText instanceof SpannableString ? (SpannableString)this.mText : new SpannableString(this.mText);
                int start = 0;
                int end = 0;
                if (!TextUtils.isEmpty(this.mRegex)) {
                    Matcher matcher = Pattern.compile(this.mRegex).matcher(this.mText);
                    if (matcher.find()) {
                        start = matcher.start();
                        end = matcher.end();
                    }
                } else if (this.mStart != this.mEnd && this.mEnd > this.mStart) {
                    start = this.mStart;
                    end = this.mEnd;
                }

                if (start != end && end > start) {
                    int flag = 33;
                    if (this.mParams.color != 0) {
                        spanString.setSpan(new ForegroundColorSpan(this.mParams.color), start, end, flag);
                    }

                    if (this.mParams.size > 0) {
                        spanString.setSpan(new AbsoluteSizeSpan(this.mParams.size), start, end, flag);
                    }

                    if (this.mParams.deleteLine) {
                        spanString.setSpan(new StrikethroughSpan(), start, end, flag);
                    }

                    if (this.mParams.style >= 0) {
                        spanString.setSpan(new StyleSpan(this.mParams.style), start, end, flag);
                    }

                    if (this.mParams.span != null) {
                        spanString.setSpan(this.mParams.span, start, end, flag);
                    }
                }

                return spanString;
            }
        }
    }
}
